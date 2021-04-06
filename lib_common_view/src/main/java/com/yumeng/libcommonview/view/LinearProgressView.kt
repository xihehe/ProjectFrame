package com.yumeng.libcommonview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.FloatRange
import com.yumeng.libcommonview.R

/**
 * Created by Chauncey on 2018/5/28 09:17.
 * 进度条View
 */
class LinearProgressView : View {

    var progressViewBackgroundColor = Color.GRAY
    var primaryColor = Color.GREEN
    var secondaryColor = Color.YELLOW
    var tertiaryColor = Color.RED

    private var backgroundPaint: Paint? = null
        get() {
            if (field == null) {
                field = Paint()
                field!!.isAntiAlias = true
                field!!.color = progressViewBackgroundColor
            }
            return field
        }

    private var primaryPaint: Paint? = null
        get() {
            if (field == null) {
                field = Paint()
                field!!.isAntiAlias = true
                field!!.color = primaryColor
            }
            return field
        }

    private var secondaryPaint: Paint? = null
        get() {
            if (field == null) {
                field = Paint()
                field!!.isAntiAlias = true
                field!!.color = secondaryColor
            }
            return field
        }

    private var tertiaryPaint: Paint? = null
        get() {
            if (field == null) {
                field = Paint()
                field!!.isAntiAlias = true
                field!!.color = tertiaryColor
            }
            return field
        }

    @FloatRange(from = 0.0, to = 100.0)
    var primaryRate: Float? = null
        set(@FloatRange(from = 0.0, to = 100.0) value) {
            field = value
            if (primaryRect == null) {
                primaryRect = RectF()
            }
            invalidate()
        }

    var secondaryRate: Float? = null
        set(@FloatRange(from = 0.0, to = 100.0) value) {
            field = value
            if (secondaryRect == null) {
                secondaryRect = RectF()
            }
            invalidate()
        }

    var tertiaryRate: Float? = null
        set(@FloatRange(from = 0.0, to = 100.0) value) {
            field = value
            if (tertiaryRect == null) {
                tertiaryRect = RectF()
            }
            invalidate()
        }

    private val backgroundRect = RectF()
    private var primaryRect: RectF? = null
    private var secondaryRect: RectF? = null
    private var tertiaryRect: RectF? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.LinearProgressView, defStyleAttr, 0)

        progressViewBackgroundColor = array.getColor(R.styleable.LinearProgressView_progressViewBackgroundColor, Color.GRAY)
        primaryColor = array.getColor(R.styleable.LinearProgressView_primaryColor, Color.GREEN)
        secondaryColor = array.getColor(R.styleable.LinearProgressView_secondaryColor, Color.YELLOW)
        tertiaryColor = array.getColor(R.styleable.LinearProgressView_tertiaryColor, Color.RED)

        array.recycle()
    }



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val radius = height / 2

        backgroundRect.right = width
        backgroundRect.bottom = height
        canvas.drawRoundRect(backgroundRect, radius, radius, backgroundPaint)


        if (tertiaryRate != null && tertiaryRate != 0F) {
            tertiaryRect!!.right = (tertiaryRate!!.plus(secondaryRate!!.plus(primaryRate
                    ?: 0F))) / 100 * width
            tertiaryRect!!.bottom = height
            canvas.drawRoundRect(tertiaryRect, radius, radius, tertiaryPaint)
        }

        if (secondaryRate != null && secondaryRate != 0F) {
            secondaryRect!!.right = (secondaryRate!!.plus(primaryRate ?: 0F)) / 100 * width
            secondaryRect!!.bottom = height
            canvas.drawRoundRect(secondaryRect, radius, radius, secondaryPaint)
        }


        if (primaryRate != null && primaryRate != 0F) {
            primaryRect!!.right = primaryRate!! / 100 * width
            primaryRect!!.bottom = height
            canvas.drawRoundRect(primaryRect, radius, radius, primaryPaint)
        }
    }
}
