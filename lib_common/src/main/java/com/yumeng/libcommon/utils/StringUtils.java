package com.yumeng.libcommon.utils;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.widget.Chronometer;


import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Administrator on 2016/07/26.
 */
public class StringUtils {
    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     *
     * @param str 无逗号的数字
     * @return 加上逗号的数字
     */
    public static String addComma(String str) {
        long round = Math.round(Double.parseDouble(str));

        // 将传进数字反转
        String reverseStr = new StringBuilder(round + "").reverse().toString();
        String strTemp = "";
        for (int i = 0; i < reverseStr.length(); i++) {
            if (i * 3 + 3 > reverseStr.length()) {
                strTemp += reverseStr.substring(i * 3, reverseStr.length());
                break;
            }
            strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
        }
        // 将 【789,456,】 中最后一个【,】去除
        if (strTemp.endsWith(",")) {
            strTemp = strTemp.substring(0, strTemp.length() - 1);
        }
        // 将数字重新反转
        String resultStr = new StringBuilder(strTemp).reverse().toString();
        return resultStr;
    }


    public static boolean isValidLong(String str){
        try{
            long _v = Long.parseLong(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * 转换
     *
     * @param number
     */
    public static String generateNumber(String number) {
        Double target = Double.parseDouble(number);
        if (target > 100000000) {
            return (target / 100000000) + "亿";
        } else if (target > 10000000) {
            return (target / 10000000) + "千万";
        } else if (target > 1000000) {
            return (target / 1000000) + "百万";
        } else if (target > 10000) {
            return (target / 10000) + "万";
        }
        return number;
    }

    public static String getRate(double amount, double total) {
        if ((int) total == 0) {
            return "0";
        }
        DecimalFormat df = new DecimalFormat("0.0000");
        return df.format(amount / total);
    }

    public static String removeZero(double d) {
        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(d);
    }

    public static String removeZero(String s) {
        if (!TextUtils.isEmpty(s)) {
            try {

                double d = Double.parseDouble(s);
                DecimalFormat format = new DecimalFormat("0.#");
                return format.format(d);
            } catch (Exception e) {
                return "0";
            }
        } else {
            return "0";
        }

    }

    /**
     * 隐藏手机号中间四位
     */
    public static String generatePhoneNumber(String phone) {
        String maskNumber = phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
        return maskNumber;
    }

    /**
     * 银行卡总共19位
     * 隐藏前15
     */
    public static String generateBankCardNubmer(String bankCardNumber) {
        String str = "***************" + bankCardNumber.substring(bankCardNumber.length() - 4, bankCardNumber.length());
        return str;
    }

    /**
     * 银行卡总共19位
     * 获取最后四位
     */
    public static String getLast4BankCardNubmer(String bankCardNumber) {
        String str = bankCardNumber.substring(bankCardNumber.length() - 4, bankCardNumber.length());
        return str;
    }

    /**
     * 银行卡总共19位
     * 每4位增加一个空格，最后不加
     */
    public static String setBlankPer4Number(String bankCardNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bankCardNumber.length(); i++) {
            if ((i == 4 || i == 8 || i == 12 || i == 16)) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(bankCardNumber.substring(i, i + 1));
        }
        return stringBuilder.toString();
    }


    /**
     * 判断邮箱是否合法
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }



    //查看是否有空的值
    public static boolean isEmptys(String... editTexts) {
        if (editTexts == null) {
            return true;
        }
        for (String ed : editTexts) {
            String str = ed.trim();
            if (TextUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(String... strings) {
        if (strings == null || strings.length == 0)
            return true;
        for (String ed : strings) {
            String str = ed.trim();
            if (TextUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }


    //其中一项是否有值
    public static boolean isHasValue(String... editTexts) {
        for (String ed : editTexts) {
            String str = ed.trim();
            if (!TextUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    //是否手机号
    public static boolean isPhone(String phoneStr) {
        String regex = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneStr);
        return m.matches();
    }

    /**
     * 匹配Luhn算法：可用于检测银行卡卡号
     *
     * @param cardNo
     * @return
     */
    public static boolean matchLuhn(String cardNo) {
        if (StringUtils.isEmptys(cardNo))
            return false;
        int[] cardNoArr = new int[cardNo.length()];
        for (int i = 0; i < cardNo.length(); i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for (int i = cardNoArr.length - 2; i >= 0; i -= 2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
        }
        int sum = 0;
        for (int i = 0; i < cardNoArr.length; i++) {
            sum += cardNoArr[i];
        }
        return sum % 10 == 0;
    }


    public static boolean paramsIsEmpty(String params, String type) {
        if (TextUtils.isEmpty(params)) {
            ToastUtils.getInstance().shortToast(type + "不能为空！");
            return true;
        } else
            return false;
    }

    public static String StringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    //判断字符串中是否包含@人
//    public static SpannableStringBuilder getSSBFromString(String str, List<User> users) {
//        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
//        int position = -1;
//        for (User user : users) {
//            position = str.indexOf("@" + user.getUserName());
//            if (position != -1) {
//                ssb.replace(position, position + user.getUserName().length() + 1, Weibo.INSTANCE.newSpannable(user));
//            }
//        }
//        return ssb;
//    }

    private static String bytesToHexString(byte[] paramArrayOfByte) {

        StringBuilder localStringBuilder = new StringBuilder();
        for (int i = 0; i < paramArrayOfByte.length; i++)
        {
            String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
            if (str.length() == 1)
                localStringBuilder.append('0');
            localStringBuilder.append(str);
        }
        return localStringBuilder.toString();
    }


    /**
     * is null or its length is 0【字符串为空、长度为0】
     *
     * <pre>
     * isEmpty(null) = true;
     * isEmpty(&quot;&quot;) = true;
     * isEmpty(&quot;  &quot;) = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0, return true, else return false.
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    public static boolean isEqual(String paramString1, String paramString2) {
        return paramString1.equals(paramString2);
    }

    /**
     * get length of CharSequence【字符串的长度】
     *
     * <pre>
     * length(null) = 0;
     * length(\"\") = 0;
     * length(\"abc\") = 3;
     * </pre>
     *
     * @param str
     * @return if str is null or empty, return 0, else return {@link CharSequence#length()}.
     */
    public static int length(CharSequence str) {
        return str == null ? 0 : str.length();
    }


    public  static int getChronometerSeconds(Chronometer cmt) {
        int totalss = 0;
        String string = cmt.getText().toString();
        if(string.length()==7){
            String[] split = string.split(":");
            String string2 = split[0];
            int hour = Integer.parseInt(string2);
            int Hours =hour*3600;
            String string3 = split[1];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS =Integer.parseInt(split[2]);
            totalss = Hours+Mins+SS;
            return totalss;
        }

        else if(string.length()==5){
            String[] split = string.split(":");
            String string3 = split[0];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS =Integer.parseInt(split[1]);

            totalss =Mins+SS;
            return totalss;
        }
        return totalss;
    }


    public static String  dealEllipsisStr(String str, int maxLength) {
        if(str == null)
            return "";
        else{
           // Log.e(str,str.length.toString())
            String lastStr = str;
            if(str.length() > maxLength){
                lastStr = str.substring(0 ,  maxLength);
                lastStr = lastStr + "…";
            }
            return lastStr;
        }
    }
}
