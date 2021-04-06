package com.yumeng.libcommon.utils.contact

import android.widget.ImageView
import com.github.stuxuhai.jpinyin.PinyinFormat
import com.github.stuxuhai.jpinyin.PinyinHelper
import com.yumeng.libcommon.R
import com.yumeng.libcommon.ext.loadUrl


/**
 * Created by Chauncey on 2018/6/15 10:32.
 * 获取 拼音检索 对应的图片
 */
object AvatarHelper {
    fun loadAvatar(imageView: ImageView, path: String?, name: String?) {
        imageView.loadUrl(path, getAvatarImageRes(name))
    }

    fun getAvatarImageRes(name: String?): Int {
        if (name.isNullOrBlank()) {
            return R.drawable.ic_character_number
        }

        return when (PinyinHelper.convertToPinyinString(name, "", PinyinFormat.WITHOUT_TONE)
            .first().toUpperCase().toString()) {
            "A" -> R.drawable.ic_character_a
            "B" -> R.drawable.ic_character_b
            "C" -> R.drawable.ic_character_c
            "D" -> R.drawable.ic_character_d
            "E" -> R.drawable.ic_character_e
            "F" -> R.drawable.ic_character_f
            "G" -> R.drawable.ic_character_g
            "H" -> R.drawable.ic_character_h
            "I" -> R.drawable.ic_character_i
            "J" -> R.drawable.ic_character_j
            "K" -> R.drawable.ic_character_k
            "L" -> R.drawable.ic_character_l
            "M" -> R.drawable.ic_character_m
            "N" -> R.drawable.ic_character_n
            "O" -> R.drawable.ic_character_o
            "P" -> R.drawable.ic_character_p
            "Q" -> R.drawable.ic_character_q
            "R" -> R.drawable.ic_character_r
            "S" -> R.drawable.ic_character_s
            "T" -> R.drawable.ic_character_t
            "U" -> R.drawable.ic_character_u
            "V" -> R.drawable.ic_character_v
            "W" -> R.drawable.ic_character_w
            "X" -> R.drawable.ic_character_x
            "Y" -> R.drawable.ic_character_y
            "Z" -> R.drawable.ic_character_z
            else -> R.drawable.ic_character_number

        }
    }

}

