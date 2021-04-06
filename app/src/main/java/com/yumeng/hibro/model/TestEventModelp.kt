package com.yumeng.hibro.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class TestEventModelp(
    var nameP: String? = null,
    var ageP: String? = null
) : Parcelable