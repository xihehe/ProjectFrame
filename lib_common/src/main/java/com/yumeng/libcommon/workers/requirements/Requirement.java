package com.yumeng.libcommon.workers.requirements;

import androidx.annotation.NonNull;


import com.yumeng.libcommon.workers.Job;

import java.io.Serializable;

public interface Requirement extends Serializable {
  /**
   * @return true if the requirement is satisfied, false otherwise.
   */
  boolean isPresent(@NonNull Job job);

  void onRetry(@NonNull Job job);
}
