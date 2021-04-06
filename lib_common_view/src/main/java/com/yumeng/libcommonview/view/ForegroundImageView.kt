package com.yumeng.libcommonview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View

class ForegroundImageView : androidx.appcompat.widget.AppCompatImageView {

    private var mForeground: Drawable? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun setForeground(foreground: Drawable) {
        mForeground = foreground
        measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        mForeground?.setBounds(0, 0, measuredWidth, measuredHeight)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mForeground?.draw(canvas)
    }
}
