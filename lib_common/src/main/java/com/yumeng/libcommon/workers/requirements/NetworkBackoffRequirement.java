package com.yumeng.libcommon.workers.requirements;

import android.content.Context;

import androidx.annotation.NonNull;


import com.yumeng.libcommon.workers.Job;

import java.util.concurrent.TimeUnit;

public class NetworkBackoffRequirement implements Requirement, ContextDependent {

  private static final String TAG = NetworkBackoffRequirement.class.getSimpleName();

  private static final long MAX_WAIT = TimeUnit.SECONDS.toMillis(30);

  private transient Context context;

  public NetworkBackoffRequirement(@NonNull Context context) {
    this.context = context.getApplicationContext();
  }

  @Override
  public boolean isPresent(@NonNull Job job) {
    return new NetworkRequirement(context).isPresent() && System.currentTimeMillis() >= calculateNextRunTime(job);
  }

  @Override
  public void onRetry(@NonNull Job job) {
  }

  @Override
  public void setContext(Context context) {
    this.context = context.getApplicationContext();
  }

  private static long calculateNextRunTime(@NonNull Job job) {
    return 0;
  }
}