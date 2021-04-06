package com.yumeng.libbaseProject.service.http

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.yumeng.libbaseProject.BuildConfig
import com.yumeng.libbaseProject.Contants.Constants
import com.yumeng.libbaseProject.application.MyApplication
import com.yumeng.libbaseProject.model.UserInfo
import com.yumeng.libbaseProject.service.Service
import com.yumeng.libcommon.context.AppContextWrapper
import com.yumeng.libcore.utils.NetWorkUtils
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import java.io.File

object Net : BaseRetrofitClient() {

    val service by lazy {
        getService(Service::class.java)
    }

    private val cookieJar by lazy {
        PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(AppContextWrapper.getApplicationContext())
        )
    }

    private val userInfo: UserInfo? get() = MyApplication.CURRENT_USER

    override fun handleBuilder(builder: OkHttpClient.Builder) {
        val httpCacheDirectory =
            File(AppContextWrapper.getApplicationContext().cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)

        builder.cache(cache)
            .cookieJar(cookieJar)
            .addInterceptor { chain ->
                var request = chain.request()

                if (!NetWorkUtils.isNetworkAvailable(AppContextWrapper.getApplicationContext())) {
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }
                val authorizedUrlBuilder = request.url()
                    .newBuilder()
                    .scheme(request.url().scheme())
                    .host(request.url().host())
                val build = request.newBuilder()
                    .method(request.method(), request.body())
                    .url(authorizedUrlBuilder.build())
//                    .addHeader("platform", "1")
//                    .addHeader("deviceId", "a855cb900e112b06")
                    .addHeader("versionNo", BuildConfig.VERSION_NAME)
                    .addHeader("versionCode", BuildConfig.VERSION_CODE.toString())
//                    .addHeader(Constants.LANGUAGE_TYPE, "zh_simple")
                userInfo?.token?.let {
                    build.addHeader(Constants.AUTHORIZATION, it)
                }
                val response = chain.proceed(build.build())
                if (!NetWorkUtils.isNetworkAvailable(AppContextWrapper.getApplicationContext())) {
                    val maxAge = 60 * 60
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                } else {
                    val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }


                response
            }
    }


}