package com.yumeng.libbaseProject.service.ResponseModel


import com.google.gson.annotations.SerializedName
import com.yumeng.libbaseProject.dataSupport.AddFriendBean

data class VerifyInfoResponse(
    @SerializedName("pageNo")
    var pageNo: Int?,
    @SerializedName("pages")
    var pages: Int?,
    @SerializedName("rows")
    var rows: ArrayList<AddFriendBean>?,
    @SerializedName("total")
    var total: Int?
)