package com.yumeng.libbaseProject.service.ResponseModel

import com.google.gson.annotations.SerializedName

data class AddFriendResponse(
        @SerializedName("blacklistFlag")
        var blacklistFlag: String?,
        @SerializedName("delFlag")
        var delFlag: String?,
        @SerializedName("enctryptRoomId")
        var enctryptRoomId: String?,
        @SerializedName("friendId")
        var friendId: String?,
        @SerializedName("id")
        var id: String?,
        @SerializedName("roomId")
        var roomId: String?,
        @SerializedName("userId")
        var userId: String?,
        @SerializedName("openEndToEndEncrypt")
        var openEndToEndEncrypt: Boolean?
)