package com.yumeng.libbaseProject.view.audioRecorder


import com.yumeng.libbaseProject.R
import com.yumeng.libcommon.context.AppContextWrapper
import com.yumeng.libcommon.utils.ToastUtils

object VoipUtils {

    var globalType: String? = null


    fun isCalling(): Boolean {
        return if (globalType.isNullOrEmpty()) {
            false
        } else {
            if (globalType == "video") {
                ToastUtils.getInstance().shortToast(AppContextWrapper.getApplicationContext().getString(
                    R.string.video_call_is_on_tips))
            } else {
                ToastUtils.getInstance().shortToast(AppContextWrapper.getApplicationContext().getString(R.string.voice_call_is_on_tips))
            }
            true
        }
    }
}