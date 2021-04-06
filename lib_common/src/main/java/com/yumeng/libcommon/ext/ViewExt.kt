package com.yumeng.libcommon.ext

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun TextView.enable(enable: Boolean) {
    if (enable) {
        this.isClickable = true
        this.isSelected = false
        this.isEnabled = true
    } else {
        this.isClickable = false
        this.isEnabled = false
        this.isSelected = true
    }
}


fun TextView.setBoldText(enable:Boolean){
    paint.isFakeBoldText=enable
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}