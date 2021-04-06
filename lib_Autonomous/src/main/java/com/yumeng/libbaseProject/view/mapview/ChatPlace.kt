package com.yumeng.libbaseProject.view.mapview

import java.io.Serializable

class ChatPlace() : Serializable {

    var name: String? = null
    var address: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var locationImg: String? = null
    var cryptoType: Int = 2//加密消息类型
    var fileKey:String?=null

    constructor(name: String, address: String, latitude: Double, longitude: Double) : this() {
        this.name = name
        this.address = address
        this.latitude = latitude
        this.longitude = longitude
    }


}