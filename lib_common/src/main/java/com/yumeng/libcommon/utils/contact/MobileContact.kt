package com.yumeng.libcommon.utils.contact

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileContact(
    var name: String,
    var phoneNumber: String,
    var contactId: String,
    var pinYin: String,
    var headerWord: String,
    var photo: Bitmap
) : Parcelable {
    @IgnoredOnParcel
    var isSelected: Boolean = false
}
