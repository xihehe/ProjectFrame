package com.yumeng.libcommon.rxManager;

import android.content.Intent;

import androidx.annotation.Nullable;

import io.reactivex.Observable;

/**
 * 传递一些 参数
 * 返回是一个Observable
 * @param <T>
 */
public interface OnPreResult<T> {
    Observable<T> response(int requestCode, int resultCode, @Nullable Intent data);
}
