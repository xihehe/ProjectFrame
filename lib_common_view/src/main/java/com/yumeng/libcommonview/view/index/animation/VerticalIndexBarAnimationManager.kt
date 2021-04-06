package com.yumeng.libcommonview.view.index.animation

import android.animation.ValueAnimator
import com.yumeng.libcommonview.view.index.IndexBarViewComponents

class VerticalIndexBarAnimationManager : IndexBarAnimationManager {
    override fun provideShowBubbleAnimation(viewComponents: IndexBarViewComponents): ValueAnimator {
        return ValueAnimator.ofFloat(HIDDEN_ALPHA, VISIBLE_ALPHA)
    }

    override fun provideShowBubbleUpdateListener(viewComponents: IndexBarViewComponents): ValueAnimator.AnimatorUpdateListener {
        if (updateListener == null) {
            updateListener = ValueAnimator.AnimatorUpdateListener {
                val animateValue = it.animatedValue as Float
                viewComponents.textView.alpha = animateValue
                viewComponents.textView.scaleX = animateValue
                viewComponents.textView.scaleY = animateValue
            }
        }
        return updateListener!!
    }

    override fun provideHideBubbleUpdateListener(viewComponents: IndexBarViewComponents): ValueAnimator.AnimatorUpdateListener {
        return provideShowBubbleUpdateListener(viewComponents)
    }

    override fun provideHideBubbleAnimation(viewComponents: IndexBarViewComponents): ValueAnimator {
        return ValueAnimator.ofFloat(VISIBLE_ALPHA, HIDDEN_ALPHA)
    }

    override fun isBubbleVisible(viewComponents: IndexBarViewComponents): Boolean {
        return viewComponents.textView.alpha == VISIBLE_ALPHA
    }

    override fun isBubbleHidden(viewComponents: IndexBarViewComponents): Boolean {
        return viewComponents.textView.alpha == HIDDEN_ALPHA
    }

    companion object {
        private const val VISIBLE_ALPHA = 1f
        private const val HIDDEN_ALPHA = 0f
    }

    private var updateListener: ValueAnimator.AnimatorUpdateListener? = null


}