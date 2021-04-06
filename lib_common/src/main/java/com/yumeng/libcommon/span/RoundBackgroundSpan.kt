package com.yumeng.libcommon.span

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan

/**
 * Created by Chauncey on 2018/7/13 16:36.
 * 显示带圆角背景的 Tag标签
 */
class RoundBackgroundSpan private constructor(private val bgColor: Int,
                                              private val strokeColor: Int,
                                              private val textColor: Int,
                                              private val textsSize: Float,
                                              private val radius: Float,
                                              private val paddingStart: Float,
                                              private val paddingTop: Float,
                                              private val paddingEnd: Float,
                                              private val paddingBottom: Float) : ReplacementSpan() {

    companion object {
        private const val roundMarginStart = 2//不加这个会导致前部分弧形绘制不全
        private const val space = 10
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        paint.isAntiAlias = true
        val fm = paint.fontMetrics

        val textTop = top + (fm.ascent - fm.top)
        val textBottom = textTop + (fm.descent - fm.ascent)

        val roundTextPaint = Paint()
        roundTextPaint.textSize = textsSize
        roundTextPaint.isAntiAlias = true

        val rect = RectF(x + roundMarginStart, textTop - paddingTop,
                x + measureText(roundTextPaint, text, start, end) + paddingStart + paddingEnd, textBottom + paddingBottom)
        roundTextPaint.style = Paint.Style.FILL
        roundTextPaint.color = this.bgColor
        canvas.drawRoundRect(rect, radius, radius, roundTextPaint)

        roundTextPaint.style = Paint.Style.STROKE
        roundTextPaint.color = strokeColor
        roundTextPaint.strokeWidth = 2F
        canvas.drawRoundRect(rect, radius, radius, roundTextPaint)

        roundTextPaint.style = Paint.Style.FILL
        roundTextPaint.color = textColor
        roundTextPaint.strokeWidth = 1F
        canvas.drawText(text, start, end, x + roundMarginStart + paddingStart,  (textBottom - textTop) / 2 - (fm.ascent+fm.descent)/2, roundTextPaint)
    }

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        val p = Paint()
        p.textSize = textsSize
        return (Math.round(p.measureText(text, start, end)) + paddingStart + paddingEnd).toInt() + roundMarginStart + space
    }

    private fun measureText(paint: Paint, text: CharSequence, start: Int, end: Int): Float {
        return paint.measureText(text, start, end)
    }

    class Builder {
        private var bgColor: Int = Color.WHITE
        private var strokeColor: Int = Color.GRAY
        private var textColor: Int = Color.BLACK
        private var radius: Float = 0F
        private var textsSize: Float = 24F
        private var paddingStart = 0F
        private var paddingEnd = 0F
        private var paddingTop = 0F
        private var paddingBottom = 0F

        fun setBackgroundColor(bgColor: Int): Builder {
            this.bgColor = bgColor
            return this
        }

        fun setStrokeColor(strokeColor: Int): Builder {
            this.strokeColor = strokeColor
            return this
        }

        fun setTextColor(textColor: Int): Builder {
            this.textColor = textColor
            return this
        }

        fun setRadius(radius: Float): Builder {
            this.radius = radius
            return this
        }

        fun setTextSize(textsSize: Float): Builder {
            this.textsSize = textsSize
            return this
        }

        fun setPaddingStart(paddingStart: Float): Builder {
            this.paddingStart = paddingStart
            return this
        }

        fun setPaddingEnd(paddingEnd: Float): Builder {
            this.paddingEnd = paddingEnd
            return this
        }

        fun setPaddingTop(paddingTop: Float): Builder {
            this.paddingTop = paddingTop
            return this
        }

        fun setPaddingBottom(paddingBottom: Float): Builder {
            this.paddingBottom = paddingBottom
            return this
        }

        fun build() = RoundBackgroundSpan(bgColor, strokeColor, textColor, textsSize, radius,
                paddingStart, paddingTop, paddingEnd, paddingBottom)
    }
}
