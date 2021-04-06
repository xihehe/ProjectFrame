package com.yumeng.libcommon.ext

import android.animation.Animator

inline fun Animator.addListener(
        crossinline onEnd: (animator: Animator) -> Unit = {},
        crossinline onStart: (animator: Animator) -> Unit = {},
        crossinline onCancel: (animator: Animator) -> Unit = {},
        crossinline onRepeat: (animator: Animator) -> Unit = {}
): Animator.AnimatorListener {
    val listener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animator: Animator) = onRepeat(animator)
        override fun onAnimationEnd(animator: Animator) = onEnd(animator)
        override fun onAnimationCancel(animator: Animator) = onCancel(animator)
        override fun onAnimationStart(animator: Animator) = onStart(animator)
    }
    addListener(listener)
    return listener
}