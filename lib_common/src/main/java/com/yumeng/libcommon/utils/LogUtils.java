package com.yumeng.libcommon.utils;

import android.text.TextUtils;
import android.util.Log;

import com.yumeng.libcommon.BuildConfig;


public final class LogUtils {
    private static boolean DEBUG = BuildConfig.DEBUG;
    public static void v(String tag, String msg){
        logger("v",tag,msg);
    }

    public static void d(String tag, String msg){
        logger("d",tag,msg);
    }

    public static void i(String tag, String msg){
        logger("i",tag,msg);
    }

    public static void w(String tag, String msg){
        logger("w",tag,msg);
    }

    public static void e(String tag, String msg){
        logger("e",tag,msg);
    }

    private static void logger(String priority, String tag, String msg){
        if (!DEBUG){
            return;
        }
        if(TextUtils.isEmpty(msg)){
            return;
        }
        switch (priority){
            case "v":
                Log.v(tag,msg);
                break;
            case "d":
                Log.d(tag,msg);
                break;
            case "i":
                Log.i(tag,msg);
                break;
            case "w":
                Log.w(tag,msg);
                break;
            default:
                Log.e(tag,msg);
        }
    }
}