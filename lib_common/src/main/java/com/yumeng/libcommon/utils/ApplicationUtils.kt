package com.yumeng.libcommon.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import java.io.File


/**
 * Created by Chauncey on 2018/4/12 10:20.
 */
object ApplicationUtils {

    /**
     * 获取当前app versionName
     */
    fun getAppVersionName(context: Context): String? {
        val pm = context.packageManager
        var pi: PackageInfo? = null
        try {
            pi = pm.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return pi?.versionName
    }

    /**
     * 目标app 是否安装
     */
    fun isTargetAppInstalled(context: Context, packageName: String): Boolean {
        val pm = context.packageManager
        return try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            false
        }
    }

//    fun isTargetAppInstalled(context: Context, packageName: String): Boolean {
//        val packageManager = context.packageManager// 获取packagemanager
//        val packages = packageManager.getInstalledPackages(0)// 获取所有已安装程序的包信息
//        if (packages != null) {
//            for (i in packages.indices) {
//                val pn = packages[i].packageName
//                if (pn == packageName) {
//                    return true
//                }
//            }
//        }
//        return false
//    }

    /**
     * 获取指定Manfest 标签参数
     */
    fun getMetaData(context: Context, name: String): String {
        val appInfo = context.packageManager
            .getApplicationInfo(
                context.packageName,
                PackageManager.GET_META_DATA
            )
        val metaData = appInfo.metaData
        val string = metaData.get(name).toString()
        return string.replace("\"", "")
    }



    //安装应用
    private fun doInstallApk(context: Context, apk: File) {
        val intent = Intent(Intent.ACTION_VIEW)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            intent.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive")
        } else {//Android7.0之后获取uri要用contentProvider
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", apk)
            intent.setDataAndType(uri, "application/vnd.android.package-archive")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}
