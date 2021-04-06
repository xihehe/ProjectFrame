package com.yumeng.libcommon.workers

import android.content.Context
import android.util.Log
import androidx.work.*
import com.annimon.stream.Stream
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class JobManager(private val context: Context, private val workManager: WorkManager) {


    private val TAG = JobManager::class.java.simpleName
    private val NETWORK_CONSTRAINT: Constraints by lazy {
        Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
    }

    private val executor: ExecutorService by lazy {
        Executors.newSingleThreadExecutor()
    }

    fun add(job: Job) {
        val jobParameters = job.jobParameters
                ?: throw IllegalStateException("Jobs must have JobParameters at this stage. (" + job::class.java.simpleName + ")")

        startChain(job).enqueue(jobParameters.soloChainParameters)
    }

    fun add(job: Job, builder: PeriodicWorkRequest) {

    }


    fun startChain(job: Job): Chain {
        return startChain(Collections.singletonList(job))
    }

    fun startChain(jobs: List<Job>): Chain {
        return Chain(jobs)
    }

    private fun enqueueChain(chain: Chain, chainParameters: ChainParameters) {
        executor.execute {
            try {
                workManager.pruneWork().result.get()
            } catch (e: ExecutionException) {
                Log.w(TAG, "Failed to prune work.", e)
            } catch (e: InterruptedException) {
                Log.w(TAG, "Failed to prune work.", e)
            }

            val jobListChain = chain.jobListChain
            val requestListChain = Stream.of(jobListChain)
                    .filter { jobList -> !jobList.isEmpty() }
                    .map { jobList ->
                        Stream.of(jobList).map {
                            toWorkRequest(it)
                        }.toList()
                    }.toList()
            if (jobListChain.isEmpty()) {
                throw IllegalStateException("Enqueued an empty chain.")
            }

            for (i in jobListChain.indices) {
                for (j in 0 until jobListChain[i].size) {
                    jobListChain[i][j].onSubmit(context, requestListChain[i][j].id)
                }
            }

            var continuation: WorkContinuation

            if (chainParameters.groupId.isPresent) {
                val policy = if (chainParameters.shouldIgnoreDuplicates()) ExistingWorkPolicy.KEEP else ExistingWorkPolicy.APPEND
                continuation = workManager.beginUniqueWork(chainParameters.groupId.get(), policy, requestListChain[0])
            } else {
                continuation = workManager.beginWith(requestListChain[0])
            }

            for (i in 1 until requestListChain.size) {
                continuation = continuation.then(requestListChain[i])
            }

            continuation.enqueue()
        }

    }


    private fun toWorkRequest(job: Job): OneTimeWorkRequest {
        val jobParameters = job.jobParameters
                ?: throw IllegalStateException("Jobs must have JobParameters at this stage. (" + job::class.java.simpleName + ")")

        val dataBuilder = Data.Builder().putInt(Job.KEY_RETRY_COUNT, jobParameters.retryCount)
                .putLong(Job.KEY_RETRY_UNTIL, jobParameters.retryUntil)
                .putLong(Job.KEY_SUBMIT_TIME, System.currentTimeMillis())
                .putBoolean(Job.KEY_REQUIRES_NETWORK, jobParameters.requiresNetwork())
                .putString(Job.KEY_BACK_ID, jobParameters.backId)
//                .putBoolean(Job.KEY_REQUIRES_SQLCIPHER, jobParameters.requiresSqlCipher())
        val data = job.serialize(dataBuilder)

        val requestBuilder = OneTimeWorkRequest.Builder(job::class.java)
                .setInputData(data)
                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)

        if (jobParameters.requiresNetwork()) {
            requestBuilder.setConstraints(NETWORK_CONSTRAINT)
        }

        return requestBuilder.build()
    }

    inner class Chain(jobs: List<Job>) {

        private val jobs = LinkedList<List<Job>>()

        val jobListChain: List<List<Job>>
            get() = jobs

        init {
            this.jobs.add(ArrayList(jobs))
        }

        fun then(job: Job): Chain {
            return then(Collections.singletonList(job))
        }

        fun then(jobs: List<Job>): Chain {
            this.jobs.add(ArrayList(jobs))
            return this
        }

        fun enqueue(chainParameters: ChainParameters) {
            enqueueChain(this, chainParameters)
        }
    }


}