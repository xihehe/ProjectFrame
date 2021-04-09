package com.yumeng.hibro

import android.os.Bundle
import android.os.PersistableBundle
import com.yumeng.hibro.viewModel.MainViewModel
import com.yumeng.libbaseProject.activity.BaseImVMActivity
import com.yumeng.libcommon.event.MessageEvent
import com.yumeng.libcommon.manage.ActivityManage
import com.yumeng.libcommon.utils.LogUtils
import com.yumeng.libcommonview.activity.CommonToolbarActivity
import com.yumeng.libcommonview.base.BaseActivity
import kotlinx.android.synthetic.main.activity_three.*

class TestthreeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
    }

    override fun initView() {
        super.initView()
        threeCheck.setOnClickListener {
            val main =ActivityManage.existActivity(MainActivity::class.java)
            val two = ActivityManage.existActivity(TestSecondActivity::class.java)
            val three = ActivityManage.existActivity(TestthreeActivity::class.java)
            LogUtils.e("test","main:$main  two$two  three$three")
            ActivityManage.finishAllActivity()
        }
    }




}