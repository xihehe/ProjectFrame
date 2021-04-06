package com.yumeng.libcommonview.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.yumeng.libcommon.helper.DensityHelper
import com.yumeng.libcommonview.R
import com.yumeng.libcommonview.theme.Theme


open class ThemeEditText : EditText, View.OnFocusChangeListener {
    constructor(context: Context) : this(context,null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    var follow=true


    override fun onFocusChange(p0: View?, hasFocus: Boolean) {
        if(!follow){
            return
        }
        if(drawable==null){
            return
        }
        if (hasFocus) {
            drawable?.setStroke(dp1, Theme.getThemeColor())
        } else {
            when {
                onCheckInputListener?.checkInput(this, text.toString().trim())==true -> drawable?.setStroke(dp0_5, ContextCompat.getColor(context, R.color.theme_color_green))
                onCheckInputListener == null -> drawable?.setStroke(dp0_5,ContextCompat.getColor(context, R.color.color_DDD))
                else -> drawable?.setStroke(dp0_5,ContextCompat.getColor(context, R.color.badge_bg))
            }
        }

    }

    fun setOnCheckInputListener(onCheckInputListener: OnCheckInputListener) {
        this.onCheckInputListener = onCheckInputListener
    }

    private var onCheckInputListener: OnCheckInputListener? = null

    /**
     * 检测输入是否符合要求的回调
     */
    interface OnCheckInputListener {
        /**
         * 检测输入的方法
         *
         * @param v   点击的view
         * @param str 输入的字符串
         * @return 检测成功返回true, 检测失败返回false
         */
        fun checkInput(v: View, str: String): Boolean
    }


    private val dp1 by lazy {
        DensityHelper.dp2px(context,1F)
    }

    private val dp0_5 by lazy {
        DensityHelper.dp2px(context,0.5F)
    }

    private var drawable: GradientDrawable? = null

    init {
        if(background!=null){
            val layerDrawable = background as LayerDrawable
            drawable = layerDrawable.findDrawableByLayerId(R.id.shape) as GradientDrawable?
        }
        onFocusChangeListener = this
    }

}