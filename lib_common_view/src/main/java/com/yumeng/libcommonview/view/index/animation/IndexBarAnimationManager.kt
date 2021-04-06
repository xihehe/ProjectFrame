package com.yumeng.libcommonview.view.index.animation

import android.animation.ValueAnimator
import com.yumeng.libcommonview.view.index.IndexBarViewComponents

interface IndexBarAnimationManager {


    fun provideShowBubbleAnimation(viewComponents: IndexBarViewComponents): ValueAnimator

    fun provideShowBubbleUpdateListener(viewComponents: IndexBarViewComponents): ValueAnimator.AnimatorUpdateListener

    fun provideHideBubbleUpdateListener(viewComponents: IndexBarViewComponents): ValueAnimator.AnimatorUpdateListener

    fun provideHideBubbleAnimation(viewComponents: IndexBarViewComponents): ValueAnimator

    fun isBubbleVisible(viewComponents: IndexBarViewComponents): Boolean

    fun isBubbleHidden(viewComponents: IndexBarViewComponents): Boolean
}