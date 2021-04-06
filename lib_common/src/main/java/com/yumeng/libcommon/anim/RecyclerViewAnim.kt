package com.yumeng.libcommon.anim

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.yumeng.libcommon.R

/**
 * Created by Chauncey on 2018/7/19 11:20.
 * recycler 动画
 * 已经写了 顶层 使用
 */
object RecyclerViewAnim {

    const val FROM_RIGHT = 0
    const val FROM_LEFT = 1

    fun runLayoutAnimation(recyclerView: RecyclerView, type: Int) {
        recyclerView.layoutAnimation = AnimationUtils.loadLayoutAnimation(recyclerView.context, if (type == FROM_RIGHT) R.anim.layout_animation_from_right else R.anim.layout_animation_from_left)
        recyclerView.scheduleLayoutAnimation()
    }
}
