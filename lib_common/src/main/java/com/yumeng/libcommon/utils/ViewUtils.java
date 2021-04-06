package com.yumeng.libcommon.utils;

import android.app.Activity;
import android.view.View;

public class ViewUtils {
    /**
     * 通过控件的Id获取对应的控件
     */
    public static <T extends View> T findViewsById(Activity activity, int viewId) {
        View view = activity.findViewById(viewId);
        return (T) view;
    }

    /**
     * 通过控件的Id获取对应的控件
     */
    public static <T extends View> T findViewsById(View parent, int viewId) {
        View view = parent.findViewById(viewId);
        return (T) view;
    }

}
