package com.yumeng.libbase.helper


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import com.yumeng.libbase.R
import com.yumeng.libcommon.manage.PhotoManager
import kotlinx.android.synthetic.main.dialog_photo.view.*
import kotlinx.android.synthetic.main.dialog_photo.view.tvOpenAlbum

import java.io.File

/**
 * 对话框 管理类
 */
object DialogHelper {

    //拍照或者选取照片对话框
    fun showPhotoDialog(activity: FragmentActivity, onPhotoCallback: OnPhotoCallback? = null) {
        val inflate = View.inflate(activity, R.layout.dialog_photo, null)

        val bottomDialog = showBottomDialog(inflate)
        inflate.tvOpenCamera.setOnClickListener {
            PhotoManager.openCamera(activity, object : PhotoManager.OnCameraCallback {
                override fun onSuccess(path: String) {
                    onPhotoCallback?.onSuccess(path)
                }

                override fun onFailure() {
                    onPhotoCallback?.onFailure()
                }

            })
            bottomDialog.dismiss()
        }

        inflate.tvOpenAlbum.setOnClickListener {
            PhotoManager.openAlbum(activity, object : PhotoManager.OnAlbumCallback {
                override fun onSuccess(path: String) {
                    onPhotoCallback?.onSuccess(path)
                }

                override fun onFailure() {
                    onPhotoCallback?.onFailure()
                }

            })
            bottomDialog.dismiss()

        }
        bottomDialog.show()
    }




    fun showDialog(context: Context, view: View): Dialog =
        Dialog(context, R.style.AlertDialogStyle).apply {
            // 定义Dialog布局和参数
            this.setContentView(view)
            // 设置点击外围解散
            this.setCanceledOnTouchOutside(true)
            this.show()

            this.window?.let { window ->
                val lp = window.attributes
                window.setGravity(Gravity.CENTER)

                view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)

                lp.height = view.measuredHeight
                lp.width = view.measuredWidth

                window.attributes = lp
            }
        }




    fun showBottomDialog(view: View, cancelable: Boolean = true): Dialog {
        val dialog = Dialog(view.context)
        dialog.setContentView(view)
        dialog.setCancelable(cancelable)
        dialog.show()
        val window = dialog.window
        if (window != null) {
            window.setGravity(Gravity.BOTTOM)
            window.setWindowAnimations(R.style.BottomDialogTheme)
            window.setBackgroundDrawable(ColorDrawable(Color.WHITE))

            val lp = window.attributes
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = lp
        }

        return dialog
    }

    //
    interface OnDownloadListener {
        fun onSuccess(file: File)

        fun onFailure(e: Throwable)
    }

    interface OnPhotoCallback {
        fun onSuccess(path: String)

        fun onFailure()
    }
}

