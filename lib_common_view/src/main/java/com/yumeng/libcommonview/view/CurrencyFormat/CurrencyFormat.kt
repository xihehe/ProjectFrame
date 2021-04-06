package com.yumeng.libcommonview.view.CurrencyFormat

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import java.text.NumberFormat
import java.util.*

/**
 * Created by Chauncey on 2018/1/28 17:11.
 * 货币格式
 */

class CurrencyFormat(private val symbolSize: Int, private val integerSize: Int, private val floatSize: Int) {

    fun formatPrice(price: String): CharSequence {

        val currencyInstance = NumberFormat.getCurrencyInstance(Locale.CHINA)
        val float = price.toFloatOrNull()
        val priceFormat = currencyInstance.format(if (price.toFloatOrNull() == null) {
            0F
        } else {
            float!!
        })

        val priceStringBuilder = SpannableStringBuilder(priceFormat.replace(Regex("￥"), "¥"))
        priceStringBuilder.insert(1, " ")
        val symbolAbsoluteSizeSpan: AbsoluteSizeSpan = AbsoluteSizeSpan(symbolSize)
        val integerAbsoluteSizeSpan: AbsoluteSizeSpan = AbsoluteSizeSpan(integerSize)
        val floatAbsoluteSizeSpan: AbsoluteSizeSpan = AbsoluteSizeSpan(floatSize)

        priceStringBuilder.setSpan(symbolAbsoluteSizeSpan, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        priceStringBuilder.setSpan(integerAbsoluteSizeSpan, 2, priceStringBuilder.indexOf("."), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        priceStringBuilder.setSpan(floatAbsoluteSizeSpan, priceStringBuilder.indexOf("."), priceStringBuilder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return priceStringBuilder
    }
}
