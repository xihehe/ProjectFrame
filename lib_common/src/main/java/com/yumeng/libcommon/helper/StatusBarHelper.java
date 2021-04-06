package com.yumeng.libcommon.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Author:45216
 * Date:2018/4/25 15:51
 * Description:状态栏帮助类
 */
public class StatusBarHelper {
    /**
     * 需要MIUI V6以上
     *
     * @param darkMode 是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean setMIUIStatusBarDarkMode(Activity activity, boolean darkMode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag;
            @SuppressLint("PrivateApi") Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkMode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 设置OPPO手机状态栏字体为黑色(colorOS3.0,6.0以下部分手机)
     */
    private static final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010;

    private static boolean setOPPOStatusTextColor(Activity activity, boolean lightStatusBar) {
        boolean b;
        if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int vis = window.getDecorView().getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (lightStatusBar) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (lightStatusBar) {
                    vis |= SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
                } else {
                    vis &= ~SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
                }
            }
            window.getDecorView().setSystemUiVisibility(vis);
            b = true;
        } else {
            b = false;
        }
        return b;
    }


    /**
     * 设置状态栏图标为深色和魅族特定的文字风格，Flyme4.0以上
     *
     * @param activity 需要设置的窗口
     * @param dark     是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean setMeiZuStatusBarDarkIcon(@NonNull Activity activity, boolean dark) {
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field MeiZuFlags = WindowManager.LayoutParams.class
                    .getDeclaredField("MeiZuFlags");
            darkFlag.setAccessible(true);
            MeiZuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = MeiZuFlags.getInt(lp);
            if (dark) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            MeiZuFlags.setInt(lp, value);
            activity.getWindow().setAttributes(lp);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public static void setStatusBarDarkIcon(Activity activity, boolean darkMode) {
        if (setNativeStatusBarDarkIcon(activity, darkMode) ||
                setMIUIStatusBarDarkMode(activity, darkMode) ||
                setMeiZuStatusBarDarkIcon(activity, darkMode) ||
                setOPPOStatusTextColor(activity, darkMode)) {
            return;
        }
        setNativeStatusBarDarkBackground(activity, darkMode);
    }
    /**
     * 设置原生状态栏为黑色字体
     *
     * @return 是否成功
     */
    private static boolean setNativeStatusBarDarkIcon(Activity activity, boolean darkMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = activity.getWindow().getDecorView();
            int visibility = view.getSystemUiVisibility();
            view.setSystemUiVisibility(darkMode ?
                    visibility | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR :
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            return true;
        }
        return false;
    }

    /**
     * 创建一个与状态看一样高度的view
     *
     * @return 返回view
     */
    public static View createStatusBarView(Context context) {
        // 绘制一个和状态栏一样高的矩形
        TextView statusBarView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(context));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(Color.parseColor("#3fbe99"));
        return statusBarView;
    }


    /**
     * 无法设置背景文字为黑色时，将背景设置为黑色半透明
     */
    private static void setNativeStatusBarDarkBackground(Activity activity, boolean darkMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Window window = activity.getWindow();
            if (darkMode) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    public static void setStatusBarBlackTextMode(Activity activity, boolean darkMode) {
        if (setNativeStatusBarDarkIcon(activity, darkMode) ||
                setMIUIStatusBarDarkMode(activity, darkMode) ||
                setMeiZuStatusBarDarkIcon(activity, darkMode) ||
                setOPPOStatusTextColor(activity, darkMode)) {
            return;
        }
        setNativeStatusBarDarkBackground(activity, darkMode);
    }

    /**
     * 状态栏透明化
     */
    public static void setStatusBarTransparent(Activity activity) {
        Window window = activity.getWindow();
//        window.setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //5.0版本及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View view = window.getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | view.getSystemUiVisibility());
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowStatusBarColor(Activity activity, int color) {
        Window window = activity.getWindow();
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(color);
    }

    /**
     * 获取状态栏高度
     *
     * @return 高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
