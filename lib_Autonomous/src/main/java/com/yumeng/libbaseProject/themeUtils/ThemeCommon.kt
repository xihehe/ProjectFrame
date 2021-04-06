package com.yumeng.libbaseProject.themeUtils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.yumeng.libbaseProject.R
import com.yumeng.libcommonview.theme.Theme

object ThemeCommon {
    fun getThemeCircleDrawable(context: Context): Drawable? {
        return when (Theme.themePosition) {
            0 -> {
                ContextCompat.getDrawable(context, R.drawable.shape_circle)
            }
            else -> {
                ContextCompat.getDrawable(context, R.drawable.shape_circle)
            }
        }
    }

    fun getFileAssistantSmall(): Int {
        return when (Theme.themePosition) {
            0 -> {
                R.mipmap.ic_file_assistant
            }
            else -> {
                R.mipmap.ic_file_assistant
            }
        }
    }

    fun getSystemNotification(): Int {
        return when (Theme.themePosition) {
            0 -> {
                R.drawable.ic_chat_system_not
            }
            else -> {
                R.drawable.ic_chat_system_not
            }
        }
    }

    fun getTmpChatBoxDrable(): Int {
        return when (Theme.themePosition) {
            0 -> {
                R.drawable.ic_tmp_chat
            }
            else -> {
                R.drawable.ic_tmp_chat
            }
        }
    }

}