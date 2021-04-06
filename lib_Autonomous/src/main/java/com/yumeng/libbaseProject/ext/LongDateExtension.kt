package com.yumeng.libbaseProject.ext

import com.yumeng.libbaseProject.utils.DataUtils

fun Long.toMessageDate():String{
    return DataUtils.formatTimeBase(this)
}