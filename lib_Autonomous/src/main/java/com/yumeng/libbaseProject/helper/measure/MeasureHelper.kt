package com.yumeng.libbaseProject.helper.measure

import android.view.View
import com.yumeng.libcommon.context.AppContextWrapper
import com.yumeng.libcommon.helper.DensityHelper

import com.yumeng.libcommon.utils.ScreenUtils

object MeasureHelper {

    fun calculatePopWindowPos(anchorView: View?, contentView: View?): IntArray {
        val windowPos = IntArray(2)
        val anchorLoc = IntArray(2)
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView?.getLocationOnScreen(anchorLoc)
        val screenWidth = ScreenUtils.getScreenWidthPoint(contentView?.context)
        contentView?.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        // 计算contentView的高宽
        val windowHeight = contentView?.measuredHeight
        val windowWidth = contentView?.measuredWidth
        // 判断需要向上弹出还是向下弹出显示
        windowPos[0] = screenWidth - windowWidth!!
        windowPos[1] = anchorLoc[1] - windowHeight!!
        return windowPos
    }

    fun calculatePopWindowPos2(anchorView: View?, contentView: View?): IntArray {
        val windowPos = IntArray(2)
        val anchorLoc = IntArray(2)
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView?.getLocationOnScreen(anchorLoc)
        val anchorHeight = anchorView?.height?:0
        // 获取屏幕的高宽
        val screenHeight = ScreenUtils.getScreenHeightPoint(contentView?.context)
        val screenWidth = ScreenUtils.getScreenWidthPoint(contentView?.context)
        contentView?.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        // 计算contentView的高宽
        val windowHeight = contentView?.measuredHeight
        val windowWidth = contentView?.measuredWidth
        // 判断需要向上弹出还是向下弹出显示
        val isNeedShowUp = screenHeight - anchorLoc[1] - anchorHeight < windowHeight ?: 0
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth!!
            windowPos[1] = anchorLoc[1] - windowHeight!!
        } else {
            windowPos[0] = screenWidth - windowWidth!!
            windowPos[1] = anchorLoc[1] + anchorHeight
        }
        return windowPos
    }


    fun getImageLayoutParams(width: Int, height: Int): MeasureModel {
        val mContext = AppContextWrapper.getApplicationContext()
        var measuredWidth = width.toDouble()
        var measuredHeight = height.toDouble()
        val minWidth =
            DensityHelper.dp2px(mContext, 80f)
        val maxWidth =
            DensityHelper.dp2px(mContext, 180f)
        val minHeight =
            DensityHelper.dp2px(mContext, 80f)
        val maxHeight =
            DensityHelper.dp2px(mContext, 180f)
        val widthInBounds = measuredWidth >= minWidth && measuredWidth <= maxWidth
        val heightInBounds = measuredHeight >= minHeight && measuredHeight <= maxHeight
        if (!widthInBounds || !heightInBounds) {
            val minWidthRatio = measuredWidth / minWidth
            val maxWidthRatio = measuredWidth / maxWidth
            val minHeightRatio = measuredHeight / minHeight
            val maxHeightRatio = measuredHeight / maxHeight
            if (maxWidthRatio > 1 || maxHeightRatio > 1) {
                if (maxWidthRatio >= maxHeightRatio) {
                    measuredWidth /= maxWidthRatio
                    measuredHeight /= maxWidthRatio
                } else {
                    measuredWidth /= maxHeightRatio
                    measuredHeight /= maxHeightRatio
                }

                measuredWidth = Math.max(measuredWidth, minWidth.toDouble())
                measuredHeight = Math.max(measuredHeight, minHeight.toDouble())

            } else if (minWidthRatio < 1 || minHeightRatio < 1) {
                if (minWidthRatio <= minHeightRatio) {
                    measuredWidth /= minWidthRatio
                    measuredHeight /= minWidthRatio
                } else {
                    measuredWidth /= minHeightRatio
                    measuredHeight /= minHeightRatio
                }

                measuredWidth = Math.min(measuredWidth, maxWidth.toDouble())
                measuredHeight = Math.min(measuredHeight, maxHeight.toDouble())

            }
        }
        val measureModel = MeasureModel()
        measureModel.width = measuredWidth.toInt()
        measureModel.height = measuredHeight.toInt()
        return measureModel
    }
}