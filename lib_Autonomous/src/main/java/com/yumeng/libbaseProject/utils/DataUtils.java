package com.yumeng.libbaseProject.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSON;
import com.annimon.stream.Stream;
import com.yumeng.libbaseProject.R;
import com.yumeng.libbaseProject.model.NationCodeBean;
import com.yumeng.libcommon.context.AppContextWrapper;
import com.yumeng.libcommon.utils.language.AppLanguageUtils;
import com.yumeng.libcommon.utils.language.ConstantLanguages;
import com.yumeng.libcommonview.theme.Theme;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataUtils {
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;

    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static String getTodayDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        return format.format(new Date());
    }

    /**
     * 掉此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
     *
     * @param time
     * @return
     */
    public String data(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    public static String getTodayDateTimes() {
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日",
                Locale.getDefault());
        return format.format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime_Today() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return sdf.format(new Date());
    }

    /**
     * 调此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     *
     * @param time
     * @return
     */
    public static String dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    public static String getTimestamp(String time, String type) {
        SimpleDateFormat sdr = new SimpleDateFormat(type, Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param time
     * @return
     */
    public static String times(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16:09"）
     *
     * @param time
     * @return
     */
    public static String timet(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16:09"）
     *
     * @param time
     * @return
     */
    public static String timet2(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    /**
     * @param time 斜杠分开
     * @return
     */
    public static String timeslash(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd,HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * @param time 斜杠分开
     * @return
     */
    public static String timeslashData(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
//      int i = Integer.parseInt(time);
        String times = sdr.format(new Date(lcc * 1000L));
        return times;

    }

    /**
     * @param time 斜杠分开
     * @return
     */
    public static String timeMinute(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        //int i = Integer.parseInt(time);
        String times = sdr.format(new Date(lcc * 1000L));
        return times;

    }

    public static String tim(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyyMMdd HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    public static String time(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    // 调用此方法输入所要转换的时间戳例如（1402733340）输出（"2014年06月14日16时09分00秒"）
    public static String times(long timeStamp) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM月dd日  #  HH:mm");
        return sdr.format(new Date(timeStamp)).replaceAll("#",
                getWeek(timeStamp));

    }

    // 调用此方法输入所要转换的时间戳例如（1402733340）输出（"06月14日"）
    public static String times2(long timeStamp) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM-dd");
        return sdr.format(new Date(timeStamp)).replaceAll("#",
                getWeek(timeStamp));

    }


    // 调用此方法输入所要转换的时间戳例如（1402733340）输出（"06月14日"）
    public static String times4(long timeStamp) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdr.format(new Date(timeStamp)).replaceAll("#",
                getWeek(timeStamp));

    }

    public static String times3(long timeStamp) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdr.format(new Date(timeStamp)).replaceAll("#",
                getWeek(timeStamp));

    }

    public static String times5(long timeStamp) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        return sdr.format(new Date(timeStamp)).replaceAll("#",
                getWeek(timeStamp));

    }

    // 调用此方法输入所要转换的时间戳例如（1402733340）输出（"06月14日"）
    public static String times6(long timeStamp) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdr.format(new Date(timeStamp)).replaceAll("#",
                getWeek(timeStamp));

    }

    public static String times7(long timeStamp) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH:mm");
        return sdr.format(new Date(timeStamp)).replaceAll("#",
                getWeek(timeStamp));

    }


    // 调用此方法输入所要转换的时间戳例如（1402733340）输出（"2020-12-12"）
    public static String time8(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        return sdr.format(new Date(time));
    }

    public static String time9(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM");
        return sdr.format(new Date(time));
    }

    public static String time10(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy");
        return sdr.format(new Date(time));
    }
    public static String time11(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM");
        return sdr.format(new Date(time));
    }
    //时间相关
    public static String formatTimeBase(long ts) {
        long temp = ts / 1000;
        String s = "";
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(temp * 1000);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int apm = cal.get(Calendar.AM_PM);
        Context context= AppContextWrapper.Companion.getApplicationContext();
        String weeks[] = {context.getString(R.string.sunday), context.getString(R.string.Monday), context.getString(R.string.Tuesday), context.getString(R.string.Wednesday), context.getString(R.string.Thursday), context.getString(R.string.Friday), context.getString(R.string.Saturday)};
        if (isToday(ts)) {
            s = String.format("%02d:%02d", hour, minute);
//            if(apm==0){
//                s=context.getString(R.string.am_zh)+s+context.getString(R.string.am_en);
//            }else{
//                s=context.getString(R.string.pm_zh)+s+context.getString(R.string.pm_en);
//            }
        } else if (isYesterday(ts)) {
            s = String.format(context.getString(R.string.yesterday) + " %02d:%02d", hour, minute);
        } else if (isInWeek(ts)) {
            s = String.format("%s %02d:%02d", weeks[dayOfWeek - 1], hour, minute);
        } else if (isInYear(ts)) {
            s = String.format("%02d-%02d %02d:%02d", month + 1, dayOfMonth, hour, minute);
        } else {
            s = String.format("%d-%02d-%02d %02d:%02d", year, month + 1, dayOfMonth, hour, minute);
        }
        return s;
    }


    public static String formatTimeBase2(long ts) {
        long temp = ts / 1000;
        String s = "";
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(temp * 1000);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int apm = cal.get(Calendar.AM_PM);
        Context context= AppContextWrapper.Companion.getApplicationContext();
        String weeks[] = {context.getString(R.string.sunday), context.getString(R.string.Monday), context.getString(R.string.Tuesday), context.getString(R.string.Wednesday), context.getString(R.string.Thursday), context.getString(R.string.Friday), context.getString(R.string.Saturday)};
        if (isToday(ts)) {
            s = String.format("%02d:%02d", hour, minute);
            if(apm==0){
                s=context.getString(R.string.am_zh)+s+context.getString(R.string.am_en);
            }else{
                s=context.getString(R.string.pm_zh)+s+context.getString(R.string.pm_en);
            }
        } else if (isYesterday(ts)) {
            s = String.format(context.getString(R.string.yesterday));
        }  else {
            String other = AppLanguageUtils.getSupportLanguage(AppLanguageUtils.getSaveLanguage());
            if(other.equals(ConstantLanguages.SIMPLIFIED_CHINESE)){
                s = String.format("%d/%02d/%02d", year, month + 1, dayOfMonth);
            }else{
                s=String.format("%02d/%02d/%d",dayOfMonth,month+1,year);
            }
        }
        return s;
    }


    public static int now() {
        Date date = new Date();
        long t = date.getTime();
        return (int) (t / 1000);
    }

    public static boolean isToday(long ts) {
        int now = now();
        return isSameDay(now, ts/1000);
    }

    public static boolean isYesterday(long ts) {
        int now = now();
        int yesterday = now - 24 * 60 * 60;
        return isSameDay(ts/1000, yesterday);
    }

    private static boolean isInYear(long ts) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts);
        int year = cal.get(Calendar.YEAR);

        cal.setTime(new Date());
        int y = cal.get(Calendar.YEAR);

        return (year == y);
    }

    private static boolean isInWeek(long ts) {
//        int now = now();
        //6天前
//        long day6 = now - 6 * 24 * 60 * 60;
        Calendar todayCal = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts);
        return todayCal.get(Calendar.WEEK_OF_YEAR) == cal.get(Calendar.WEEK_OF_YEAR);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        long zero = cal.getTimeInMillis();
//        return (ts >= zero);
    }

    private static boolean isSameDay(long ts1, long ts2) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts1 * 1000);
        int year1 = cal.get(Calendar.YEAR);
        int month1 = cal.get(Calendar.MONTH);
        int day1 = cal.get(Calendar.DAY_OF_MONTH);


        cal.setTimeInMillis(ts2 * 1000);
        int year2 = cal.get(Calendar.YEAR);
        int month2 = cal.get(Calendar.MONTH);
        int day2 = cal.get(Calendar.DAY_OF_MONTH);

        return ((year1 == year2) && (month1 == month2) && (day1 == day2));
    }

    private static String getWeek(long timeStamp) {
        int mydate = 0;
        String week = null;
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(timeStamp));
        mydate = cd.get(Calendar.DAY_OF_WEEK);
        Context context= AppContextWrapper.Companion.getApplicationContext();
        // 获取指定日期转换成星期几
        if (mydate == 1) {
            week = context.getString(R.string.sunday);
        } else if (mydate == 2) {
            week =context.getString(R.string.Monday);
        } else if (mydate == 3) {
            week =context.getString(R.string.Tuesday);
        } else if (mydate == 4) {
            week = context.getString(R.string.Wednesday);
        } else if (mydate == 5) {
            week = context.getString(R.string.Thursday);
        } else if (mydate == 6) {
            week = context.getString(R.string.Friday);
        } else if (mydate == 7) {
            week = context.getString(R.string.Saturday);
        }
        return week;
    }

    // 并用分割符把时间分成时间数组

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14-16-09-00"）
     *
     * @param time
     * @return
     */
    public String timesOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    public static String timesTwo(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 并用分割符把时间分成时间数组
     *
     * @param time
     * @return
     */
    public static String[] timestamp(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        String[] fenge = times.split("[年月日时分秒]");
        return fenge;
    }

    /**
     * 根据传递的类型格式化时间
     *
     * @param str
     * @param type 例如：yy-MM-dd
     * @return
     */
    public static String getDateTimeByMillisecond(String str, String type) {

        Date date = new Date(Long.valueOf(str));

        SimpleDateFormat format = new SimpleDateFormat(type);

        String time = format.format(date);

        return time;
    }

    /**
     * 分割符把时间分成时间数组
     *
     * @param time
     * @return
     */
    public String[] division(String time) {

        String[] fenge = time.split("[年月日时分秒]");

        return fenge;

    }

    /**
     * 输入时间戳变星期
     *
     * @param time
     * @return
     */
    public static String changeweek(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        Date date = null;
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(times);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;

    }

    /**
     * 获取日期和星期　例如：２０１４－１１－１３　１１:００　星期一
     *
     * @param time
     * @param type
     * @return
     */
    public static String getDateAndWeek(String time, String type) {
        return getDateTimeByMillisecond(time + "000", type) + "  "
                + changeweekOne(time);
    }

    /**
     * 输入时间戳变星期
     *
     * @param time
     * @return
     */
    public static String changeweekOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        Date date = null;
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(times);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;

    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date());
    }

    /**
     * 输入日期如（2014年06月14日16时09分00秒）返回（星期数）
     *
     * @param time
     * @return
     */
    public String week(String time) {
        Date date = null;
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(time);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;
    }

    //今天是时间，昨天显示昨天，前天开始显示日期
    public static String transformToDate(long ts){
        String dateStr = "";
        Context context= AppContextWrapper.Companion.getApplicationContext();
        if(isToday(ts)){
            dateStr =new SimpleDateFormat("HH:mm").format(ts);
        }else if(isYesterday(ts)){
            dateStr =String.format(context.getString(R.string.yesterday));
        }else{
            dateStr = new SimpleDateFormat("yyyy-MM-dd").format(ts);
        }
        return dateStr;
    }

    public static String transformToDate2(long ts){
        String dateStr = "";
        Context context= AppContextWrapper.Companion.getApplicationContext();
        if(isToday(ts)){
            dateStr = String.format(context.getString(R.string.today));
        }else if(isYesterday(ts)){
            dateStr =String.format(context.getString(R.string.yesterday));
        }else{
            dateStr = new SimpleDateFormat("yyyy-MM-dd").format(ts);
        }
        return dateStr;
    }


    //判断是否同一天
    public static boolean isTheSameDay(Long date1, Long date2){
        if(time8(date1).equals(time8(date2))){
            return true;
        }
        return false;
    }

    /**
     * 输入日期如（2014-06-14-16-09-00）返回（星期数）
     *
     * @param time
     * @return
     */
    public String weekOne(String time) {
        Date date = null;
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(time);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;
    }


    public static String formatTime(Object timeTemp) {
        int timeParam = 0;
        if (timeTemp instanceof Integer) {
            timeParam = (Integer) timeTemp;
        }
        if (timeTemp instanceof String) {
            timeParam = Integer.valueOf((String) timeTemp);
        }

        int second = timeParam % 60;
        int minuteTemp = timeParam / 60;
        if (minuteTemp > 0) {
            int minute = minuteTemp % 60;
            int hour = minuteTemp / 60;
            if (hour > 0) {
                return (hour > 10 ? (hour + "") : ("0" + hour)) + ":" + (minute > 10 ? (minute + "") : ("0" + minute))
                        + ":" + (second > 10 ? (second + "") : ("0" + second));
            } else {
                return "00:" + (minute > 10 ? (minute + "") : ("0" + minute)) + ":"
                        + (second > 10 ? (second + "") : ("0" + second));
            }
        } else {
            return "00:00:" + (second > 10 ? (second + "") : ("0" + second));
        }
    }

    public static String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return "未知时间";
        }
        final long diff = now - time;

        if (diff < MINUTE_MILLIS) {
            return "刚刚";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "1分钟前";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + "分钟前";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "1小时前";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + "小时前";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "昨天";
        } else {
            return diff / DAY_MILLIS + "天前";
        }
    }

    //如果是當前月顯示本月，如果是今年的月份，则显示月份,不然年份也显示了
    public static String dealTimeStr(Long ts){
        Long nowTs = Calendar.getInstance().getTimeInMillis();
        String timeStr = DataUtils.time9(ts);
        String nowTimeStr = DataUtils.time9(nowTs);
        if(timeStr.equals(nowTimeStr)){
            return "本月";
        }
        String timeYear = DataUtils.time10(ts);
        String nowTimeYeat = DataUtils.time10(nowTs);
        if(timeYear.equals(nowTimeYeat)){
            return  DataUtils.time11(ts)+"月";
        }else{
            return timeStr;
        }
    }


    public static Spanned getHighlightedSpan(@NonNull Locale locale,
                                       @Nullable String value,
                                       @Nullable String highlight)
    {
        if (TextUtils.isEmpty(value)) {
            return new SpannableString("");
        }

        value = value.replaceAll("\n", " ");

        if (TextUtils.isEmpty(highlight)) {
            return new SpannableString(value);
        }

        String       normalizedValue  = value.toLowerCase(locale);
        String       normalizedTest   = highlight.toLowerCase(locale);
        List<String> testTokens       = Stream.of(normalizedTest.split(" ")).filter(s -> s.trim().length() > 0).toList();

        Spannable spanned          = new SpannableString(value);
        int       searchStartIndex = 0;

        for (String token : testTokens) {
            if (searchStartIndex >= spanned.length()) {
                break;
            }

            int start = normalizedValue.indexOf(token, searchStartIndex);

            if (start >= 0) {
                int end = Math.min(start + token.length(), spanned.length());
                spanned.setSpan(getGreenSpan(), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                searchStartIndex = end;
            }
        }

        return spanned;
    }

    private static ForegroundColorSpan getGreenSpan(){
        int color;
        if(Theme.Companion.getThemePosition()==1){
            color=R.color.basic_theme_colors_blue;
        }else{
            color=R.color.basic_theme_colors;
        }
        return new ForegroundColorSpan(ContextCompat.getColor(AppContextWrapper.Companion.getApplicationContext(),color));
    }


    public static List<NationCodeBean> loadData() {
        String nationStr = "[\n" +
                "  {\n" +
                "    \"cn_name\": \"中国\",\n" +
                "    \"name\": \"China\",\n" +
                "    \"dial_code\": \"86\",\n" +
                "    \"price\": \"0.045\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"加拿大\",\n" +
                "    \"name\": \"Canada\",\n" +
                "    \"dial_code\": \"1\",\n" +
                "    \"price\": \"0.045\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"美国\",\n" +
                "    \"name\": \"United States\",\n" +
                "    \"dial_code\": \"1\",\n" +
                "    \"price\": \"0.057\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"哈萨克\",\n" +
                "    \"name\": \"Kazakhstan\",\n" +
                "    \"dial_code\": \"7\",\n" +
                "    \"price\": \"0.301\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"俄罗斯\",\n" +
                "    \"name\": \"Russian Federation\",\n" +
                "    \"dial_code\": \"7\",\n" +
                "    \"price\": \"0.193\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"埃及\",\n" +
                "    \"name\": \"Egypt\",\n" +
                "    \"dial_code\": \"20\",\n" +
                "    \"price\": \"0.502\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"南非\",\n" +
                "    \"name\": \"South Africa\",\n" +
                "    \"dial_code\": \"27\",\n" +
                "    \"price\": \"0.177\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"希腊\",\n" +
                "    \"name\": \"Greece\",\n" +
                "    \"dial_code\": \"30\",\n" +
                "    \"price\": \"0.453\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"荷兰\",\n" +
                "    \"name\": \"Netherlands\",\n" +
                "    \"dial_code\": \"31\",\n" +
                "    \"price\": \"0.607\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"比利时\",\n" +
                "    \"name\": \"Belgium\",\n" +
                "    \"dial_code\": \"32\",\n" +
                "    \"price\": \"0.709\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"法国\",\n" +
                "    \"name\": \"France\",\n" +
                "    \"dial_code\": \"33\",\n" +
                "    \"price\": \"0.374\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"西班牙\",\n" +
                "    \"name\": \"Spain\",\n" +
                "    \"dial_code\": \"34\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"匈牙利\",\n" +
                "    \"name\": \"Hungary\",\n" +
                "    \"dial_code\": \"36\",\n" +
                "    \"price\": \"0.597\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"意大利\",\n" +
                "    \"name\": \"Italy\",\n" +
                "    \"dial_code\": \"39\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"罗马尼亚\",\n" +
                "    \"name\": \"Romania\",\n" +
                "    \"dial_code\": \"40\",\n" +
                "    \"price\": \"0.598\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"瑞士\",\n" +
                "    \"name\": \"Switzerland\",\n" +
                "    \"dial_code\": \"41\",\n" +
                "    \"price\": \"0.426\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"奥地利\",\n" +
                "    \"name\": \"Austria\",\n" +
                "    \"dial_code\": \"43\",\n" +
                "    \"price\": \"0.506\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"英国\",\n" +
                "    \"name\": \"United Kingdom\",\n" +
                "    \"dial_code\": \"44\",\n" +
                "    \"price\": \"0.263\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"丹麦\",\n" +
                "    \"name\": \"Denmark\",\n" +
                "    \"dial_code\": \"45\",\n" +
                "    \"price\": \"0.184\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"瑞典\",\n" +
                "    \"name\": \"Sweden\",\n" +
                "    \"dial_code\": \"46\",\n" +
                "    \"price\": \"0.365\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"挪威\",\n" +
                "    \"name\": \"Norway\",\n" +
                "    \"dial_code\": \"47\",\n" +
                "    \"price\": \"0.395\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"波兰\",\n" +
                "    \"name\": \"Poland\",\n" +
                "    \"dial_code\": \"48\",\n" +
                "    \"price\": \"0.233\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"德国\",\n" +
                "    \"name\": \"Germany\",\n" +
                "    \"dial_code\": \"49\",\n" +
                "    \"price\": \"0.506\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"秘鲁\",\n" +
                "    \"name\": \"Peru\",\n" +
                "    \"dial_code\": \"51\",\n" +
                "    \"price\": \"0.317\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"墨西哥\",\n" +
                "    \"name\": \"Mexico\",\n" +
                "    \"dial_code\": \"52\",\n" +
                "    \"price\": \"0.354\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"古巴\",\n" +
                "    \"name\": \"Cuba\",\n" +
                "    \"dial_code\": \"53\",\n" +
                "    \"price\": \"0.317\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"阿根廷\",\n" +
                "    \"name\": \"Argentina\",\n" +
                "    \"dial_code\": \"54\",\n" +
                "    \"price\": \"0.486\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"巴西\",\n" +
                "    \"name\": \"Brazil\",\n" +
                "    \"dial_code\": \"55\",\n" +
                "    \"price\": \"0.426\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"智利\",\n" +
                "    \"name\": \"Chile\",\n" +
                "    \"dial_code\": \"56\",\n" +
                "    \"price\": \"0.365\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"哥伦比亚\",\n" +
                "    \"name\": \"Colombia\",\n" +
                "    \"dial_code\": \"57\",\n" +
                "    \"price\": \"0.512\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"委内瑞拉\",\n" +
                "    \"name\": \"Venezuela, Bolivarian Republic of\",\n" +
                "    \"dial_code\": \"58\",\n" +
                "    \"price\": \"0.415\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"马来西亚\",\n" +
                "    \"name\": \"Malaysia\",\n" +
                "    \"dial_code\": \"60\",\n" +
                "    \"price\": \"0.248\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"澳洲\",\n" +
                "    \"name\": \"Australia\",\n" +
                "    \"dial_code\": \"61\",\n" +
                "    \"price\": \"0.426\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"印度尼西亚\",\n" +
                "    \"name\": \"Indonesia\",\n" +
                "    \"dial_code\": \"62\",\n" +
                "    \"price\": \"0.193\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"菲律宾\",\n" +
                "    \"name\": \"Philippines\",\n" +
                "    \"dial_code\": \"63\",\n" +
                "    \"price\": \"0.162\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"新西兰\",\n" +
                "    \"name\": \"New Zealand\",\n" +
                "    \"dial_code\": \"64\",\n" +
                "    \"price\": \"0.76\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"新加坡\",\n" +
                "    \"name\": \"Singapore\",\n" +
                "    \"dial_code\": \"65\",\n" +
                "    \"price\": \"0.253\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"泰国\",\n" +
                "    \"name\": \"Thailand\",\n" +
                "    \"dial_code\": \"66\",\n" +
                "    \"price\": \"0.152\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"日本\",\n" +
                "    \"name\": \"Japan\",\n" +
                "    \"dial_code\": \"81\",\n" +
                "    \"price\": \"0.455\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"韩国\",\n" +
                "    \"name\": \"Korea, Republic of\",\n" +
                "    \"dial_code\": \"82\",\n" +
                "    \"price\": \"0.121\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"越南\",\n" +
                "    \"name\": \"Vietnam\",\n" +
                "    \"dial_code\": \"84\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"土耳其\",\n" +
                "    \"name\": \"Turkey\",\n" +
                "    \"dial_code\": \"90\",\n" +
                "    \"price\": \"0.152\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"印度\",\n" +
                "    \"name\": \"India\",\n" +
                "    \"dial_code\": \"91\",\n" +
                "    \"price\": \"0.051\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"巴基斯坦\",\n" +
                "    \"name\": \"Pakistan\",\n" +
                "    \"dial_code\": \"92\",\n" +
                "    \"price\": \"0.182\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"阿富汗\",\n" +
                "    \"name\": \"Afghanistan\",\n" +
                "    \"dial_code\": \"93\",\n" +
                "    \"price\": \"0.628\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"斯里兰卡\",\n" +
                "    \"name\": \"Sri Lanka\",\n" +
                "    \"dial_code\": \"94\",\n" +
                "    \"price\": \"0.49\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"缅甸\",\n" +
                "    \"name\": \"Myanmar\",\n" +
                "    \"dial_code\": \"95\",\n" +
                "    \"price\": \"0.921\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"伊朗\",\n" +
                "    \"name\": \"Iran, Islamic Republic of\",\n" +
                "    \"dial_code\": \"98\",\n" +
                "    \"price\": \"0.253\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"南苏丹\",\n" +
                "    \"name\": \"South Sudan\",\n" +
                "    \"dial_code\": \"211\",\n" +
                "    \"price\": \"0.314\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"摩洛哥\",\n" +
                "    \"name\": \"Morocco\",\n" +
                "    \"dial_code\": \"212\",\n" +
                "    \"price\": \"0.634\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"阿尔及利亚\",\n" +
                "    \"name\": \"Algeria\",\n" +
                "    \"dial_code\": \"213\",\n" +
                "    \"price\": \"0.556\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"突尼斯\",\n" +
                "    \"name\": \"Tunisia\",\n" +
                "    \"dial_code\": \"216\",\n" +
                "    \"price\": \"0.517\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"利比亚\",\n" +
                "    \"name\": \"Libya\",\n" +
                "    \"dial_code\": \"218\",\n" +
                "    \"price\": \"0.486\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"冈比亚\",\n" +
                "    \"name\": \"Gambia\",\n" +
                "    \"dial_code\": \"220\",\n" +
                "    \"price\": \"0.131\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"塞内加尔\",\n" +
                "    \"name\": \"Senegal\",\n" +
                "    \"dial_code\": \"221\",\n" +
                "    \"price\": \"0.385\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"毛里塔尼亚\",\n" +
                "    \"name\": \"Mauritania\",\n" +
                "    \"dial_code\": \"222\",\n" +
                "    \"price\": \"0.709\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"马里共和国\",\n" +
                "    \"name\": \"Mali\",\n" +
                "    \"dial_code\": \"223\",\n" +
                "    \"price\": \"0.765\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"几内亚\",\n" +
                "    \"name\": \"Guinea\",\n" +
                "    \"dial_code\": \"224\",\n" +
                "    \"price\": \"0.905\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"科特迪瓦\",\n" +
                "    \"name\": \"Côte d'Ivoire\",\n" +
                "    \"dial_code\": \"225\",\n" +
                "    \"price\": \"0.709\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"布基纳法索\",\n" +
                "    \"name\": \"Burkina Faso\",\n" +
                "    \"dial_code\": \"226\",\n" +
                "    \"price\": \"0.282\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"尼日\",\n" +
                "    \"name\": \"Niger\",\n" +
                "    \"dial_code\": \"227\",\n" +
                "    \"price\": \"0.584\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"多哥\",\n" +
                "    \"name\": \"Togo\",\n" +
                "    \"dial_code\": \"228\",\n" +
                "    \"price\": \"0.282\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"贝宁\",\n" +
                "    \"name\": \"Benin\",\n" +
                "    \"dial_code\": \"229\",\n" +
                "    \"price\": \"0.493\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"毛里求斯\",\n" +
                "    \"name\": \"Mauritius\",\n" +
                "    \"dial_code\": \"230\",\n" +
                "    \"price\": \"0.359\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"利比里亚\",\n" +
                "    \"name\": \"Liberia\",\n" +
                "    \"dial_code\": \"231\",\n" +
                "    \"price\": \"0.569\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"狮子山共和国\",\n" +
                "    \"name\": \"Sierra Leone\",\n" +
                "    \"dial_code\": \"232\",\n" +
                "    \"price\": \"0.244\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"加纳\",\n" +
                "    \"name\": \"Ghana\",\n" +
                "    \"dial_code\": \"233\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"尼日利亚\",\n" +
                "    \"name\": \"Nigeria\",\n" +
                "    \"dial_code\": \"234\",\n" +
                "    \"price\": \"0.122\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"查德\",\n" +
                "    \"name\": \"Chad\",\n" +
                "    \"dial_code\": \"235\",\n" +
                "    \"price\": \"0.264\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"中非共和国\",\n" +
                "    \"name\": \"Central African Republic\",\n" +
                "    \"dial_code\": \"236\",\n" +
                "    \"price\": \"0.138\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"喀麦隆\",\n" +
                "    \"name\": \"Cameroon\",\n" +
                "    \"dial_code\": \"237\",\n" +
                "    \"price\": \"0.616\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"佛得角\",\n" +
                "    \"name\": \"Cape Verde\",\n" +
                "    \"dial_code\": \"238\",\n" +
                "    \"price\": \"0.633\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"圣多美普林西比\",\n" +
                "    \"name\": \"Sao Tome and Principe\",\n" +
                "    \"dial_code\": \"239\",\n" +
                "    \"price\": \"1.063\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"赤道几内亚\",\n" +
                "    \"name\": \"Equatorial Guinea\",\n" +
                "    \"dial_code\": \"240\",\n" +
                "    \"price\": \"0.087\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"加蓬\",\n" +
                "    \"name\": \"Gabon\",\n" +
                "    \"dial_code\": \"241\",\n" +
                "    \"price\": \"0.44\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"刚果共和国\",\n" +
                "    \"name\": \"Congo\",\n" +
                "    \"dial_code\": \"242\",\n" +
                "    \"price\": \"0.433\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"刚果民主共和国\",\n" +
                "    \"name\": \"Congo, the Democratic Republic of the\",\n" +
                "    \"dial_code\": \"243\",\n" +
                "    \"price\": \"0.294\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"安哥拉\",\n" +
                "    \"name\": \"Angola\",\n" +
                "    \"dial_code\": \"244\",\n" +
                "    \"price\": \"0.303\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"几内亚比绍\",\n" +
                "    \"name\": \"Guinea \",\n" +
                "    \"dial_code\": \"245\",\n" +
                "    \"price\": \"1.229\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"阿森松岛\",\n" +
                "    \"name\": \"Ascension island\",\n" +
                "    \"dial_code\": \"247\",\n" +
                "    \"price\": \"1.013\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"塞舌尔\",\n" +
                "    \"name\": \"Seychelles\",\n" +
                "    \"dial_code\": \"248\",\n" +
                "    \"price\": \"0.264\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"苏丹\",\n" +
                "    \"name\": \"Sudan\",\n" +
                "    \"dial_code\": \"249\",\n" +
                "    \"price\": \"0.362\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"卢旺达\",\n" +
                "    \"name\": \"Rwanda\",\n" +
                "    \"dial_code\": \"250\",\n" +
                "    \"price\": \"0.434\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"埃塞俄比亚\",\n" +
                "    \"name\": \"Ethiopia\",\n" +
                "    \"dial_code\": \"251\",\n" +
                "    \"price\": \"0.577\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"索马里\",\n" +
                "    \"name\": \"Somalia\",\n" +
                "    \"dial_code\": \"252\",\n" +
                "    \"price\": \"0.544\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"吉布提\",\n" +
                "    \"name\": \"Djibouti\",\n" +
                "    \"dial_code\": \"253\",\n" +
                "    \"price\": \"0.856\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"肯尼亚\",\n" +
                "    \"name\": \"Kenya\",\n" +
                "    \"dial_code\": \"254\",\n" +
                "    \"price\": \"0.285\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"坦桑尼亚\",\n" +
                "    \"name\": \"Tanzania, United Republic of\",\n" +
                "    \"dial_code\": \"255\",\n" +
                "    \"price\": \"0.418\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"乌干达\",\n" +
                "    \"name\": \"Uganda\",\n" +
                "    \"dial_code\": \"256\",\n" +
                "    \"price\": \"0.44\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"布隆迪\",\n" +
                "    \"name\": \"Burundi\",\n" +
                "    \"dial_code\": \"257\",\n" +
                "    \"price\": \"0.709\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"莫桑比克\",\n" +
                "    \"name\": \"Mozambique\",\n" +
                "    \"dial_code\": \"258\",\n" +
                "    \"price\": \"0.138\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"赞比亚\",\n" +
                "    \"name\": \"Zambia\",\n" +
                "    \"dial_code\": \"260\",\n" +
                "    \"price\": \"0.335\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"马达加斯加\",\n" +
                "    \"name\": \"Madagascar\",\n" +
                "    \"dial_code\": \"261\",\n" +
                "    \"price\": \"0.264\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"留尼汪 (法国)\",\n" +
                "    \"name\": \"Réunion\",\n" +
                "    \"dial_code\": \"262\",\n" +
                "    \"price\": \"1.367\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"津巴布韦\",\n" +
                "    \"name\": \"Republic of Zimbabwe\",\n" +
                "    \"dial_code\": \"263\",\n" +
                "    \"price\": \"0.506\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"纳米比亚\",\n" +
                "    \"name\": \"Namibia\",\n" +
                "    \"dial_code\": \"264\",\n" +
                "    \"price\": \"0.324\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"马拉维\",\n" +
                "    \"name\": \"Malawi\",\n" +
                "    \"dial_code\": \"265\",\n" +
                "    \"price\": \"0.385\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"莱索托\",\n" +
                "    \"name\": \"Lesotho\",\n" +
                "    \"dial_code\": \"266\",\n" +
                "    \"price\": \"0.709\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"博茨瓦纳\",\n" +
                "    \"name\": \"Botswana\",\n" +
                "    \"dial_code\": \"267\",\n" +
                "    \"price\": \"0.601\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"斯威士兰\",\n" +
                "    \"name\": \"Swaziland\",\n" +
                "    \"dial_code\": \"268\",\n" +
                "    \"price\": \"0.239\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"科摩罗\",\n" +
                "    \"name\": \"Comoros\",\n" +
                "    \"dial_code\": \"269\",\n" +
                "    \"price\": \"0.146\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"马约特\",\n" +
                "    \"name\": \"Mayotte\",\n" +
                "    \"dial_code\": \"269\",\n" +
                "    \"price\": \"1.62\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"阿鲁巴 (荷兰王国)\",\n" +
                "    \"name\": \"Aruba\",\n" +
                "    \"dial_code\": \"297\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"法罗群岛 (丹麦)\",\n" +
                "    \"name\": \"Faroe Islands\",\n" +
                "    \"dial_code\": \"298\",\n" +
                "    \"price\": \"0.132\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"格陵兰 (丹麦)\",\n" +
                "    \"name\": \"Greenland\",\n" +
                "    \"dial_code\": \"299\",\n" +
                "    \"price\": \"0.105\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"直布罗陀 (英国)\",\n" +
                "    \"name\": \"Gibraltar\",\n" +
                "    \"dial_code\": \"350\",\n" +
                "    \"price\": \"0.117\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"葡萄牙\",\n" +
                "    \"name\": \"Portugal\",\n" +
                "    \"dial_code\": \"351\",\n" +
                "    \"price\": \"0.406\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"卢森堡\",\n" +
                "    \"name\": \"Luxembourg\",\n" +
                "    \"dial_code\": \"352\",\n" +
                "    \"price\": \"0.13\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"马其顿\",\n" +
                "    \"name\": \"Macedonia, the former Yugoslav Republic of\",\n" +
                "    \"dial_code\": \"389\",\n" +
                "    \"price\": \"0.506\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"爱尔兰共和国\",\n" +
                "    \"name\": \"Ireland\",\n" +
                "    \"dial_code\": \"353\",\n" +
                "    \"price\": \"0.365\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"冰岛\",\n" +
                "    \"name\": \"Iceland\",\n" +
                "    \"dial_code\": \"354\",\n" +
                "    \"price\": \"0.229\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"阿尔巴尼亚\",\n" +
                "    \"name\": \"Albania\",\n" +
                "    \"dial_code\": \"355\",\n" +
                "    \"price\": \"0.703\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"马耳他\",\n" +
                "    \"name\": \"Malta\",\n" +
                "    \"dial_code\": \"356\",\n" +
                "    \"price\": \"0.244\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"塞浦路斯\",\n" +
                "    \"name\": \"Cyprus\",\n" +
                "    \"dial_code\": \"357\",\n" +
                "    \"price\": \"0.566\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"芬兰\",\n" +
                "    \"name\": \"Finland\",\n" +
                "    \"dial_code\": \"358\",\n" +
                "    \"price\": \"0.669\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"保加利亚\",\n" +
                "    \"name\": \"Bulgaria\",\n" +
                "    \"dial_code\": \"359\",\n" +
                "    \"price\": \"0.659\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"立陶宛\",\n" +
                "    \"name\": \"Lithuania\",\n" +
                "    \"dial_code\": \"370\",\n" +
                "    \"price\": \"0.202\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"拉脱维亚\",\n" +
                "    \"name\": \"Latvia\",\n" +
                "    \"dial_code\": \"371\",\n" +
                "    \"price\": \"0.37\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"爱沙尼亚\",\n" +
                "    \"name\": \"Estonia\",\n" +
                "    \"dial_code\": \"372\",\n" +
                "    \"price\": \"0.686\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"摩尔多瓦\",\n" +
                "    \"name\": \"Moldova, Republic of\",\n" +
                "    \"dial_code\": \"373\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"亚美尼亚\",\n" +
                "    \"name\": \"Armenia\",\n" +
                "    \"dial_code\": \"374\",\n" +
                "    \"price\": \"0.709\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"白俄罗斯\",\n" +
                "    \"name\": \"Belarus\",\n" +
                "    \"dial_code\": \"375\",\n" +
                "    \"price\": \"0.487\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"安道尔\",\n" +
                "    \"name\": \"Andorra\",\n" +
                "    \"dial_code\": \"376\",\n" +
                "    \"price\": \"0.118\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"摩纳哥\",\n" +
                "    \"name\": \"Monaco\",\n" +
                "    \"dial_code\": \"377\",\n" +
                "    \"price\": \"0.76\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"圣马力诺\",\n" +
                "    \"name\": \"San Marino\",\n" +
                "    \"dial_code\": \"378\",\n" +
                "    \"price\": \"0.607\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"乌克兰\",\n" +
                "    \"name\": \"Ukraine\",\n" +
                "    \"dial_code\": \"380\",\n" +
                "    \"price\": \"0.506\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"塞尔维亚共和国\",\n" +
                "    \"name\": \"Serbia\",\n" +
                "    \"dial_code\": \"381\",\n" +
                "    \"price\": \"0.185\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"黑山共和国\",\n" +
                "    \"name\": \"Montenegro\",\n" +
                "    \"dial_code\": \"382\",\n" +
                "    \"price\": \"0.206\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"克罗地亚\",\n" +
                "    \"name\": \"Croatia\",\n" +
                "    \"dial_code\": \"385\",\n" +
                "    \"price\": \"0.337\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"斯洛文尼亚\",\n" +
                "    \"name\": \"Slovenia\",\n" +
                "    \"dial_code\": \"386\",\n" +
                "    \"price\": \"0.226\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"波斯尼亚与赫塞哥维纳\",\n" +
                "    \"name\": \"Bosnia and Herzegovina\",\n" +
                "    \"dial_code\": \"387\",\n" +
                "    \"price\": \"0.535\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"捷克\",\n" +
                "    \"name\": \"Czech Republic\",\n" +
                "    \"dial_code\": \"420\",\n" +
                "    \"price\": \"0.295\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"斯洛伐克\",\n" +
                "    \"name\": \"Slovakia\",\n" +
                "    \"dial_code\": \"421\",\n" +
                "    \"price\": \"0.505\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"列支敦士登\",\n" +
                "    \"name\": \"Liechtenstein\",\n" +
                "    \"dial_code\": \"423\",\n" +
                "    \"price\": \"0.246\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"伯利兹\",\n" +
                "    \"name\": \"Belize\",\n" +
                "    \"dial_code\": \"501\",\n" +
                "    \"price\": \"0.1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"危地马拉\",\n" +
                "    \"name\": \"Guatemala\",\n" +
                "    \"dial_code\": \"502\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"萨尔瓦多\",\n" +
                "    \"name\": \"El Salvador\",\n" +
                "    \"dial_code\": \"503\",\n" +
                "    \"price\": \"0.352\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"洪都拉斯\",\n" +
                "    \"name\": \"Honduras\",\n" +
                "    \"dial_code\": \"504\",\n" +
                "    \"price\": \"0.414\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"尼加拉瓜\",\n" +
                "    \"name\": \"Nicaragua\",\n" +
                "    \"dial_code\": \"505\",\n" +
                "    \"price\": \"0.515\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"哥斯达黎加\",\n" +
                "    \"name\": \"Costa Rica\",\n" +
                "    \"dial_code\": \"506\",\n" +
                "    \"price\": \"0.387\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"巴拿马\",\n" +
                "    \"name\": \"Panama\",\n" +
                "    \"dial_code\": \"507\",\n" +
                "    \"price\": \"0.487\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"圣皮耶与密克隆群岛 (法国)\",\n" +
                "    \"name\": \"Saint Pierre and Miquelon\",\n" +
                "    \"dial_code\": \"508\",\n" +
                "    \"price\": \"0.506\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"海地\",\n" +
                "    \"name\": \"Haiti\",\n" +
                "    \"dial_code\": \"509\",\n" +
                "    \"price\": \"0.567\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"瓜德罗普岛\",\n" +
                "    \"name\": \"Guadeloupe\",\n" +
                "    \"dial_code\": \"590\",\n" +
                "    \"price\": \"1.282\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"圣马丁岛(荷兰部分)\",\n" +
                "    \"name\": \"Sint Maarten(Dutch)\",\n" +
                "    \"dial_code\": \"590\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"玻利维亚\",\n" +
                "    \"name\": \"Bolivia, Plurinational\",\n" +
                "    \"dial_code\": \"591\",\n" +
                "    \"price\": \"0.383\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"圭亚那\",\n" +
                "    \"name\": \"Guyana\",\n" +
                "    \"dial_code\": \"592\",\n" +
                "    \"price\": \"0.382\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"厄瓜多尔\",\n" +
                "    \"name\": \"Ecuador\",\n" +
                "    \"dial_code\": \"593\",\n" +
                "    \"price\": \"0.601\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"法属圭亚那 (法国)\",\n" +
                "    \"name\": \"French Guiana\",\n" +
                "    \"dial_code\": \"594\",\n" +
                "    \"price\": \"1.246\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"巴拉圭\",\n" +
                "    \"name\": \"Paraguay\",\n" +
                "    \"dial_code\": \"595\",\n" +
                "    \"price\": \"0.185\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"马提尼克 (法国)\",\n" +
                "    \"name\": \"Martinique\",\n" +
                "    \"dial_code\": \"596\",\n" +
                "    \"price\": \"1.013\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"苏里南\",\n" +
                "    \"name\": \"Suriname\",\n" +
                "    \"dial_code\": \"597\",\n" +
                "    \"price\": \"0.162\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"乌拉圭\",\n" +
                "    \"name\": \"Uruguay\",\n" +
                "    \"dial_code\": \"598\",\n" +
                "    \"price\": \"0.607\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"博内尔岛，圣尤斯特歇斯和\",\n" +
                "    \"name\": \"Bonaire Sint Eustat\",\n" +
                "    \"dial_code\": \"599\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"库拉索 (荷兰王国)\",\n" +
                "    \"name\": \"Curacao\",\n" +
                "    \"dial_code\": \"599\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"东帝汶\",\n" +
                "    \"name\": \"Timor \",\n" +
                "    \"dial_code\": \"670\",\n" +
                "    \"price\": \"0.703\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"文莱\",\n" +
                "    \"name\": \"Brunei Darussalam\",\n" +
                "    \"dial_code\": \"673\",\n" +
                "    \"price\": \"0.156\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"巴布亚新几内亚\",\n" +
                "    \"name\": \"Papua New Guinea\",\n" +
                "    \"dial_code\": \"675\",\n" +
                "    \"price\": \"0.365\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"东加\",\n" +
                "    \"name\": \"Tonga\",\n" +
                "    \"dial_code\": \"676\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"所罗门群岛\",\n" +
                "    \"name\": \"Solomon Islands\",\n" +
                "    \"dial_code\": \"677\",\n" +
                "    \"price\": \"0.65\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"瓦努阿图\",\n" +
                "    \"name\": \"Vanuatu\",\n" +
                "    \"dial_code\": \"678\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"斐济\",\n" +
                "    \"name\": \"Fiji\",\n" +
                "    \"dial_code\": \"679\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"帕劳\",\n" +
                "    \"name\": \"Palau\",\n" +
                "    \"dial_code\": \"680\",\n" +
                "    \"price\": \"0.569\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"库克群岛 (新西兰)\",\n" +
                "    \"name\": \"Cook Islands\",\n" +
                "    \"dial_code\": \"682\",\n" +
                "    \"price\": \"0.212\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"萨摩亚\",\n" +
                "    \"name\": \"Samoa\",\n" +
                "    \"dial_code\": \"685\",\n" +
                "    \"price\": \"0.446\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"基里巴斯\",\n" +
                "    \"name\": \"Kiribati\",\n" +
                "    \"dial_code\": \"686\",\n" +
                "    \"price\": \"0.219\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"新喀里多尼亚 (法国)\",\n" +
                "    \"name\": \"New Caledonia\",\n" +
                "    \"dial_code\": \"687\",\n" +
                "    \"price\": \"1.154\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"法属波利尼西亚 (法国)\",\n" +
                "    \"name\": \"French Polynesia\",\n" +
                "    \"dial_code\": \"689\",\n" +
                "    \"price\": \"1.39\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"香港 (中华人民共和国)\",\n" +
                "    \"name\": \"Hong Kong\",\n" +
                "    \"dial_code\": \"852\",\n" +
                "    \"price\": \"0.273\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"澳门 (中华人民共和国)\",\n" +
                "    \"name\": \"Macao\",\n" +
                "    \"dial_code\": \"853\",\n" +
                "    \"price\": \"0.152\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"柬埔寨\",\n" +
                "    \"name\": \"Cambodia\",\n" +
                "    \"dial_code\": \"855\",\n" +
                "    \"price\": \"0.416\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"老挝\",\n" +
                "    \"name\": \"Lao People's Democratic Republic\",\n" +
                "    \"dial_code\": \"856\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"孟加拉国\",\n" +
                "    \"name\": \"Bangladesh\",\n" +
                "    \"dial_code\": \"880\",\n" +
                "    \"price\": \"0.568\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"台湾\",\n" +
                "    \"name\": \"Taiwan, Province of China\",\n" +
                "    \"dial_code\": \"886\",\n" +
                "    \"price\": \"0.202\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"巴勒斯坦\",\n" +
                "    \"name\": \"Palestine, State of\",\n" +
                "    \"dial_code\": \"930\",\n" +
                "    \"price\": \"0.569\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"马尔代夫\",\n" +
                "    \"name\": \"Maldives\",\n" +
                "    \"dial_code\": \"960\",\n" +
                "    \"price\": \"0.105\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"黎巴嫩\",\n" +
                "    \"name\": \"Lebanon\",\n" +
                "    \"dial_code\": \"961\",\n" +
                "    \"price\": \"0.334\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"约旦\",\n" +
                "    \"name\": \"Jordan\",\n" +
                "    \"dial_code\": \"962\",\n" +
                "    \"price\": \"0.488\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"叙利亚\",\n" +
                "    \"name\": \"Syrian Arab Republic\",\n" +
                "    \"dial_code\": \"963\",\n" +
                "    \"price\": \"0.502\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"伊拉克\",\n" +
                "    \"name\": \"Iraq\",\n" +
                "    \"dial_code\": \"964\",\n" +
                "    \"price\": \"0.506\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"科威特\",\n" +
                "    \"name\": \"Kuwait\",\n" +
                "    \"dial_code\": \"965\",\n" +
                "    \"price\": \"0.247\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"沙特阿拉伯\",\n" +
                "    \"name\": \"Saudi Arabia\",\n" +
                "    \"dial_code\": \"966\",\n" +
                "    \"price\": \"0.193\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"也门\",\n" +
                "    \"name\": \"Yemen\",\n" +
                "    \"dial_code\": \"967\",\n" +
                "    \"price\": \"0.506\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"阿曼\",\n" +
                "    \"name\": \"Oman\",\n" +
                "    \"dial_code\": \"968\",\n" +
                "    \"price\": \"0.701\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"阿拉伯联合酋长国\",\n" +
                "    \"name\": \"United Arab Emirates\",\n" +
                "    \"dial_code\": \"971\",\n" +
                "    \"price\": \"0.233\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"以色列\",\n" +
                "    \"name\": \"Israel\",\n" +
                "    \"dial_code\": \"972\",\n" +
                "    \"price\": \"0.569\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"巴林\",\n" +
                "    \"name\": \"Bahrain\",\n" +
                "    \"dial_code\": \"973\",\n" +
                "    \"price\": \"0.177\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"卡达\",\n" +
                "    \"name\": \"Qatar\",\n" +
                "    \"dial_code\": \"974\",\n" +
                "    \"price\": \"0.283\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"不丹\",\n" +
                "    \"name\": \"Bhutan\",\n" +
                "    \"dial_code\": \"975\",\n" +
                "    \"price\": \"0.378\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"蒙古国\",\n" +
                "    \"name\": \"Mongolia\",\n" +
                "    \"dial_code\": \"976\",\n" +
                "    \"price\": \"0.395\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"尼泊尔\",\n" +
                "    \"name\": \"Nepal\",\n" +
                "    \"dial_code\": \"977\",\n" +
                "    \"price\": \"0.352\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"塔吉克\",\n" +
                "    \"name\": \"Tajikistan\",\n" +
                "    \"dial_code\": \"992\",\n" +
                "    \"price\": \"0.539\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"土库曼\",\n" +
                "    \"name\": \"Turkmenistan\",\n" +
                "    \"dial_code\": \"993\",\n" +
                "    \"price\": \"0.709\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"阿塞拜疆\",\n" +
                "    \"name\": \"Azerbaijan\",\n" +
                "    \"dial_code\": \"994\",\n" +
                "    \"price\": \"1.071\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"乔治亚\",\n" +
                "    \"name\": \"Georgia\",\n" +
                "    \"dial_code\": \"995\",\n" +
                "    \"price\": \"0.179\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"吉尔吉斯\",\n" +
                "    \"name\": \"Kyrgyzstan\",\n" +
                "    \"dial_code\": \"996\",\n" +
                "    \"price\": \"0.324\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"乌兹别克\",\n" +
                "    \"name\": \"Uzbekistan\",\n" +
                "    \"dial_code\": \"998\",\n" +
                "    \"price\": \"0.361\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"巴哈马\",\n" +
                "    \"name\": \"Bahamas\",\n" +
                "    \"dial_code\": \"1242\",\n" +
                "    \"price\": \"0.276\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"巴巴多斯\",\n" +
                "    \"name\": \"Barbados\",\n" +
                "    \"dial_code\": \"1246\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"安圭拉\",\n" +
                "    \"name\": \"Anguilla\",\n" +
                "    \"dial_code\": \"1264\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"安提瓜和巴布达\",\n" +
                "    \"name\": \"Antigua and Barbuda\",\n" +
                "    \"dial_code\": \"1268\",\n" +
                "    \"price\": \"0.369\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"英属维尔京群岛 (英国)\",\n" +
                "    \"name\": \"Virgin Islands, U.S.\",\n" +
                "    \"dial_code\": \"1284\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"美属维尔京群岛 (美国)\",\n" +
                "    \"name\": \"United States Virgin Islands\",\n" +
                "    \"dial_code\": \"1340\",\n" +
                "    \"price\": \"0.182\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"开曼群岛 (英国)\",\n" +
                "    \"name\": \"Cayman Islands\",\n" +
                "    \"dial_code\": \"1345\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"百慕大 (英国)\",\n" +
                "    \"name\": \"Bermuda\",\n" +
                "    \"dial_code\": \"1441\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"格林纳达\",\n" +
                "    \"name\": \"Grenada\",\n" +
                "    \"dial_code\": \"1473\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"土克凯可群岛 (英国)\",\n" +
                "    \"name\": \"Turks and Caicos Islands\",\n" +
                "    \"dial_code\": \"1649\",\n" +
                "    \"price\": \"0.279\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"蒙塞拉特岛 (英国)\",\n" +
                "    \"name\": \"Montserrat\",\n" +
                "    \"dial_code\": \"1664\",\n" +
                "    \"price\": \"0.412\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"关岛 (美国)\",\n" +
                "    \"name\": \"Guam\",\n" +
                "    \"dial_code\": \"1671\",\n" +
                "    \"price\": \"0.354\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"美属萨摩亚 (美国)\",\n" +
                "    \"name\": \"American Samoa\",\n" +
                "    \"dial_code\": \"1684\",\n" +
                "    \"price\": \"0.63\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"圣卢西亚\",\n" +
                "    \"name\": \"Saint Lucia\",\n" +
                "    \"dial_code\": \"1758\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"多米尼克\",\n" +
                "    \"name\": \"Dominica\",\n" +
                "    \"dial_code\": \"1767\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"圣文森及格林纳丁\",\n" +
                "    \"name\": \"Saint Vincent and the Grenadines\",\n" +
                "    \"dial_code\": \"1784\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"波多黎各 (美国)\",\n" +
                "    \"name\": \"Puerto Rico\",\n" +
                "    \"dial_code\": \"1787\",\n" +
                "    \"price\": \"0.081\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"多米尼加共和国\",\n" +
                "    \"name\": \"Dominican Republic\",\n" +
                "    \"dial_code\": \"1809\",\n" +
                "    \"price\": \"0.439\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"特立尼达和多巴哥\",\n" +
                "    \"name\": \"Trinidad and Tobago\",\n" +
                "    \"dial_code\": \"1868\",\n" +
                "    \"price\": \"0.28\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"圣克里斯多福与尼维斯\",\n" +
                "    \"name\": \"Saint Kitts and Nevis\",\n" +
                "    \"dial_code\": \"1869\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cn_name\": \"牙买加\",\n" +
                "    \"name\": \"Jamaica\",\n" +
                "    \"dial_code\": \"1876\",\n" +
                "    \"price\": \"0.405\"\n" +
                "  }\n" +
                "]";

        return JSON.parseArray(nationStr, NationCodeBean.class);
    }
}