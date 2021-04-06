package com.yumeng.libcommon.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object PermissionHelper {

    fun checkPermissions(mContexts: Context, items: Array<String>): Boolean {
        items.forEach {
            if (checkPermission(mContexts, it)) {
                return true
            }
        }
        return false
    }
    /**
     * 判断是否缺少权限
     */
    private fun checkPermission(mContexts: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            mContexts,
            permission
        ) == PackageManager.PERMISSION_DENIED

    }
}