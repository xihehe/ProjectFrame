package com.yumeng.libcommon.ext

import android.content.Context
import android.text.SpannableString
import androidx.annotation.ColorInt
import com.yumeng.libcommon.helper.CharSequenceHelper

/**
 * Created by Chauncey on 2018/5/2 14:49.
 * Char 设置 size  color  image顶层
 */
fun CharSequence.setSize(size: Int): CharSequence = CharSequenceHelper.setSize(this, size)

fun CharSequence.setColor(@ColorInt color: Int): SpannableString = CharSequenceHelper.setColor(this, color)

fun CharSequence.setImage(context: Context, res: Int): CharSequence = CharSequenceHelper.setImage(context, this, res)
