package com.yumeng.libbaseProject.type

enum class AddFriendType(var type: Int, var nameType: String) {
    Mobile(0, "手机号"),
    UID(1, "ID"),
    Group(2, "群聊"),
    QRCode(3, "二维码"),
    Card(4, "名片")
}