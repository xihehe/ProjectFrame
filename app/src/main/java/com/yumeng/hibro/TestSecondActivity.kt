package com.yumeng.hibro

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import androidx.work.*
import com.yumeng.hibro.testWorkManager.TestWork_1
import com.yumeng.hibro.testWorkManager.TestWork_2
import com.yumeng.hibro.testWorkManager.TestWork_3
import com.yumeng.hibro.testWorkManager.TestWork_4
import com.yumeng.hibro.viewModel.MainViewModel
import com.yumeng.libbaseProject.activity.BaseImVMActivity
import com.yumeng.libcommon.event.MessageEvent
import com.yumeng.libcommon.manage.ActivityManage
import com.yumeng.libcommon.utils.LogUtils
import com.yumeng.libcommonview.activity.CommonToolbarActivity
import com.yumeng.libcommonview.base.BaseActivity
import kotlinx.android.synthetic.main.activity_second.*

class TestSecondActivity : CommonToolbarActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNormalConfig()
        setContentView(R.layout.activity_second)
        setTitle("second")
    }

    override fun initView() {
        super.initView()
        var request_1 = createRequest_1()
        var request_2 = createRequest_2()
        val request_3 = createRequest_3()
        var request_4 = createRequest_4()
        twoCheck.setOnClickListener {
            val main =ActivityManage.existActivity(MainActivity::class.java)
            val two = ActivityManage.existActivity(TestSecondActivity::class.java)
            val three = ActivityManage.existActivity(TestthreeActivity::class.java)
            LogUtils.e("test","main:$main  two$two  three$three")
            ActivityManage.recreateAllActivity()
        }
        testWorkContinuation.setOnClickListener {

            WorkManager.getInstance()?.let {
                val requestData1 = it.getWorkInfoByIdLiveData(request_1.id)
                val requestData2 = it.getWorkInfoByIdLiveData(request_2.id)
                if(requestData1.value==null){
                    request_1 = createRequest_1()
                }
                if(requestData2.value==null){
                    request_2 = createRequest_2()
                }
            }

            WorkManager.getInstance()?.apply {
                val continuation1 = beginWith(mutableListOf(request_1, request_2))
//                    val continuation1 = beginWith(request1, request2)
//                    WorkContinuation.combine(continuation1, continuation2).enqueue()
                WorkContinuation.combine(arrayListOf(continuation1)).enqueue()
            }


            WorkManager.getInstance().getWorkInfoByIdLiveData(request_1.id).observe(this, Observer {
                when (it.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        val outResult = it.outputData.getString("name");
                        LogUtils.e("TestWork","test_1:$outResult")
                        WorkManager.getInstance().cancelWorkById(it.id)

                    }
                    WorkInfo.State.FAILED -> {

                    }
                    else -> {}
                }
            })
            WorkManager.getInstance().getWorkInfoByIdLiveData(request_2.id).observe(this, Observer {
                when (it.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        val outResult = it.outputData.getString("name");
                        LogUtils.e("TestWork","test_2:$outResult")
                        WorkManager.getInstance().cancelWorkById(it.id)
                    }
                    WorkInfo.State.FAILED -> {

                    }
                    else -> {}
                }
            })

        }

        testBeginUniqueWork.setOnClickListener {
            WorkManager.getInstance()?.apply {
                beginUniqueWork("a",ExistingWorkPolicy.APPEND,request_3).enqueue()
                beginUniqueWork("a",ExistingWorkPolicy.APPEND,request_4).enqueue()//这个应该不会忽略
            }
        }

    }

    private fun createRequest_1(tag:String? = null): OneTimeWorkRequest {
        val builder = OneTimeWorkRequest.Builder(TestWork_1::class.java).apply {
            setInputData(Data.Builder().putString("test_1_param_1", "这是第一个Work").build())
//        setConstraints()
        }
        return builder.build()
    }
    private fun createRequest_2(tag:String? = null): OneTimeWorkRequest {
        val builder = OneTimeWorkRequest.Builder(TestWork_2::class.java).apply {
            setInputData(Data.Builder().putString("test_2_param_2", "这是第二个Work").build())
//        setConstraints()
        }
        return builder.build()
    }
    private fun createRequest_3(tag:String? = null): OneTimeWorkRequest {
        val builder = OneTimeWorkRequest.Builder(TestWork_3::class.java).apply {
            setInputData(Data.Builder().putString("test_3_param_3", "这是第三个Work").build())
//        setConstraints()
        }
        return builder.build()
    }
    private fun createRequest_4(tag:String? = null): OneTimeWorkRequest {
        val builder = OneTimeWorkRequest.Builder(TestWork_4::class.java).apply {
            setInputData(Data.Builder().putString("test_4_param_4", "这是第四个Work").build())
//        setConstraints()
        }
        return builder.build()
    }



}



