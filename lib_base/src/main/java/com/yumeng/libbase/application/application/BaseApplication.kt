package com.yumeng.libbaseProject.application

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.work.Configuration
import androidx.work.WorkManager
import com.sankuai.waimai.router.Router
import com.sankuai.waimai.router.common.DefaultRootUriHandler
import com.sankuai.waimai.router.components.DefaultLogger
import com.sankuai.waimai.router.core.Debugger
import com.yumeng.libcommon.context.AppContextWrapper
import com.yumeng.libcommon.manage.ActivityManage
import com.yumeng.libcommon.rxManager.RxActivityResult
import com.yumeng.libcommon.utils.CrashUtils
import com.yumeng.libcommon.utils.MyActivityLifeCycleCallbacks
import com.yumeng.libcommon.utils.RxCrashUtils
import com.yumeng.libcommon.utils.language.MultiLanguageUtil
import com.yumeng.libcommon.workers.JobManager
import org.litepal.BuildConfig
import org.litepal.LitePal

open class BaseApplication : Application() {

    private var myActivityLifeCycleCallbacks: MyActivityLifeCycleCallbacks? = null
    private var jobManager: JobManager? = null

    override fun onCreate() {
        super.onCreate()
        initializeJobManager()
        registerActivityLifeCallback()
        RxActivityResult.register(this)
        LitePal.initialize(this)
        RxCrashUtils.getInstance(this).init()
        CrashUtils.getInstance(this)?.init()

        initRouter()
        MultiLanguageUtil.init(this)


    }


    private fun initRouter() {

        if (BuildConfig.DEBUG) {
            // 自定义Logger
            val logger = object : DefaultLogger() {
                override fun handleError(t: Throwable) {
                    super.handleError(t)
                    Log.d("TAG", "router error" + t.message)
                }
            }
            // 设置Logger
            Debugger.setLogger(logger)

            // Log开关，建议测试环境下开启，方便排查问题。
            Debugger.setEnableLog(true)

            // 调试开关，建议测试环境下开启。调试模式下，严重问题直接抛异常，及时暴漏出来。
            Debugger.setEnableDebug(true)
        }

        // 创建RootHandler
        val rootHandler = DefaultRootUriHandler(this)

        // 初始化
        Router.init(rootHandler)

    }

    //    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(AppLanguageUtils.attachBaseContext(base, AppLanguageUtils.getSupportLanguage(SharedDataTool.getString(base, Constants.LANGUAGE_TYPE, "other"))));
//    }
    /**
     * 描述：注册界面的生命周期，也可以
     * 时间：2017/5/11
     */
    private fun registerActivityLifeCallback() {
        registerActivityLifecycleCallbacks(object : MyActivityLifeCycleCallbacks() {
            override fun onActivityCreated(
                activity: Activity?,
                savedInstanceState: Bundle?
            ) {
                ActivityManage.addActivity(activity!!)
            }

            override fun onActivityStarted(activity: Activity?) {
                super.onActivityStarted(activity)
            }

            override fun onActivityDestroyed(activity: Activity?) {
                ActivityManage.removeActivity(activity!!)
            }
        }.also { myActivityLifeCycleCallbacks = it })
    }

    private fun initializeJobManager() {
        WorkManager.initialize(
            this, Configuration.Builder()
                .setMinimumLoggingLevel(Log.INFO)
                .build()
        )
        this.jobManager = JobManager(this, WorkManager.getInstance())
    }

    fun getJobManager(): JobManager? {
        return jobManager
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(AppContextWrapper.init(base))
    }

    override fun getBaseContext(): Context {
        return AppContextWrapper.getBaseContext()
    }

    override fun getApplicationContext(): Context {
        return AppContextWrapper.getApplicationContext()
    }

}