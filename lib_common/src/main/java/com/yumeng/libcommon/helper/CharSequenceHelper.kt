package com.yumeng.libcommon.helper

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt
import com.yumeng.libcommon.span.MyImageSpan

/**
 * Created by Chauncey on 2018/1/18 16:13.
 * CharSequence辅助类
 */

object CharSequenceHelper {
    fun setColor(sequence: CharSequence?, color: Int): SpannableString {
        if (sequence == null) {
            return SpannableString("")
        }
        val builder = SpannableString(sequence)
        builder.setSpan(ForegroundColorSpan(color), 0, sequence.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        return builder
    }

    fun setSize(sequence: CharSequence?, size: Int): CharSequence {
        if (sequence == null) {
            return ""
        }
        val builder = SpannableString(sequence)
        builder.setSpan(AbsoluteSizeSpan(size), 0, sequence.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        return builder
    }

    fun setImage(context: Context, sequence: CharSequence?, res: Int): CharSequence {
        if (sequence == null) {
            return ""
        }
        val builder = SpannableString(sequence)
        builder.setSpan(
            MyImageSpan(context, res),
            0,
            sequence.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return builder
    }

    fun replaceSizeContent(
        startKeyWord: String,
        startSize: Int,
        endKeyWord: String? = null,
        endSize: Int,
        strContent: String,
        contentSize: Int
    ): CharSequence {
        val startKeyWordIndex = strContent.indexOf(startKeyWord)
        var endKeyWordIndex = -1
        if (endKeyWord != null) {
            endKeyWordIndex = strContent.indexOf(endKeyWord)
        }
        val builder = SpannableStringBuilder(strContent)
        builder.setSpan(
            AbsoluteSizeSpan(startSize),
            startKeyWordIndex,
            startKeyWordIndex + startKeyWord.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        if (endKeyWordIndex != -1) {
            builder.setSpan(
                AbsoluteSizeSpan(endSize),
                endKeyWordIndex,
                endKeyWordIndex + endKeyWord!!.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            builder.setSpan(
                AbsoluteSizeSpan(contentSize),
                startKeyWord.length,
                endKeyWordIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        } else {
            builder.setSpan(
                AbsoluteSizeSpan(contentSize),
                startKeyWord.length,
                strContent.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        return builder
    }

    fun replaceContent(keyword: String, strContent: String, @ColorInt color: Int): CharSequence {
        var content: String
        var keywordIndex = strContent.indexOf(keyword)
        val builder = SpannableStringBuilder(strContent)
        while (keywordIndex != -1) {
            builder.setSpan(
                ForegroundColorSpan(color),
                keywordIndex,
                keywordIndex + keyword.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            val tempKeyWordTempIndex = keywordIndex + keyword.length
            content = strContent.substring(tempKeyWordTempIndex, strContent.length)
            keywordIndex = content.indexOf(keyword)
            if (keywordIndex != -1) {
                keywordIndex += tempKeyWordTempIndex;
            }
        }
        return builder
    }


}
