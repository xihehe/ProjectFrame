package com.yumeng.libcommon.aop

@Target(AnnotationTarget.FUNCTION)
annotation class SingleClick(/*点击间隔时间*/
        val value: Long = 1000)
