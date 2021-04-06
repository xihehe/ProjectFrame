package com.yumeng.libcommon.utils

/**
 * Created by Chauncey on 2018/8/2 14:41.
 * String 转 int float Double  long 格式 Utils
 */
object DigitsUtils {
    fun toInt(string: String?, defaultValue: Int = 0): Int = try {
        string?.toInt() ?: defaultValue
    } catch (e: NumberFormatException) {
        defaultValue
    }

    fun toFloat(string: String?, defaultValue: Float = 0.0F) = try {
        string?.toFloat() ?: defaultValue
    } catch (e: NumberFormatException) {
        defaultValue
    }

    fun toDouble(string: String?, defaultValue: Double = 0.0) = try {
        string?.toDouble() ?: defaultValue
    } catch (e: NumberFormatException) {
        defaultValue
    }

    fun toLong(string: String, defaultValue: Long) = try {
        string.toLong()
    } catch (e: NumberFormatException) {
        defaultValue
    }
}
