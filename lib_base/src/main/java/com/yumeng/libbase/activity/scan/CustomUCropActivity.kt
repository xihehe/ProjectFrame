package com.yumeng.libbase.activity.scan

import android.graphics.Color
import android.os.Bundle
import com.gyf.immersionbar.ktx.immersionBar
import com.yalantis.ucrop.UCropActivity
import com.yumeng.libbase.R

class CustomUCropActivity : UCropActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersionBar {
            titleBar(R.id.toolbar)
            navigationBarColorInt(Color.WHITE)
        }
    }
}