package com.yumeng.libcommon.workers.requirements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkRequirement extends SimpleRequirement implements ContextDependent {

  private transient Context context;

  public NetworkRequirement(Context context) {
    this.context = context;
  }

  public NetworkRequirement() {}

  @Override
  public boolean isPresent() {
    ConnectivityManager cm      = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    @SuppressLint("MissingPermission") NetworkInfo netInfo = cm.getActiveNetworkInfo();

    return netInfo != null && netInfo.isConnected();
  }

  @Override
  public void setContext(Context context) {
    this.context = context;
  }
}