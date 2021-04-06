package com.yumeng.libbaseProject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SmsModel(
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dialCode")
    val dialCode: String) : Parcelable