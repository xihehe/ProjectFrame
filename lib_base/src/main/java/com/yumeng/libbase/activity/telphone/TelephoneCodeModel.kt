package com.yumeng.libbase.activity.telphone

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TelephoneCodeModel(
    @SerializedName("en")
    var en: String = "", // Zimbabwe
    @SerializedName("name")
    var name: String = "", // 津巴布韦
    @SerializedName("short")
    val short: String = "", // ZW
    @SerializedName("tel")
    val tel: String = ""
) : Parcelable {


    @SuppressLint("DefaultLocale")
    fun getHeadWord(): String {
        return en.subSequence(0, 1).toString().toUpperCase()
    }
}