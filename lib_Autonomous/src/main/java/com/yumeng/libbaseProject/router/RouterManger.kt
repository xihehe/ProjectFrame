package com.yumeng.libbaseProject.router

import android.content.Context
import com.sankuai.waimai.router.Router

object RouterManger {

    fun startLoginActivity(context: Context?) {
        Router.startUri(context, RouterScheme.LoginActivityPath)
    }

}