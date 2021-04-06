package com.yumeng.libcommon.manage

import android.app.Activity
import java.util.*
import kotlin.collections.ArrayList

class ActivityManage {

    /**
     * 维护Activity 的list
     */
    private val mActivitys = Stack<Activity>()

    companion object {
        var instance: ActivityManage =
            ActivityManage()
            private set

        val activityList: Stack<Activity>
            get() {
                return instance.mActivitys
            }

        val size: Int
            get() {
                return instance.mActivitys.size
            }

        //在Activity基类的onCreate()方法中执行
        fun addActivity(activity: Activity) {
            instance.mActivitys.add(activity)
        }

        fun getActivity(clz: Class<out Activity>): Activity? {
            instance.mActivitys.forEach { value ->
                val className = value.componentName?.className
                val targetName = clz.name
                if (className == targetName) {
                    return value
                }
            }
            return null
        }

        fun removeActivity(activity: Activity) {
            instance.mActivitys.remove(activity)
        }

        /**
         * 结束指定的Activity
         *
         * @param clz Activity类
         */
        fun finishActivity(clz: Class<out Activity>) {
            val pairs = ArrayList<Activity>()
            instance.mActivitys.forEach { value ->
                val className = value.componentName?.className
                val targetName = clz.name
                if (className == targetName) {
                    pairs.add(value)
                }
            }

            pairs.forEach {
                instance.mActivitys.remove(it)
                it.finish()
            }
        }

        private fun finishActivity(hashCode: Int) {
            instance.mActivitys[hashCode]?.let {
                instance.mActivitys.remove(it)
                it.finish()
            }
        }

        fun finishActivity(activity: Activity) {
            finishActivity(activity.hashCode())
        }

        fun recreateAllActivity() {
            instance.mActivitys.forEach { value ->
                value.recreate()
            }
        }

        fun recreateAllActivityWithout(vararg clz: Class<out Activity>) {
            instance.mActivitys.forEach { value ->
                var canRecreate = true
                val className = value.componentName?.className
                clz.forEach { clzs ->
                    val targetName = clzs.name
                    if (className == targetName) {
                        canRecreate = false
                    }
                }
                if (canRecreate) {
                    value.recreate()
                }
            }
        }

        fun recreateAllActivityWithout(vararg activity: Activity) {
            instance.mActivitys.forEach { value ->
                var canRecreate = true
                activity.forEach { item ->
                    if (value == item) {
                        canRecreate = false
                    }
                }
                if (canRecreate) {
                    value.recreate()
                }
            }
        }


        fun finishAllActivity(clz: Class<out Activity>? = null) {

            val activityList = if (clz != null) getActivityList(
                clz
            ) else null
            val activityHashCodeList = activityList?.mapTo(ArrayList()) {
                it.hashCode()
            }

            val activityMap = instance.mActivitys
            activityMap.forEach {
                if (activityList == null) {
                    it.finish()
                } else if (!activityList.contains(it)) {
                    it.finish()
                }
            }

            instance.mActivitys.clear()
//            activityMap.keyIterator().forEach {
//                if (activityHashCodeList == null) {
//                    activityMap.remove(it)
//                } else if (!activityHashCodeList.contains(it)) {
//                    activityMap.remove(it)
//                }
//            }
        }

        fun existActivity(clz: Class<out Activity>): Boolean {
            instance.mActivitys.forEach { value ->
                val className = value.componentName?.className
                val targetName = clz.name
                if (className == targetName) {
                    return true
                }
            }
            return false
        }

        private fun getActivityList(clz: Class<out Activity>): ArrayList<Activity>? {

            val list = ArrayList<Activity>()
            instance.mActivitys.forEach { value ->
                val className = value.componentName?.className
                val targetName = clz.name
                if (className == targetName) {
                    list.add(value)
                }
            }
            return list
        }

        /**
         * 获取当前activity
         * @return
         */
        fun getCurrentActivity(): Activity? {
            var targetActivity: Activity? = null
            targetActivity = try {
                instance.mActivitys.peek()
            } catch (e: EmptyStackException) {
                null
            }
            return targetActivity
        }

    }
}