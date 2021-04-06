package com.yumeng.libcommon.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

@Aspect
class SingleClickAspect {

    /**
     * 定义切点，标记切点为所有被@AopOnclick注解的方法
     */
    @Pointcut("execution(@com.yumeng.libcommon.aop.SingleClick * *(..))")
    fun methodAnnotated() {
    }

    /**
     * 定义一个切面方法，包裹切点方法
     */
    @Around("methodAnnotated()")
    @Throws(Throwable::class)
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint) {
        // 取出方法的注解
        val methodSignature = joinPoint.signature as MethodSignature
        val method = methodSignature.method
        if(method==null){
            joinPoint.proceed()
            return
        }
        if (!method.isAnnotationPresent(SingleClick::class.java)) {
            return
        }
        val singleClick = method.getAnnotation(SingleClick::class.java)
        // 判断是否快速点击
        if (!ClickUtil.isFastDoubleClick(singleClick?.value ?: 0)) {
            // 不是快速点击，执行原方法
            joinPoint.proceed()
        }
    }
}