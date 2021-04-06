package com.yumeng.libcommon.helper

import com.yumeng.libcommon.R
import com.yumeng.libcommon.context.AppContextWrapper
import java.text.SimpleDateFormat
import java.util.*


object DateFormatHelper {

    fun formatDate(timeStamp: Long?, format: String = "yyyy-MM-dd HH:mm"): String? {
        timeStamp ?: return null
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(Date(timeStamp))
    }

    fun formatDateEn(timeStamp: Long?, format: String = "yyyy-MM-dd HH:mm"): String? {
        timeStamp ?: return null
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())// Locale.getDefault()
        return dateFormat.format(Date(timeStamp))
    }

    fun getDuration(duration: String): String {
        val second = duration.toInt()
        if (second > 60) {
            return formatSecond(
                second
            )
        }
        return "$duration\""
    }

    // 调用此方法输入所要转换的时间戳例如（1402733340）输出（"2014年06月14日16时09分00秒"）
    fun times(timeStamp: Long): String {
        val sdr = SimpleDateFormat("MM月dd日  #  HH:mm")
        return sdr.format(Date(timeStamp)).replace("#".toRegex(),
            getWeek(timeStamp)
        )

    }


    private fun getWeek(timeStamp: Long): String {
        var mydate = 0
        var week: String = ""
        val cd = Calendar.getInstance()
        cd.time = Date(timeStamp)
        mydate = cd.get(Calendar.DAY_OF_WEEK)
        val context= AppContextWrapper.getApplicationContext()
        // 获取指定日期转换成星期几
        if (mydate == 1) {
            week = context.getString(R.string.sunday)
        } else if (mydate == 2) {
            week = context.getString(R.string.Monday)
        } else if (mydate == 3) {
            week = context.getString(R.string.Tuesday)
        } else if (mydate == 4) {
            week = context.getString(R.string.Wednesday)
        } else if (mydate == 5) {
            week = context.getString(R.string.Thursday)
        } else if (mydate == 6) {
            week = context.getString(R.string.Friday)
        } else if (mydate == 7) {
            week = context.getString(R.string.Saturday)
        }
        return week
    }



    private val minute = (60 * 1000).toLong()// 1分钟
    private val hour = 60 * minute// 1小时
    private val day = 24 * hour// 1天
    private val month = 31 * day// 月
    private val year = 12 * month// 年

    fun getTimeFormatText(date: Long): String {
        //        if (date == null) {
        //            return null;
        //        }

        val diff = Date().time - date
        var r: Long = 0

        if (diff > year) {
            r = diff / year
            return r.toString() + "年前"
        }
        if (diff > month) {
            r = diff / month
            return r.toString() + "个月前"
        }
        if (diff > day) {
            r = diff / day
            return r.toString() + "天前"
        }
        if (diff > hour) {
            r = diff / hour
            return r.toString() + "个小时前"
        }
        if (diff > minute) {
            r = diff / minute
            return r.toString() + "分钟前"
        }
        return "刚刚"
    }


    fun formatTimeDay(ts: Long): String? {
        val cal = Calendar.getInstance()
        cal.timeInMillis = ts
        return when {
            isToday(ts) -> {
                formatDate(
                    ts,
                    "HH:mm"
                )
            }
            isYesterday(ts) -> {
                formatDate(
                    ts,
                    "HH:mm"
                )
            }
            else -> {
                formatDate(
                    ts,
                    "MM-dd"
                )
            }

        }
    }


    private fun isInWeek(ts: Long): Boolean {
        val now = now()
        //6天前
        val day6 = now - 6 * 24 * 60 * 60 * 1000
        val cal = Calendar.getInstance()
        cal.timeInMillis = day6
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        val zero = cal.timeInMillis
        return ts >= zero
    }


    private fun now(): Long {
        val date = Date()
        return date.time
    }

    private fun isToday(ts: Long): Boolean {
        val now = now()
        return isSameDay(now, ts)
    }

    private fun isYesterday(ts: Long): Boolean {
        val now = now()
        val yesterday = now - 24 * 60 * 60 * 1000
        return isSameDay(
            yesterday,
            ts
        )
    }


    /**
     * 是否是同一天
     */
    private fun isSameDay(now: Long, ts: Long): Boolean {
        val cal = Calendar.getInstance()
        cal.timeInMillis = now
        val year1 = cal.get(Calendar.YEAR)
        val month1 = cal.get(Calendar.MONTH)
        val day1 = cal.get(Calendar.DAY_OF_MONTH)
        cal.timeInMillis = ts
        val year2 = cal.get(Calendar.YEAR)
        val month2 = cal.get(Calendar.MONTH)
        val day2 = cal.get(Calendar.DAY_OF_MONTH)
        return year1 == year2 && month1 == month2 && day1 == day2
    }

    /**
     * 是否是本月
     */
    private fun isSameMonth(now: Long, ts: Long): Boolean {
        val cal = Calendar.getInstance()
        cal.timeInMillis = now
        val year1 = cal.get(Calendar.YEAR)
        val month1 = cal.get(Calendar.MONTH)
        cal.timeInMillis = ts
        val year2 = cal.get(Calendar.YEAR)
        val month2 = cal.get(Calendar.MONTH)
        return year1 == year2 && month1 == month2
    }

    private fun formatSecond(second: Int): String {
        val formatTime: String
        val format: String
        val array: Array<Any>
        val hours = second / (60 * 60)
        val minutes = second / 60 - hours * 60
        val seconds = second - minutes * 60 - hours * 60 * 60
        when {
            hours > 0 -> {
                format = "%1$,d:%2$,d:%3$,d"
                array = arrayOf(hours, minutes, seconds)
            }
            minutes > 0 -> {
                format = "%1$,d'%2$,d\""
                array = arrayOf(minutes, seconds)
            }
            else -> {
                format = "%1$,d'"
                array = arrayOf(seconds)
            }
        }
        formatTime = String.format(format, *array)

        return formatTime

    }

    private var dateFormat: SimpleDateFormat? = null

    fun dateToTimeStamp(date: String, format: String = "yyyy-MM-dd HH:mm:ss"): Long {
        try {
            if (dateFormat == null) {
                dateFormat = SimpleDateFormat(format, Locale.getDefault())
            }
            return dateFormat?.parse(date)?.time ?: -1
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return -1
    }

}