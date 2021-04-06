package com.yumeng.libcommon.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.text.TextUtils
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class CrashUtils(private val context: Context) {
    private var mInitialized: Boolean = false
    private var crashDir: String? = null

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var mInstance: CrashUtils? = null

        fun getInstance(context: Context): CrashUtils? {
            if (mInstance == null) {
                synchronized(CrashUtils::class.java) {
                    if (mInstance == null) {
                        mInstance = CrashUtils(context)
                    }
                }
            }
            return mInstance
        }
    }


    /**
     * 初始化
     *
     * @return `true`: 成功<br></br>`false`: 失败
     */
    fun init(): Boolean {
        if (mInitialized) return true
        crashDir = if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() && context.externalCacheDir != null) {
            context.externalCacheDir!!.path + File.separator + "crash" + File.separator
        } else {
            context.cacheDir.path + File.separator + "crash" + File.separator
        }
        try {
            val pi = context.packageManager.getPackageInfo(context.packageName, 0)
            versionName = pi.versionName
            versionCode = pi.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return false
        }
        mInitialized = true
        return mInitialized
    }

    fun writeException(throwable: Throwable, offline: Boolean) {
        val type = if (offline) {
            "离线"
        } else {
            "在线"
        }
        val now = SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val fullPath = "$crashDir$now(${type}消息解密失败).txt"
        if (!createOrExistsFile(fullPath)) return
        var pw: PrintWriter? = null
        try {
            pw = PrintWriter(FileWriter(fullPath, false))
            pw.write(getCrashHead())
            throwable.printStackTrace(pw)
            var cause: Throwable? = throwable.cause
            while (cause != null) {
                cause.printStackTrace(pw)
                cause = cause.cause
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            closeIO(pw)
        }
    }

    private val now = SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

    fun writeError(throwable: String) {
//        val fullPath = "$crashDir$now(转圈日志).txt"
//        if (!createOrExistsFile(fullPath)) return
//        var pw: PrintWriter? = null
//        try {
//            pw = PrintWriter(FileWriter(fullPath, true))
//            pw.write("\n"+SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())+"：")
//            pw.write(throwable)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } finally {
//            closeIO(pw)
//        }
    }

    fun writeMessage(throwable: String) {
        val fullPath = "$crashDir$now(测试群加密漏消息).txt"
        if (!createOrExistsFile(fullPath)) return
        var pw: PrintWriter? = null
        try {
            pw = PrintWriter(FileWriter(fullPath, true))
            pw.write("\n"+SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())+"：")
            pw.write(throwable)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            closeIO(pw)
        }
    }
    fun writeOnlineMessage(throwable: String) {
//        val fullPath = "$crashDir$now(在线消息).txt"
//        if (!createOrExistsFile(fullPath)) return
//        var pw: PrintWriter? = null
//        try {
//            pw = PrintWriter(FileWriter(fullPath, true))
//            pw.write("\n"+SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())+"：")
//            pw.write(throwable)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } finally {
//            closeIO(pw)
//        }
    }

    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    private fun closeIO(vararg closeables: Closeable?) {
        if (closeables == null) return
        try {
            for (closeable in closeables) {
                closeable?.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


    private var versionName: String? = null
    private var versionCode: Int = 0
    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    private fun getFileByPath(filePath: String): File? {
        return if (TextUtils.isEmpty(filePath)) null else File(filePath)
    }


    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param filePath 文件路径
     * @return `true`: 存在或创建成功<br></br>`false`: 不存在或创建失败
     */
    private fun createOrExistsFile(filePath: String): Boolean {
        return createOrExistsFile(getFileByPath(filePath))
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return `true`: 存在或创建成功<br></br>`false`: 不存在或创建失败
     */
    private fun createOrExistsFile(file: File?): Boolean {
        if (file == null) return false
        // 如果存在，是文件则返回true，是目录则返回false
        if (file.exists()) return file.isFile
        if (!createOrExistsDir(file.parentFile)) return false
        return try {
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }

    }


    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return `true`: 存在或创建成功<br></br>`false`: 不存在或创建失败
     */
    fun createOrExistsDir(file: File?): Boolean {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && if (file.exists()) file.isDirectory else file.mkdirs()
    }


    /**
     * 获取崩溃头
     *
     * @return 崩溃头
     */
    private fun getCrashHead(): String {
        return "\n************* Crash Log Head ****************" +
                "\nDevice Manufacturer: " + Build.MANUFACTURER +// 设备厂商

                "\nDevice Model       : " + Build.MODEL +// 设备型号

                "\nAndroid Version    : " + Build.VERSION.RELEASE +// 系统版本

                "\nAndroid SDK        : " + Build.VERSION.SDK_INT +// SDK版本

                "\nApp VersionName    : " + versionName +
                "\nApp VersionResponse    : " + versionCode +
                "\n************* Crash Log Head ****************\n\n"
    }
}