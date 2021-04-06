package com.yumeng.libcommon.ext

import com.yumeng.libcommon.helper.Preference

fun String.putData(key: String) {
    Preference<String>().putValue(key, this)
}

fun Int.putData(key: String) {
    Preference<Int>().putValue(key, this)
}

fun Double.putData(key: String) {
    Preference<Double>().putValue(key, this)
}

fun Long.putData(key: String) {
    Preference<Long>().putValue(key, this)
}

fun Float.putData(key: String) {
    Preference<Float>().putValue(key, this)
}

fun Boolean.putData(key: String) {
    Preference<Boolean>().putValue(key, this)
}

//不建议用吧这个
fun Any.putData(key: String) {
    Preference<Any>().putValue(key, this)
}

