package com.yumeng.libcore.response

class BaseResponse<out T>(val status: Int, val message: String, val data: T)