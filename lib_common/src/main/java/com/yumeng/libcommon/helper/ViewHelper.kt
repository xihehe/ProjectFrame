package com.yumeng.libcommon.helper

import android.view.View

/**
 * Created by Chauncey on 2018/12/14 13:57.
 */
object ViewHelper {
    //使view 填充 系统窗口
    fun fitsSystemWindows(view: View) {
        view.let {
            val statusBarHeight = StatusBarHelper.getStatusBarHeight(it.context)
            it.setPadding(it.paddingLeft, statusBarHeight, it.paddingRight,
                    it.paddingBottom)
//            it.invalidate()
        }
    }
}
