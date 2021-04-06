package com.yumeng.libbase.helper

object DataHelper {

    fun isMatch(original: String, content: String): Boolean {
        return original.contains(content) || original.startsWith(content) || original.endsWith(
            content
        )
    }

}