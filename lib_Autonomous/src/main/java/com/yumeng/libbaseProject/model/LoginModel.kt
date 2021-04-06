package com.yumeng.libbaseProject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("smsCode")
    val smsCode: String,
    @SerializedName("dialCode")
    val dialCode: String) : Parcelable