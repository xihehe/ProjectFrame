package com.yumeng.libbaseProject.model

import com.google.gson.annotations.SerializedName

data class FeedbackModel(
    @SerializedName("content")
    val content:String
)