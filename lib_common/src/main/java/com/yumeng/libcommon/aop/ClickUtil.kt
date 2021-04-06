package com.yumeng.libcommon.aop

import android.os.SystemClock
import kotlin.math.abs

object ClickUtil {

    private var mLastClickTime: Long = 0

    fun isFastDoubleClick(intervalMillis: Long): Boolean {
        val time = SystemClock.elapsedRealtime()
        val timeInterval = abs(time - mLastClickTime)
        return if (timeInterval < intervalMillis) {
            true
        } else {
            mLastClickTime = time
            false
        }
    }
}