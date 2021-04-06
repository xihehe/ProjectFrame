package com.yumeng.libcore.response

import com.google.gson.annotations.SerializedName

/**
 * 网络请求 带 list的  BaseListHttpResponse
 */
open class BaseResponseDataList<T>{
        @SerializedName("current")
        val current: Int = 1
        @SerializedName("pages")
        val pages: Int = 1
        @SerializedName("records")
        val records: ArrayList<T> = arrayListOf()
        @SerializedName("searchCount")
        val searchCount: Boolean = false
        @SerializedName("size")
        val size: Int = 10
        @SerializedName("total")
        val total: Int = 0
}