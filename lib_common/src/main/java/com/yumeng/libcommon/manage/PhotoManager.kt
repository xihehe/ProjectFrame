package com.yumeng.libcommon.manage

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import com.yumeng.libcommon.rxManager.RxActivityResult
import com.yumeng.libcommon.rxManager.RxPermissions
import com.yumeng.libcommon.utils.FileUtil

import java.io.File
import java.io.IOException

/**
 * Created by Chauncey on 2017/9/20 11:21.
 * 系统图片选择管理类
 */

object PhotoManager {

    @SuppressLint("CheckResult")
    fun openAlbum(activity: FragmentActivity, callback: OnAlbumCallback? = null) {
        RxPermissions(activity).request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe { b ->
                    if (b) {
                        val intent = createOpenAlbumIntent()
                        RxActivityResult.on(activity)
                                .startIntent(intent)
                                .subscribe { result ->
                                    when {
                                        result.resultCode() != Activity.RESULT_OK
                                                || result.data().data?.apply {
                                            FileUtil.getPath(activity, this)?.let {
                                            callback?.onSuccess(it)
                                        }
                                        } == null -> callback?.onFailure()
                                    }
//                                    when (result.resultCode()) {
//                                        Activity.RESULT_OK -> {
//                                            result.data().data.let {
//                                                if (it == null) {
//                                                    callback?.onFailure()
//                                                } else {
//                                                    FileUtils.getPath(activity, it).let { string ->
//                                                        if (string.isNullOrBlank()) {
//                                                            callback?.onFailure()
//                                                        } else {
//                                                            callback?.onSuccess(string)
//                                                        }
//                                                    }
//                                                }
//                                            }
//
//                                        }
//                                        else -> {
//                                            callback?.onFailure()
//                                        }
//                                    }
                                }
                    } else {
                        callback?.onFailure()
                    }

                }

    }

    @SuppressLint("CheckResult")
    fun openCamera(activity: FragmentActivity, onCameraCallback: OnCameraCallback? = null) {
        RxPermissions(activity).requestEach(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe {
                    val path = getSavePicPath(activity)
                    if (it.granted) {
                        val photoURI = FileProvider.getUriForFile(activity, activity.packageName + ".provider", File(path))
                        val intent = createOpenCameraIntent()
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

                        RxActivityResult.on(activity)
                                .startIntent(intent)
                                .subscribe {result->
                                    when (result.resultCode()) {
                                        Activity.RESULT_OK -> {
                                            onCameraCallback?.onSuccess(path)
                                        }
                                        else -> {
                                            onCameraCallback?.onFailure()
                                        }
                                    }
                                }

                    } else {
                        onCameraCallback?.onFailure()
                    }
                }
    }

    private fun getSavePicPath(context: Context): String {
        val dir = "${context.externalCacheDir.path}${File.separator}image_data"
        try {
            FileUtil.createSDDirectory(dir)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return "$dir${File.separator}${System.currentTimeMillis()}.png"
    }

    private fun createOpenAlbumIntent(): Intent {
        return Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    }

    private fun createOpenCameraIntent(): Intent {
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    }

    interface OnCameraCallback {
        fun onSuccess(path: String)

        fun onFailure()
    }

    interface OnAlbumCallback {
        fun onSuccess(path: String)

        fun onFailure()
    }
}
