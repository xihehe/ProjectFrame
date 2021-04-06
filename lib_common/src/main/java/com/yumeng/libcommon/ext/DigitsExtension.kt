package com.yumeng.libcommon.ext

import com.yumeng.libcommon.utils.DigitsUtils

/**
 * Created by Chauncey on 2018/8/2 14:43.
 *  String转 int float double long 顶层
 */
fun String?.toIntExact(defaultValue: Int = 0): Int = DigitsUtils.toInt(this, defaultValue)

fun String?.toFloatExact(defaultValue: Float = 0.0F): Float =
    DigitsUtils.toFloat(this, defaultValue)

fun String?.toDoubleExact(defaultValue: Double = 0.0): Double =
    DigitsUtils.toDouble(this, defaultValue)

fun String.toLongExact(defaultValue: Long) = DigitsUtils.toLong(this, defaultValue)