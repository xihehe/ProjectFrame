package com.yumeng.libcore.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetWorkUtils {

    companion object {
        fun isWiFi(context: Context): Boolean {
            val info = getActiveNetworkInfo(context)
            return info != null && info.isAvailable && info.type == 1
        }

        @SuppressLint("MissingPermission")
        fun isNetworkAvailable(context: Context): Boolean {
            val info = getActiveNetworkInfo(context)
            return !(null == info || !info.isAvailable)
        }

        @SuppressLint("MissingPermission")
        private fun getActiveNetworkInfo(context: Context): NetworkInfo? {
            return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        }

        fun checkNet(context: Context): Boolean {
            val mobileConnection = isMobileConnection(context)
            val wifiConnection = isWIFIConnection(context)
            return mobileConnection || wifiConnection
        }

        fun isMobileConnection(context: Context): Boolean {
            val manager =
                context.getSystemService( Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = manager.getNetworkInfo(0)
            return networkInfo != null && networkInfo.isConnected
        }

        fun isWIFIConnection(context: Context): Boolean {
            val manager =
                context.getSystemService( Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = manager.getNetworkInfo(1)
            return networkInfo != null && networkInfo.isConnected
        }


    }
}