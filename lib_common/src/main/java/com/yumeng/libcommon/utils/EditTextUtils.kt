package com.yumeng.libcommon.utils

import android.text.method.ReplacementTransformationMethod
import android.widget.EditText
import java.util.regex.Pattern

/**
 * edittext 小写替换成大写
 */
object EditTextUtils {

    fun word2WORD(editText:EditText){

        editText.transformationMethod = object: ReplacementTransformationMethod() {
            override fun getOriginal(): CharArray {
                return charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' );
            }
            override fun getReplacement(): CharArray {
                return charArrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' )
            }

        };

    }

}