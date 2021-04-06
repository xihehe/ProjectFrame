package com.yumeng.libcommon.event

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.StringDef
import java.io.Serializable
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by Chauncey on 2019/1/8 10:53.
 */
open class MessageEvent {
    var event: String? = null
        private set//但它的设值方法只在 example.kt 文件内可以访问

    private var mBundle: Bundle? = null



    fun <T> putValue(key: String?, value: T) {
        when (value) {
            is Int -> bundle.putInt(key, value)
            is Long -> bundle.putLong(key, value)
            is Double -> bundle.putDouble(key, value)
            is Float -> bundle.putFloat(key, value)
            is String -> bundle.putString(key, value)
            is Boolean -> bundle.putBoolean(key, value)
            is Serializable -> bundle.putSerializable(key, value)
            is Parcelable -> bundle.putParcelable(key, value)

        }
    }

    fun <T> getData(key: String?, default: T?): T {
        var res: Any = when (default) {
            is Long -> bundle.getLong(key, default)
            is String -> bundle.getString(key, default)
            is Int -> bundle.getInt(key, default)
            is Boolean -> bundle.getBoolean(key, default)
            is Double -> bundle.getDouble(key, default)
            is Float -> bundle.getFloat(key, default)
            is Parcelable -> bundle.getParcelable(key)
            else -> ""
        }
        if(res==""){
            if(default is Serializable){
                res =  bundle.getSerializable(key)
            }else{
                res = ""
            }
        }
        return res as T
    }


    fun checkKey(key: String): Boolean {
        return bundle.containsKey(key)
    }

    constructor() {}
    constructor(@Event event: String?) {
        this.event = event
    }

    @StringDef(
        ON_LOGIN_SUCCESS,
        ON_LOGOUT,
        ON_COUNTRY_CODE_CHANGED,
        ON_USER_INFO_CHANGED,
        ON_INFO_FOLLOW_CHANGE
    )
    @Retention(RetentionPolicy.SOURCE)
    private annotation class Event

    private val bundle: Bundle
        private get() {
            if (mBundle == null) {
                mBundle = Bundle()
            }
            return mBundle!!
        }

    var status: Int
        set(value) {
            bundle.putInt(STATUS_KEY, value)
        }
        get() = bundle.getInt(STATUS_KEY, 0)

    var orderId: String
        set(value) {
            bundle.putString(ORDER_ID_KEY, value)
        }
        get() = bundle.getString(ORDER_ID_KEY)


    var message: String
        set(value) {
            bundle.putString(MESSAGE_KEY, value)
        }
        get() = bundle.getString(MESSAGE_KEY)


    companion object {
        const val DEFUALT_TIME: Long = 1000 //默认等待发送时间
        //登录
        const val ON_LOGIN_SUCCESS = "on_login_success"
        const val ON_LOGOUT = "on_logout"
        const val ON_COUNTRY_CODE_CHANGED = "on_country_code_changed"
        const val ON_USER_INFO_CHANGED = "on_user_info_changed"
        const val ON_INFO_FOLLOW_CHANGE = "on_info_follow_change"
        //其他
        private const val ORDER_ID_KEY = "order_id"
        private const val STATUS_KEY = "status"
        private const val MESSAGE_KEY = "message"
    }
}