package com.yumeng.libcommonview.view.index

import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout


import com.yumeng.libcommonview.R
import com.yumeng.libcommonview.view.index.animation.IndexBarAnimationManager
import com.yumeng.libcommonview.view.index.animation.VerticalIndexBarAnimationManager

class IndexBar : FrameLayout {

    private lateinit var indexBarViewComponents: IndexBarViewComponents

    private var indexBarAnimationManager: IndexBarAnimationManager =
            VerticalIndexBarAnimationManager()

    private var showBubbleAnimation: ValueAnimator? = null
    private var hideBubbleAnimation: ValueAnimator? = null
    private var currentScrollbarState = BubbleIndexBarState.HIDDEN_BUBBLE

    lateinit var wordsNavigation: WordsNavigation

    constructor(context: Context) : super(context) {
        initializeView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    ) {
        initializeView()
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int,
            defStyleRes: Int
    ) : super(
            context,
            attrs,
            defStyleAttr,
            defStyleRes
    ) {
        initializeView()
    }

    private fun initializeView() {
        val root =
                LayoutInflater.from(context).inflate(R.layout.widget_bubble_index_bar, this, true)
        indexBarViewComponents =
                IndexBarViewComponents(root.findViewById(R.id.wordsNavigation), root.findViewById(R.id.bubble))
        initializeAnimations()
        post { setScrollState(BubbleIndexBarState.HIDDEN_BUBBLE) }
        post { indexBarViewComponents.textView.y = -1000F }
        wordsNavigation = indexBarViewComponents.wordsNavigation
        indexBarViewComponents.wordsNavigation.setOnTouchIndexListener { y, index, indexBarState ->
            setScrollState(indexBarState)
            if (indexBarState == BubbleIndexBarState.VISIBLE_BUBBLE) {
                val bubble = indexBarViewComponents.textView
                bubble.text = index
                moveBubble(y)
            }
        }
    }


    private fun initializeAnimations() {
        showBubbleAnimation =
                indexBarAnimationManager.provideShowBubbleAnimation(indexBarViewComponents)
        hideBubbleAnimation =
                indexBarAnimationManager.provideHideBubbleAnimation(indexBarViewComponents)
        val showIndexUpdateListener =
                indexBarAnimationManager.provideShowBubbleUpdateListener(indexBarViewComponents)
        val hideIndexUpdateListener =
                indexBarAnimationManager.provideHideBubbleUpdateListener(indexBarViewComponents)
        showBubbleAnimation?.addUpdateListener(showIndexUpdateListener)
        hideBubbleAnimation?.addUpdateListener(hideIndexUpdateListener)
    }

    private fun setScrollState(scrollStateBubble: BubbleIndexBarState) {
        this.currentScrollbarState = scrollStateBubble
        renderScrollState()
    }

    private fun renderScrollState() {
        when (currentScrollbarState) {
            BubbleIndexBarState.VISIBLE_BUBBLE -> onVisibleBubble()
            BubbleIndexBarState.HIDDEN_BUBBLE -> onHiddenBubble()
        }
    }

    private fun moveBubble(moveY: Float) {
        val bubble = indexBarViewComponents.textView
        bubble.y = moveY
    }

    fun onHiddenBubble() {
        playHideBubbleAnimation()
    }

    private fun onVisibleBubble() {
        playShowBubbleAnimation()
    }

    private fun playHideBubbleAnimation() {
        showBubbleAnimation?.cancel()
        if (indexBarAnimationManager.isBubbleHidden(indexBarViewComponents) || hideBubbleAnimation?.isRunning == true) {
            return
        }
        hideBubbleAnimation =
                indexBarAnimationManager.provideHideBubbleAnimation(indexBarViewComponents)
        hideBubbleAnimation?.addUpdateListener(
                indexBarAnimationManager.provideHideBubbleUpdateListener(indexBarViewComponents)
        )
        hideBubbleAnimation?.start()
    }


    private fun playShowBubbleAnimation() {
        hideBubbleAnimation?.cancel()
        if (indexBarAnimationManager.isBubbleVisible(indexBarViewComponents) || showBubbleAnimation?.isRunning == true) {
            return
        }
        showBubbleAnimation =
                indexBarAnimationManager.provideShowBubbleAnimation(indexBarViewComponents)
        showBubbleAnimation?.addUpdateListener(
                indexBarAnimationManager.provideShowBubbleUpdateListener(
                        indexBarViewComponents
                )
        )
        showBubbleAnimation?.start()
    }


}
