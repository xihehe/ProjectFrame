package com.yumeng.libbaseProject.model

import com.google.gson.annotations.SerializedName

data class ReportModel(
        @SerializedName("complaint")
        var complaint: String?,
        @SerializedName("targetId")
        var targetId: String?,
        @SerializedName("type")
        var type: Int?
)