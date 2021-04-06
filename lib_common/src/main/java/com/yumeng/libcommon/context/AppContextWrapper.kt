package com.yumeng.libcommon.context

import android.content.Context
import android.content.ContextWrapper


class AppContextWrapper private constructor(base: Context) : ContextWrapper(base) {

    companion object {
        private lateinit var instance: AppContextWrapper

        fun getApplicationContext(): Context = instance.applicationContext

        fun getBaseContext(): Context = instance.baseContext

        fun init(context: Context): Context {
            instance =
                AppContextWrapper(context)
            return instance.baseContext
        }
       
    }
}