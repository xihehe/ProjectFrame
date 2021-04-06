package com.yumeng.libcommon.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.yumeng.libcommon.utils.EditTextUtils
import java.util.Collections.replaceAll
import java.util.regex.Pattern
import java.util.Collections.replaceAll

/**
 * edittext
 * 大小写转换
 * 限制仅仅同意字母、数字和汉字
 * 顶层
 */
fun EditText.word2WORD() {
    EditTextUtils.word2WORD(this)
}

fun EditText.exceptNum() {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // 仅仅同意字母、数字和汉字
            if (!s.isNullOrEmpty()) {
                val editable = this@exceptNum.getText().toString()
                val regEx = "[^a-zA-Z\u4E00-\u9FA5]"  //只能输入字母或数字
                val p = Pattern.compile(regEx)
                val m = p.matcher(editable)
                val str = m.replaceAll("").trim()    //删掉不是字母或数字的字符
                if (editable != str) {
                    this@exceptNum.setText(str)  //设置EditText的字符
                    this@exceptNum.setSelection(str.length) //因为删除了字符，要重写设置新的光标所在位置
                }
            }
        }

    })
}
