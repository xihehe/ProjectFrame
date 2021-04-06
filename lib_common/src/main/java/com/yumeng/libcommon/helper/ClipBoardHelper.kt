package com.yumeng.libcommon.helper

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

/**
 * Created by Chauncey on 2018/6/6 17:24.
 *  剪贴板辅助类
 */
object ClipBoardHelper {
    fun newPlainText(context: Context, text: String) {
        //获取剪贴板管理器：
        val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        // 创建普通字符型ClipData
        val mClipData = ClipData.newPlainText(text, text)
        // 将ClipData内容放到系统剪贴板里。
        cm.primaryClip = mClipData
    }
}