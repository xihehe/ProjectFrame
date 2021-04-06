package com.yumeng.libcommon.helper

import com.google.gson.Gson

object GsonHelper {


    fun toJson(any: Any): String {
        return Gson().toJson(any)
    }

}