package com.yumeng.libcommon.ext

import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

fun NestedScrollView.scrollToSelf(x: Int, y: Int) {
    this.apply {
        fling(y);
        smoothScrollTo(x, y)
    }
}

fun AppBarLayout.scrollToSelf(toTop: Boolean) {
    val behavior = (this.layoutParams as CoordinatorLayout.LayoutParams).behavior
    if (behavior is AppBarLayout.Behavior) {
        val appBarLayoutBehavior = behavior as AppBarLayout.Behavior
        if (toTop) {
            appBarLayoutBehavior.topAndBottomOffset = 0
        } else {
            val height = this.height
            appBarLayoutBehavior.topAndBottomOffset = -height//快速滑动实现吸顶效果
        }
    }
}

fun RecyclerView.scrollToSelf(x: Int, y: Int, position: Int) {
    this.apply {
        fling(x, y);
        smoothScrollToPosition(position)
    }

}

