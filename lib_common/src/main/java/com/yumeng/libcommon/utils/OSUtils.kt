package com.yumeng.libcommon.utils

import android.app.ActivityManager
import android.app.AppOpsManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import com.yumeng.libcommon.context.AppContextWrapper
import org.whispersystems.libsignal.util.guava.Optional
import java.io.File

object OSUtils {

    /**
     * Get Mobile Type
     * 获取手机类型 utils
     *
     * @return
     */

    const val OPPO = "OPPO"
    const val XIAO_MI = "Xiaomi"
    const val LETV = "Letv"
    const val SAMSUNG = "samsung"
    const val HUAWEI = "HUAWEI"
    const val VIVO = "vivo"
    const val MEIZU = "Meizu"
    const val ULONG = "ulong"
    const val UMENG = "umeng"
    const val FIREBASE = "firebase"

    /**
     * Get Mobile Type
     *
     * @return
     */
    private fun getMobileType(): String {
        return Build.MANUFACTURER
    }


    fun jumpStartInterface(context: Context) {
        var intent = Intent(Intent.ACTION_MAIN)
        val sdkInt = Build.VERSION.SDK_INT
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            Log.e("HLQ_Struggle", "******************当前手机型号为：" + getMobileType())
            var componentName: ComponentName? = null
            if (getMobileType() == "Xiaomi") { // 红米Note4测试通过
                //小米4 6.0  红米5A 7.1.2 小米6 8.0测试通过
                componentName = ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")
            } else if (getMobileType() == "Letv") { // 乐视2测试通过
                //有待验证
                intent.action = "com.letv.android.permissionautoboot"
            } else if (getMobileType() == "samsung") { // 三星Note5测试通过
//                componentName = ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.ram.AutoRunActivity")
                //三星J6 8.1 电池页面 AppSleepListActivity无法跳转，可能需要传参，点击 未监视的应用程序 //跳转成功可以做提示点击未监视的应用程序 手动添加
                componentName = ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")
            } else if (getMobileType() == "HUAWEI") { // 华为测试通过
                componentName = when {
                    sdkInt >= Build.VERSION_CODES.P -> {
                        ComponentName.unflattenFromString("com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity")
                    }
                    sdkInt >= Build.VERSION_CODES.O -> //华为Y6测试通过
                        ComponentName.unflattenFromString("com.huawei.systemmanager/.appcontrol.activity.StartupAppControlActivity")
                    sdkInt >= Build.VERSION_CODES.M -> //华为DIG-TL10 EMUI4.1 Android6.0测试通过
                        ComponentName.unflattenFromString("com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity")
                    else -> //有待测试
                        ComponentName.unflattenFromString("com.huawei.systemmanager/.optimize.process.ProtectActivity")
                }
            } else if (getMobileType() == "vivo") { // 有待验证
                componentName = ComponentName.unflattenFromString("com.iqoo.secure/.safeguard.PurviewTabActivity")
            } else if (getMobileType() == "Meizu") { //万恶的魅族
                // 通过测试，发现魅族是真恶心，也是够了，之前版本还能查看到关于设置自启动这一界面，系统更新之后，完全找不到了，心里默默Fuck！
                // 针对魅族，我们只能通过魅族内置手机管家去设置自启动，所以我在这里直接跳转到魅族内置手机管家界面，具体结果请看图
                componentName = ComponentName.unflattenFromString("com.meizu.safe/.permission.PermissionMainActivity")
            } else if (getMobileType() == "OPPO") { // OPPO R8205测试通过
                //OPPO A83 7.1.1与5.1.1测试通过 耗电保护 也是只能跳转到电池 耗电保护页面无法跳转 PowerUsageModelActivity 可能需要传参
                componentName = ComponentName("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity")
            } else if (getMobileType() == "ulong") { // 360手机 未测试
                componentName = ComponentName("com.yulong.android.coolsafe", ".ui.activity.autorun.AutoRunListActivity")
            } else {
                // 以上只是市面上主流机型，由于公司你懂的，所以很不容易才凑齐以上设备
                // 针对于其他设备，我们只能调整当前系统app查看详情界面
                // 在此根据用户手机当前版本跳转系统设置界面
                if (Build.VERSION.SDK_INT >= 9) {
                    intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                    intent.data = Uri.fromParts("package", context.packageName, null)
                } else if (Build.VERSION.SDK_INT <= 8) {
                    intent.action = Intent.ACTION_VIEW
                    intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails")
                    intent.putExtra("com.android.settings.ApplicationPkgName", context.packageName)
                }
            }
            intent.component = componentName
            context.startActivity(intent)
        } catch (e: Exception) {//抛出异常就直接打开设置页面
            intent = Intent(Settings.ACTION_SETTINGS)
            context.startActivity(intent)
        }


    }


    fun notifyImage(file: File) {
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val uri = Uri.fromFile(file)
        intent.data = uri
        AppContextWrapper.getApplicationContext().sendBroadcast(intent)
    }


    fun isWifiConnect(context: Context): Boolean {
        val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connManager.activeNetworkInfo
        if (activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI) {
            return true
        }
        return false
    }

    fun isServiceRunning(context: Context, serviceName: String?): Boolean {
        if (serviceName.isNullOrEmpty()) {
            return false
        }
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningService = manager.getRunningServices(Int.MAX_VALUE) as ArrayList<ActivityManager.RunningServiceInfo>
        val a = runningService.firstOrNull {
            it.service.className == serviceName
        }
        return a != null

    }

    fun getBrandType(): Int {
        return when (Build.BRAND.toLowerCase()) {
            "huawei" -> 1
            "xiaomi" -> 2
            "meizu" -> 3
            else -> 0
        }
    }


    fun getSimCountryIso(context: Context): Optional<String> {
        val simCountryIso = (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).simCountryIso
        return Optional.fromNullable(simCountryIso?.toUpperCase())
    }


    fun getMetaData(context: Context, name: String): String {
        val appInfo = context.packageManager
            .getApplicationInfo(context.packageName,
                PackageManager.GET_META_DATA)
        val metaData = appInfo.metaData
        val string = metaData.get(name).toString()
        return string.replace("\"", "")
    }


    fun isHomeWin(context: Context): Boolean {
        val mActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val lstRti = mActivityManager.getRunningTasks(1)
        return getHomes(context).contains(lstRti[0].topActivity?.packageName)
    }


    fun isFloatPermission(context: Context): Boolean {
        var isPermission = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isPermission = Settings.canDrawOverlays(context)
            LogUtils.e("isFloatPermission", "型号:${Build.MODEL}\nAndroid:${Build.VERSION.SDK_INT}canDrawOverlays：$isPermission")
        }
        if (!isPermission) {
            val permission = isFloatPermissionFromO(context)
            LogUtils.e("isFloatPermission", "型号:${Build.MODEL}\nAndroid:${Build.VERSION.SDK_INT}isFloatPermissionFromO：$permission")
            if (permission) {
                isPermission = permission
            }
        }
        return isPermission
    }

    private fun isFloatPermissionFromO(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val appOpsMgr = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager?
                ?: return false
            val mode = appOpsMgr.checkOpNoThrow("android:system_alert_window", android.os.Process.myUid(), context
                .packageName)
            LogUtils.e("isFloatPermission", "型号:${Build.MODEL}\nAndroid:${Build.VERSION.SDK_INT}isFloatPermissionFromOModel：$mode")
            return mode == AppOpsManager.MODE_ALLOWED /*|| mode == AppOpsManager.MODE_IGNORED*/;
        }
        return false
    }

    fun isAndroidQ():Boolean{
        return Build.VERSION.SDK_INT >= 29
    }

    private fun getHomes(context: Context): List<String> {
        val packages = ArrayList<String>()
        val packageManager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        val resolveInfo = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        resolveInfo.forEach {
            packages.add(it.activityInfo.packageName)
        }
        return packages
    }



}