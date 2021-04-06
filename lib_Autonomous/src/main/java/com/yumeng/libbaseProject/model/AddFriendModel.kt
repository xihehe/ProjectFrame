package com.yumeng.libbaseProject.model

import com.google.gson.annotations.SerializedName

data class AddFriendModel(
        @SerializedName("receiver")
        var `receiver`: String?,
        @SerializedName("remark")
        var remark: String?,
        @SerializedName("source")
        var source: Int?=0
)