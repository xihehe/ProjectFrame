package com.yumeng.libcommon.utils

import java.sql.Array
import java.text.SimpleDateFormat
import java.util.*

object TrendsUtils {

    fun getUrlList(str: String?): List<String> {
        if(str == null){
            return listOf()
        }
        val subString = str!!.substring(str.indexOf("[") + 1, str.lastIndexOf("]"))
        return subString.split(",".toRegex()).dropLastWhile { it.isEmpty() }
    }


    private val video_suffix_list = arrayOf("mp4","m4v","mov","3gp","3gpp","3gpp2","mkv","webm","ts","avi")

    fun isVideoUrl(path:String):Boolean{
        val str = path.split(".")
        if(str.size > 1) {
            if (video_suffix_list.contains(str[str.lastIndex])) {
                return true
            }
        }
        return false
    }

    /**
     * 获取视频时长（格式化）
     *
     * @param timestamp
     * @return
     */
    fun getVideoDuration(timestamp: Long): String {
        if (timestamp < 1000) {
            return "00:01"
        }
        val date = Date(timestamp)
        val simpleDateFormat = SimpleDateFormat("mm:ss")
        return simpleDateFormat.format(date)
    }


}