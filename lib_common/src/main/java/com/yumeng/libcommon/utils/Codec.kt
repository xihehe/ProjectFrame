package com.yumeng.libcommon.utils

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder

/**
 * Created by Chauncey on 2018/7/25 16:11.
 * 编解码器
 */
object Codec {
    /**
     * 字符串换成UTF-8
     *
     * @param str
     * @return
     */
    fun unicodeToUtf8(str: String?): String? {
        var result: String? = null
        try {
            result = URLEncoder.encode(str, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return result
    }


    /**
     * utf-8换成字符串
     *
     * @param str
     * @return
     */
    fun utf8ToUnicode(str: String?): String? {
        var result: String? = null
        try {
            result = URLDecoder.decode(str, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return result
    }

    fun utf8ToBase64(str: String?): String? = Base64.encodeToString(str?.toByteArray(), Base64.DEFAULT)

    fun base64ToUtf8(encodedString: String?) = try {
        String(Base64.decode(encodedString, Base64.DEFAULT))
    } catch (e: IllegalArgumentException) {
        null
    }
}
