package com.yumeng.libbaseProject.service.ResponseModel


import com.google.gson.annotations.SerializedName

data class LoginStatusResponse(
        @SerializedName("status")
        var status: Int?
)