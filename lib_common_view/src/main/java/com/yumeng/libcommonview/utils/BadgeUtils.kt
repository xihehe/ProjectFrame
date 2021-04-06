package com.yumeng.libcommonview.utils

import com.yumeng.libcommon.ext.putData
import com.yumeng.libcommon.helper.Preference


object BadgeUtils {

    private const val SPACE_WIDTH_KEY = "space_width"

    fun setSpaceWidth(x: Float) {
        if (x > 0)
            x.putData(SPACE_WIDTH_KEY)

    }

    fun getSpaceWidth(): Float {
        val space by Preference(SPACE_WIDTH_KEY, 0F)
        return space
//        return SharedPreferencesHelper.getFloat(AppContextWrapper.getApplicationContext(), SPACE_WIDTH_KEY, 0F)
    }

    fun isDelayed(): Boolean {
        return getSpaceWidth() == 0F
    }
}