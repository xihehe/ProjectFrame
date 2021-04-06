package com.yumeng.libcommon.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.yumeng.libcommon.context.AppContextWrapper;


/**
 * Created by Huangjinfu on 2016/8/6.
 */
public class ToastUtils {
    private static ToastUtils mToastUtils;
    private static Context mContext;

    private ToastUtils() {
    }

    public static ToastUtils getInstance() {
        if (mToastUtils == null)
            mToastUtils = new ToastUtils();
        mContext = AppContextWrapper.Companion.getApplicationContext();
        return mToastUtils;
    }

    //shortToast
    public void shortToast(String str) {
        if(!TextUtils.isEmpty(str)){
            if(!str.startsWith("token")){
                Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void shortToastOffset(String str){
        if(!TextUtils.isEmpty(str)){
            Toast toast=Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM,0,ScreenUtils.getScreenHeightPoint(mContext)/3);
            toast.show();
        }
    }

    public void longToastOffset(String str){
        if(!TextUtils.isEmpty(str)){
            Toast toast=Toast.makeText(mContext, str, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM,0,ScreenUtils.getScreenHeightPoint(mContext)/3);
            toast.show();
        }
    }

    //longToast
    public void longToast(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
    }
}
