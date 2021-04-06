package com.yumeng.libcommon.helper

import android.util.Log


import com.yumeng.libcommon.R
import com.yumeng.libcommon.context.AppContextWrapper
import com.yumeng.libcommon.utils.ToastUtils
import com.yumeng.libcommon.utils.luban.Luban
import com.yumeng.libcommon.utils.luban.OnCompressListener
import java.io.File

object LubanHelper {

    //带Id 的 方便修改对应list的那个
    fun luban(index: Int, filePath: String?, callback: TakeIdCallback) {
        if (filePath.isNullOrEmpty()) {
            ToastUtils.getInstance()
                .shortToast(AppContextWrapper.getApplicationContext().getString(R.string.the_path_is_empty))
            return
        }
        val file = File(filePath)
        if (!file.exists()) {
            return
        }
        Luban.with(AppContextWrapper.getApplicationContext())
            .load(file)
            .ignoreBy(100)
            .setCompressListener(object : OnCompressListener {
                override fun onStart() {
                }

                override fun onSuccess(file: File?) {
                    callback.onResult(index, file)
                }

                override fun onError(e: Throwable?) {
                    callback.onResult(index, null)
                }
            }).launch()
    }

    fun luban(isLuban: Boolean, file: File?, callback: CommonCallback) {
        if (!isLuban) {
            callback.onResult(file)
            return
        }
        Luban.with(AppContextWrapper.getApplicationContext())
            .load(file)
            .setCompressListener(object : OnCompressListener {
                override fun onStart() {
                }

                override fun onSuccess(file: File?) {
                    callback.onResult(file)
                }

                override fun onError(e: Throwable?) {
                }

            }).launch()
    }


    interface CommonCallback {
        fun onResult(file: File?)
    }

    interface TakeIdCallback {
        fun onResult(index: Int, file: File?)
    }


}