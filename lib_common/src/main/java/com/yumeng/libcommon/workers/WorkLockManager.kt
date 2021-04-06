package com.yumeng.libcommon.workers

import androidx.work.ListenableWorker
import java.io.Closeable
import java.util.*
import java.util.concurrent.Semaphore

class WorkLockManager {

    private val locks = HashMap<UUID?, WorkLock>()

    fun acquire(uuid: UUID?): WorkLock? {
        var workLock: WorkLock?
        synchronized(this) {
            workLock = locks[uuid]
            if (workLock == null) {
                workLock = WorkLock(uuid)
                locks[uuid] = workLock!!
            }
            workLock?.increment()
        }
        workLock?.lock?.acquireUninterruptibly()
        return workLock
    }

    private fun release(uuid: UUID?) {
        val lock: WorkLock?
        synchronized(this) {
            lock = locks[uuid]
            if (lock == null) {
                throw IllegalStateException("Released a lock that was already removed from use.")
            }
            if (lock.decrementAndGet() == 0) {
                locks.remove(uuid)
            }
        }
        lock?.lock?.release()
    }

    inner class WorkLock(private val uuid: UUID?) : Closeable {

        val lock: Semaphore by lazy {
            Semaphore(1)
        }

        var result: ListenableWorker.Result? = null

        private var count: Int = 0

        fun increment() {
            count = count.plus(1)
        }

        fun decrementAndGet(): Int {
            count = count.dec()
            return count
        }

        override fun close() {
            this@WorkLockManager.release(uuid)
        }
    }
}