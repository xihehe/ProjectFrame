package com.yumeng.libcommon.utils

import java.text.DecimalFormat

/**
 * Created by Chauncey on 2018/5/25 12:55.
 *
 */
object Formatter {

    fun formatFileSize(size: Long?): String =
            if (size == null || size == 0L) {
                "0.00B"
            } else {
                val decimalFormat = DecimalFormat("#.##")
                val pair = formatFileSize(size.toFloat(), 0)
                val unit = when (pair.second) {
                    0 -> "B"
                    1 -> "KB"
                    2 -> "MB"
                    3 -> "GB"
                    4 -> "TB"
                    else -> {
                        "PB"
                    }
                }
                "${decimalFormat.format(pair.first)}$unit"
            }


    private fun formatFileSize(size: Float, times: Int): Pair<Float, Int> {
        return if (size >= 1024) {
            formatFileSize(size / 1024, times + 1)
        } else {
            Pair(size, times)
        }
    }

    fun formatDoubleToString(num: Double?): String {
        return DecimalFormat("0.00").format(num)
    }
}
