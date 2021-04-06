package com.yumeng.libcore.utils

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

object RequestBodyHelper {

    fun createJsonBody(
        any: Any?,
        contentType: String = "application/json; charset=utf-8"
    ): RequestBody =
        RequestBody.create(MediaType.parse(contentType), Gson().toJson(any))


}