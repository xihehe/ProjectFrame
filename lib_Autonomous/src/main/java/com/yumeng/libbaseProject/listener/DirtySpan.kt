package com.yumeng.aillo.core.listener

import android.text.Spannable

interface DirtySpan {
    fun isDirty(text: Spannable): Boolean
}