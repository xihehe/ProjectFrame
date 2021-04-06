package com.yumeng.libbaseProject.model

import com.google.gson.annotations.SerializedName

data class FeedSheildSetModel(
        @SerializedName("shieldId")
        var shieldId : String?,//true 图片 false 文件
        @SerializedName("shieldType")
        var shieldType: Boolean//文件下载id
)