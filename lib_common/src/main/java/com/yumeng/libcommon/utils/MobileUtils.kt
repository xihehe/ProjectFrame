package com.yumeng.libcommon.utils

import android.os.Build
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*


/**
 * Created by Chauncey on 2018/6/26 11:14.
 *
 */
object MobileUtils {
    const val SYS_EMUI = "sys_emui"
    const val SYS_MIUI = "sys_miui"
    const val SYS_FLYME = "sys_flyme"
    const val SYS_OPPO = "sys_oppo"
    private const val KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.status"
    private const val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
    private const val KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage"
    private const val KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level"
    private const val KEY_EMUI_VERSION = "ro.build.version.emui"
    private const val KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion"

    fun getSystem(): String? {
        return try {
            val prop = Properties()
            prop.load(FileInputStream(File(Environment.getRootDirectory(), "build.prop")))
            when {
                prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                        || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                        || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null -> SYS_MIUI//小米

                prop.getProperty(KEY_EMUI_API_LEVEL, null) != null
                        || prop.getProperty(KEY_EMUI_VERSION, null) != null
                        || prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null -> SYS_EMUI//华为

                getMeizuFlymeOSFlag().toLowerCase().contains("flyme") -> SYS_FLYME//魅族

                Build.MANUFACTURER.equals("OPPO", true) -> SYS_OPPO //OPPO
                else -> null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun getMeizuFlymeOSFlag(): String {
        return getSystemProperty("ro.build.display.id", "")
    }

    private fun getSystemProperty(key: String, defaultValue: String): String {
        try {
            val clz = Class.forName("android.os.SystemProperties")
            val get = clz.getMethod("get", String::class.java, String::class.java)
            return get.invoke(clz, key, defaultValue) as String
        } catch (e: Exception) {
        }

        return defaultValue
    }
}