package com.yumeng.libcommon.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Fragment.show(fragmentManager: FragmentManager? = null) {
    (fragmentManager ?: this.fragmentManager)?.let {
        val beginTransaction = it.beginTransaction()
        it.fragments.forEach { fragment ->
            if (fragment == this) {
                beginTransaction.show(this)
            } else {
                beginTransaction.hide(fragment)
            }
        }

        beginTransaction.commit()
    }
}