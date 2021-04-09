package com.yumeng.hibro.testWorkManager

import android.content.Context
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.yumeng.libcommon.utils.LogUtils

class TestWork_1(context: Context, params: WorkerParameters)  : Worker (context,params) {

    override fun doWork(): Result {
        val getInData = inputData.getString("test_1_param_1")
        LogUtils.e("TestWork","TestWork_1:$getInData")
        val outputData = Data.Builder().putString("name", "老张").build()

        return Result.success(outputData)
    }


}