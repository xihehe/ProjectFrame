package com.yumeng.libcommon.handler

import android.os.Handler
import android.os.Message

import java.lang.ref.WeakReference

/**
 * Created by Chauncey on 2019/3/9 12:02.
 *弱引用防止内存泄露
 */
class WeakReferenceHandler<T : WeakReferenceHandler.Callback>(t: T) : Handler() {

    private val mWeakReference: WeakReference<T> = WeakReference(t)

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        mWeakReference.get()?.handleMessage(msg)
    }

    interface Callback {
        fun handleMessage(msg: Message)
    }
}
