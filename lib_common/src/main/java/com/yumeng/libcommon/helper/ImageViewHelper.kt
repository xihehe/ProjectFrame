package com.yumeng.libcommon.helper

import android.view.ViewGroup
import android.widget.ImageView

/**
 * Created by Chauncey on 2019-05-28 16:25.
 * 应该是属于一个计算  image宽高的一个方法，应该用于客服聊天那边
 */
object ImageViewHelper {
    fun getImageLayoutParams(width: Int, height: Int, imageView: ImageView): ViewGroup.LayoutParams {
        val layoutParams = imageView.layoutParams
        val context = imageView.context
        if (width > height) {
            //横屏
            val widthDp = DensityHelper.dip2px(context, width.toFloat())
            if (widthDp > 180) {
                layoutParams.width = DensityHelper.dip2px(context, 180f).toInt()
                val scaleX = width.toFloat().div(layoutParams.width)
                val h = height.div(scaleX)
                layoutParams.height = h.toInt()
            } else {
                layoutParams.width = width
                layoutParams.height = height
            }
        } else if (width == height) {
            val heightDp = DensityHelper.px2dip(context, height.toFloat())
            if (heightDp > 180) {
                layoutParams.height = DensityHelper.dip2px(context, 180f).toInt()
                layoutParams.width = DensityHelper.dip2px(context, 180f).toInt()
            } else {
                layoutParams.height = height
                layoutParams.width = width
            }
        } else {
            val heightDp = DensityHelper.px2dip(context, height.toFloat())
            if (heightDp > 180) {
                layoutParams.height = DensityHelper.dip2px(context, 180f).toInt()
                val scaleX = height.toFloat().div(layoutParams.height)
                val w = width.div(scaleX)
                layoutParams.width = w.toInt()
            } else {
                layoutParams.height = height
                layoutParams.width = width
            }
        }
        return layoutParams
    }
}
