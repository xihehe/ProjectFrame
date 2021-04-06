package com.yumeng.libcommon.workers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


import com.yumeng.libcommon.workers.requirements.ContextDependent;
import com.yumeng.libcommon.workers.requirements.NetworkRequirement;

import java.io.Serializable;
import java.util.Collections;
import java.util.UUID;

public abstract class Job extends Worker implements Serializable {

  private static final String TAG = Job.class.getSimpleName();

  private static final WorkLockManager WORK_LOCK_MANAGER = new WorkLockManager();

  public static final String KEY_RETRY_COUNT        = "Job_retry_count";
  public static final String KEY_RETRY_UNTIL        = "Job_retry_until";
  public static final String KEY_SUBMIT_TIME        = "Job_submit_time";
  public static final String KEY_FAILED             = "Job_failed";
  public static final String KEY_REQUIRES_NETWORK   = "Job_requires_network";
  public static final String KEY_BACK_ID   = "Job_requires_back_id";
//  static final String KEY_REQUIRES_SQLCIPHER = "Job_requires_sqlcipher";

  private JobParameters parameters;

  public Job(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  /**
   * Invoked when a job is first created in our own codebase.
   */
  @SuppressLint("RestrictedApi")
  protected Job(@NonNull Context context, @Nullable JobParameters parameters) {
    //noinspection ConstantConditions
    super(context, new WorkerParameters(null, null, Collections.<String>emptyList(), null, 0, null, null, null));
    this.parameters = parameters;
  }

  @Override
  public @NonNull Result doWork() {
    log("doWork()" + logSuffix());

    try (WorkLockManager.WorkLock workLock = WORK_LOCK_MANAGER.acquire(getId())) {
      Result result = workLock.getResult();

      if (result == null) {
        result = doWorkInternal();
        workLock.setResult(result);
      } else {
        log("Using result from preempted run (" + result + ")." + logSuffix());
      }

      return result;
    }
  }

  private @NonNull Result doWorkInternal() {
    Data data = getInputData();
    if (this instanceof ContextDependent) {
      ((ContextDependent)this).setContext(getApplicationContext());
    }

    boolean foregroundRunning = false;

    try {

      if (data.getBoolean(KEY_FAILED, false)) {
        warn("Failing due to a failure earlier in the chain." + logSuffix());
        return cancel();
      }

      if (!withinRetryLimits(data)) {
        warn("达到重试限制后失败." + logSuffix());
        return cancel();
      }

//      if(needsForegroundService(data)){
//        foregroundRunning = true;
//      }

      onRun();
      log("Successfully completed." + logSuffix());
      return success();
    } catch (Exception e) {
      if (onShouldRetry(e)) {
        log("Retrying after a retryable exception." + logSuffix(), e);
        return retry();
      }
      warn("Failing due to an exception." + logSuffix(), e);
      return cancel();
    }
  }

  @Override
  public void onStopped() {
    log("onStopped()" + logSuffix());
  }

  final void onSubmit(@NonNull Context context, @NonNull UUID id) {
    Log.i(TAG, buildLog(id, "onSubmit() network: " + (new NetworkRequirement(getApplicationContext()).isPresent())));

    if (this instanceof ContextDependent) {
      ((ContextDependent) this).setContext(context);
    }

    onAdded();
  }


  /**
   * Called after a run has finished and we've determined a retry is required, but before the next
   * attempt is run.
   */
  protected void onRetry() { }

  /**
   * Called after a job has been added to the JobManager queue. Invoked off the main thread, so its
   * safe to do longer-running work. However, work should finish relatively quickly, as it will
   * block the submission of future tasks.
   */
  protected void onAdded() { }

  /**
   * All instance state needs to be persisted in the provided {@link Data.Builder} so that it can
   * @param dataBuilder The builder where you put your state.
   * @return The result of {@code dataBuilder.build()}.
   */
  protected abstract @NonNull Data serialize(@NonNull Data.Builder dataBuilder);


  /**
   * 实际执行的工作
   */
  public abstract void onRun() throws Exception;

  /**
   * 如果无法运行，则调用onShouldRetry返回false，或者重试次数超过作业配置的重试次数
   */
  protected abstract void onCanceled();

  /**
   * 如果onRun抛出异常调用onShouldRetry重试调用
   */
  protected abstract boolean onShouldRetry(Exception exception);

  @Nullable JobParameters getJobParameters() {
    return parameters;
  }

  private Result success() {
    return Result.success();
  }

  private Result retry() {
    onRetry();
    return Result.retry();
  }

  private Result cancel() {
    onCanceled();
    return Result.success(new Data.Builder().putBoolean(KEY_FAILED, true).build());
  }


  private boolean withinRetryLimits(@NonNull Data data) {
    int  retryCount = data.getInt(KEY_RETRY_COUNT, 0);
    long retryUntil = data.getLong(KEY_RETRY_UNTIL, 0);

    if (retryCount > 0) {
      return getRunAttemptCount() <= retryCount;
    }

    return System.currentTimeMillis() < retryUntil;
  }

  private boolean needsForegroundService(@NonNull Data data) {
    NetworkRequirement networkRequirement = new NetworkRequirement(getApplicationContext());
    boolean            requiresNetwork    = data.getBoolean(KEY_REQUIRES_NETWORK, false);

    return requiresNetwork && !networkRequirement.isPresent();
  }

  private void log(@NonNull String message) {
    log(message, null);
  }

  private void log(@NonNull String message, @Nullable Throwable t) {
    Log.i(TAG, buildLog(getId(), message), t);
  }

  private void warn(@NonNull String message) {
    warn(message, null);
  }

  private void warn(@NonNull String message, @Nullable Throwable t) {
    Log.w(TAG, buildLog(getId(), message), t);
  }

  private String buildLog(@NonNull UUID id, @NonNull String message) {
    return "[" + id + "] " + getClass().getSimpleName() + " :: " + message;
  }

  protected String logSuffix() {
    long timeSinceSubmission = System.currentTimeMillis() - getInputData().getLong(KEY_SUBMIT_TIME, 0);
    return " (Time since submission: " + timeSinceSubmission + " ms, Run attempt: " + getRunAttemptCount() + ", isStopped: " + isStopped() + ")";
  }
}
