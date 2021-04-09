package com.yumeng.hibro.testWorkManager

import android.content.Context
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.yumeng.libcommon.utils.LogUtils

class TestWork_3(context: Context, params: WorkerParameters)  : Worker (context,params) {

    override fun doWork(): Result {
        val getInData = inputData.getString("test_3_param_3")
        LogUtils.e("TestWork","TestWork_3:$getInData")
        val outputData = Data.Builder().putString("name", "老李").build()

        return Result.success(outputData)
    }


}