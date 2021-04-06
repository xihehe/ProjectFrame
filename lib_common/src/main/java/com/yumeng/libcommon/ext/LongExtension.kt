package com.yumeng.libcommon.ext

import com.yumeng.libcommon.helper.DateFormatHelper


fun Long.formatNewsTime(): String {
    return DateFormatHelper.getTimeFormatText(this)
}

fun Long.formatTime(format: String = "yyyy-MM-dd HH:mm"): String? {
    return DateFormatHelper.formatDate(this, format)
}

fun Long.formatMessageNotice():String{
    return DateFormatHelper.times(this)
}



fun Long?.toDate(format: String = "yyyy-MM-dd\tHH:mm") = DateFormatHelper.formatDate(this, format)
fun Long?.toDateZh(format: String = "yyyy年MM月dd日") = DateFormatHelper.formatDate(this, format)
