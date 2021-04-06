package com.yumeng.libbaseProject.application

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.danikula.videocache.HttpProxyCacheServer
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.ios.IosEmojiProvider
import com.yumeng.libbaseProject.model.UserInfo
import com.yumeng.libbaseProject.router.RouterManger
import com.yumeng.libcommon.helper.Preference
import com.yumeng.libcommon.utils.LogUtils
import com.yumeng.libcommon.utils.MyActivityLifeCycleCallbacks
import com.yumeng.libcommonview.theme.Theme
import org.litepal.LitePal.use
import org.litepal.LitePalDB
import skin.support.SkinCompatManager
import skin.support.app.SkinAppCompatViewInflater
import skin.support.app.SkinCardViewInflater
import skin.support.constraint.app.SkinConstraintViewInflater
import skin.support.design.app.SkinMaterialViewInflater
import kotlin.properties.Delegates

open class MyApplication : BaseApplication() {

    companion object {
        var CONTEXT: Context by Delegates.notNull()
        var CURRENT_USER: UserInfo? = null
        var instance: MyApplication? = null
        fun isForeground(): Boolean {
            return MyActivityLifeCycleCallbacks.isApplicationInForeground()
        }

        fun isLogin(context: Context, toDo: () -> Unit) {
            val userInfo by Preference(
                Preference.USER_JSON,
                ""
            )
            Log.e("UserInfo", "${userInfo.toString()}")
            if (!userInfo.isNullOrEmpty()) {
                toDo()
            } else {
                RouterManger.startLoginActivity(context)
            }

        }

        fun isLogin(): Boolean {
            val userInfo by Preference(
                Preference.USER_JSON,
                ""
            )
            return !userInfo.isNullOrEmpty()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        CONTEXT = this
        initSkin()
        registerNetWorkChangedReceiver()
        //        if (CURRENT_USER != null && !existMainActivity())
//            initDataBase(CURRENT_USER?.getId())
        //        notifier = EaseNotifier(this)
        setMessageChannel(0)
        setMuteChannel()
        listenForScreenTurningOff()
        EmojiManager.install(IosEmojiProvider())

    }

    //初始化数据库
    fun initDataBase(dbName: String?) {
        val litePalDB = LitePalDB.fromDefault(dbName)
        use(litePalDB)
    }

    //手机开屏
    private fun listenForScreenTurningOff() {
//        val screenStateFilter = IntentFilter(Intent.ACTION_SCREEN_ON)
//        registerReceiver(object : BroadcastReceiver() {
//            override fun onReceive(context: Context, intent: Intent) {
//                if (intent.action != null && intent.action == Intent.ACTION_SCREEN_ON) {
//                    val activity: Activity = MyApplication.getCurrentActivity()
//                    if (activity !is LoginActivity) {
//                        notifyForeground()
//                    }
//                }
//            }
//        }, screenStateFilter)
    }

    private fun initSkin() {
        Theme.Companion.init(this)
        SkinCompatManager.withoutActivity(this)
            .addInflater(SkinAppCompatViewInflater()) // 基础控件换肤
            .addInflater(SkinMaterialViewInflater()) // material design
            .addInflater(SkinConstraintViewInflater()) // ConstraintLayout
            .addInflater(SkinCardViewInflater()) // CardView v7
            .setSkinStatusBarColorEnable(true) // 关闭状态栏换肤
//                .setSkinWindowBackgroundEnable(false)           // 关闭windowBackground换肤
//                .setSkinAllActivityEnable(false)                // true: 默认所有的Activity都换肤; false: 只有实现SkinCompatSupportable接口的Activity换肤
            .loadSkin()
    }




    //注册网络监听广播
    fun registerNetWorkChangedReceiver() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //实例化IntentFilter对象
//            val filter = IntentFilter()
//            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
//            mPushReceiver = MPushReceiver()
//            //注册广播接收
//            registerReceiver(mPushReceiver, filter)
//        }
    }

    private fun notifyForeground() { // 前台
//        AccountHelper.INSTANCE.reconnection()
    }

    fun bindUser() {
//        if (!TextUtils.isEmpty(CURRENT_USER?.getId())) {
//            MPush.I.bindAccount(CURRENT_USER?.getId(), "mpush:" + (Math.random() * 10).toInt())
//        }
    }

    fun startPush() {
//        userInfo = AppSharePre.getPersonalInfo()
//        if (userInfo != null) {
//            initPush(CURRENT_USER?.getId())
//            MPush.I.checkInit(this).startPush()
//            bindUser()
//        }
    }

    /**
     * mPush相关
     */
    fun initPush(userId: String?) { //公钥有服务端提供和私钥对应
//        val cc: ClientConfig = ClientConfig.build()
//            .setPublicKey(Constants.PUSH_PUBLIC_KEY)
//            .setAllotServer(Constants.MpushUrl)
//            .setDeviceId(getDeviceId())
//            .setClientVersion(BuildConfig.VERSION_NAME)
//            .setEnableHttpProxy(true)
//            .setUserId(userId)
//        MPush.I.checkInit(applicationContext).setClientConfig(cc)
    }

    private fun getDeviceId(): String? {
        val ANDROID_ID = Settings.System.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
        )
        LogUtils.e("TAG_deviceId", ANDROID_ID)
        return ANDROID_ID
    }

    //设置消息渠道 type:0为普通消息渠道 1为rtc渠道
    fun setMessageChannel(type: Int) {
//        val notificationManager =
//            MyApplication.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        if (Build.VERSION.SDK_INT >= 26) { // Create the notification channel for Android 8.0
//            @SuppressLint("WrongConstant") val channel = NotificationChannel(
//                if (type == 0) Constants.CHANNEL_ID else Constants.VIDEO_CHANNEL,
//                if (type == 0) getString(R.string.message_channel) else getString(R.string.video_channel),
//                NotificationManager.IMPORTANCE_HIGH
//            )
//            channel.vibrationPattern = Constants.VIBRATION_PATTERN
//            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
//            if (type == 1) {
//                channel.setSound(
//                    Uri.parse("android.resource://" + this.packageName.toString() + "/" + R.raw.ic_incoming_telegram),
//                    Notification.AUDIO_ATTRIBUTES_DEFAULT
//                )
//            }
//            notificationManager.createNotificationChannel(channel)
//        }
    }

    //设置静音渠道
    fun setMuteChannel() {
//        val notificationManager =
//            MyApplication.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        if (Build.VERSION.SDK_INT >= 26) { // Create the notification channel for Android 8.0
//            @SuppressLint("WrongConstant") val channel = NotificationChannel(
//                Constants.MUTE_CHANNEL_ID
//                , getString(R.string.silent_channel), NotificationManager.IMPORTANCE_LOW
//            )
//            channel.vibrationPattern = Constants.VIBRATION_PATTERN
//            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
//            channel.setSound(null, null)
//            notificationManager.createNotificationChannel(channel)
//        }
    }


    private var proxy: HttpProxyCacheServer? = null

    fun getProxy(context: Context): HttpProxyCacheServer? {
        val app = context.applicationContext as MyApplication
        return if (app.proxy == null) app.newProxy().also { app.proxy = it } else app.proxy
    }

    private fun newProxy(): HttpProxyCacheServer {
        return HttpProxyCacheServer(this)
    }

}