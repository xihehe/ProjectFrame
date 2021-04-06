package com.yumeng.libcommon.rxManager;

import android.content.Intent;

import androidx.annotation.Nullable;

import java.io.Serializable;

/**
 * 跳转发挥result model
 */
interface OnResult extends Serializable {
    void response(int requestCode, int resultCode, @Nullable Intent data);
    void error(Throwable throwable);
}
