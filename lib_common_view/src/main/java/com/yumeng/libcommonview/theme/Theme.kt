package com.yumeng.libcommonview.theme

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.yumeng.libcommon.ext.putData
import com.yumeng.libcommon.helper.Preference
import com.yumeng.libcommon.utils.language.MultiLanguageUtil
import com.yumeng.libcommonview.R
import java.lang.ref.WeakReference

class Theme {

    companion object {
        const val key_switch2Track = "switch2Track"
        const val key_switch2TrackChecked = "switch2TrackChecked"
        const val key_windowBackgroundWhite = "windowBackgroundWhite"
        const val key_switchTrackBlueSelectorChecked = "switchTrackBlueSelectorChecked"
        const val key_switchTrackBlueSelector = "switchTrackBlueSelector"
        const val THEME_COLOR_POSITION = "theme_color_position"
        var themePosition: Int = 0
            set(value) {
                getColor(R.color.key_switch2Track).putData(key_switch2Track)
                getColor(R.color.white).putData(key_windowBackgroundWhite)

                if (value == 0) {
                    getColor(R.color.basic_theme_colors).putData(key_switch2TrackChecked)
                } else {
                    getColor(R.color.basic_theme_colors_blue).putData(key_switch2TrackChecked)
                }
                field = value
            }
        private lateinit var context: WeakReference<Context>


        fun getColor(key: String): Int {
            return Preference<Int>().getValue(key,0)
        }

        fun getThemeColor(): Int {
            return Preference<Int>().getValue(key_switch2TrackChecked,0)
        }



        fun init(context: Context) {
            this.context = WeakReference(context)
            themePosition =  Preference<Int>().getValue(THEME_COLOR_POSITION, 0)
        }

        private fun getColor(color: Int): Int {
            if (context.get() == null) {
                return Color.RED
            }
            return ContextCompat.getColor(context.get()!!, color)
        }
    }
}