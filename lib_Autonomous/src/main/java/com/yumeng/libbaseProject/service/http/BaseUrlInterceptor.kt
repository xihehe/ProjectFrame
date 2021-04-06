package com.yumeng.libbaseProject.service.http

import android.util.Log
import com.yumeng.libbaseProject.BuildConfig
import com.yumeng.libbaseProject.service.Service
import com.yumeng.libcore.service.Api
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class BaseUrlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //获取request
        val request = chain.request()
        //从request中获取原有的HttpUrl实例oldHttpUrl
        val oldHttpUrl = request.url()
        //获取request的创建者builder
        val builder = request.newBuilder()
        //从request中获取headers，通过给定的键url_name
        val headerValues:List<String>? = request.headers(Api.URL_NAME_UNIT)
        if (headerValues != null && headerValues.isNotEmpty()) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader(Api.URL_NAME_UNIT)
            //匹配获得新的BaseUrl
            val newBaseUrl = when (headerValues[0]) {
                Service.BASE_DOMAIN_NAME -> {
                    HttpUrl.parse(BuildConfig.BASE_URL)
                }
//                UserService.USER_DOMAIN_NAME -> {
//                    HttpUrl.parse(BuildConfig.BASE_URL)
//                }
                else -> oldHttpUrl
            }

            Log.e("BaseUrlInterceptor","newBaseUrl:-> $newBaseUrl")
            //重建新的HttpUrl，修改需要修改的url部分
            newBaseUrl?.apply {
                val newFullUrl = oldHttpUrl.newBuilder()
                    .scheme(newBaseUrl.scheme())//更换网络协议
                    .host(newBaseUrl.host())//更换主机名
                    .port(newBaseUrl.port())//更换端口
                    .build()
                return chain.proceed(builder.url(newFullUrl).build());
            }

        }
        return chain.proceed(request);

    }
}