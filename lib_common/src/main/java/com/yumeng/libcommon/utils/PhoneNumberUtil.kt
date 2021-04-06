package com.yumeng.libcommon.utils

/**
 * Created by Chauncey on 2019/4/17 23:19.
 *格式化 保护手机号
 */
class PhoneNumberUtil {
    companion object {
        fun formatProtectedNumber(number: String) = with(number) {
            val l = length / 3
            val replaceChar = "*"
            var replaceString = ""
            for (i in 0 until length - l * 2) {
                replaceString += replaceChar
            }

            replaceRange(l, length - l - 1, replaceString)
        }

    }
}