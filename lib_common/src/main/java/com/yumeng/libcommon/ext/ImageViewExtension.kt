package com.yumeng.libcommon.ext

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.yumeng.libcommon.R

import com.yumeng.libcommon.helper.PictureHelper
import java.io.File

/**
 * Created by Chauncey on 2018/2/5 10:44.
 *
 */
fun ImageView.loadUrl(url: String?, @DrawableRes placeholderRes: Int = 0) {
    this.setTag(this.id, url)
    PictureHelper.loadImageFromPath(url, this, placeholderRes)
}

fun ImageView.loadUrl(url: String?, placeholder: Drawable?) {
    this.setTag(this.id, url)
    PictureHelper.loadImageFromPath(url, this, placeholder)
}

fun ImageView.loadUrl(resId: Int?, @DrawableRes placeholder: Int = 0) {
    this.setTag(this.id, resId)
    PictureHelper.loadImageFromPath(resId, this, placeholder)
}

fun ImageView.loadUrl(file: File) {
    PictureHelper.loadImageFromFile(file, this)
}

fun ImageView.loadAvatar(url: String?) {
    PictureHelper.loadImageFromPath(url, this, R.mipmap.ic_avatar)
}

fun ImageView.loadAvatar(resId: Int?) {
    PictureHelper.loadImageAvatar(resId, this)
}

fun ImageView.loadUrlToRadius(url: String?, radius: Float = 5F) {
    this.setTag(this.id, url)
    PictureHelper.loadImageFromPath(url, this, R.color.colorGray, radius)
}

fun ImageView.loadUrlToRadius(path: Int?, radius: Float = 5F) {
    this.setTag(this.id, url)
    PictureHelper.loadImageFromPath(path, this, R.color.colorGray, radius)
}


fun ImageView.loadGroupAvatar(url: String?, @DrawableRes placeholderRes: Int = R.mipmap.create_group) {
    PictureHelper.loadImageFromPath(url, this, placeholderRes)
}

fun ImageView.loadUrl(url: String, width: Int, height: Int) {
    PictureHelper.loadImageFromPath(url, this, width, height)
}

fun ImageView.setGone(flag: Boolean) {
    visibility = if (flag) {
        View.GONE
    } else {
        View.VISIBLE
    }
}


val ImageView.url: String?
    get() = this.getTag(this.id) as String?
