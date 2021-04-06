package com.yumeng.libcommon.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.util.regex.Pattern;

/*
 * 文件名:   PinYinUtils
 * 创建者:   ZSY
 * 创建时间: 2016/11/17 17:51
 * 描述:     得到指定汉字的拼音
 */
public class PinYinUtils {
    private static final String PATTERN_LETTER = "^[a-zA-Z].*+";

    /**
     * 将hanzi转成拼音
     *
     * @param hanzi 汉字或字母
     * @return 拼音
     */
    public static String getPinyin(String hanzi) {
        StringBuilder sb = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //由于不能直接对多个汉子转换，只能对单个汉子转换
        char[] arr = hanzi.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isWhitespace(arr[i])) {
                continue;
            }
            try {
                String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(arr[i], format);
                if (pinyinArr != null) {
                    sb.append(pinyinArr[0]);
                } else {
                    sb.append(arr[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //不是正确的汉字
                sb.append(arr[i]);
            }

        }
        return sb.toString();
    }



    private static final String PATTERN_POLYPHONE = "^#[a-zA-Z]+#.+";

    /**
     * Chinese character -> Pinyin
     */
    public static String getPingYin(String inputString) {
        if (inputString == null) return "";
        return getPinyin(inputString).toLowerCase();
    }

    /**
     * Are start with a letter
     *
     * @return if return false, index should be #
     */
    public static boolean matchingLetter(String inputString) {
        return Pattern.matches(PATTERN_LETTER, inputString);
    }

    public static boolean matchingPolyphone(String inputString) {
        return Pattern.matches(PATTERN_POLYPHONE, inputString);
    }

    public static String gePolyphoneInitial(String inputString) {
        return inputString.substring(1, 2);
    }

    public static String getPolyphoneRealPinyin(String inputString) {
        String[] splits = inputString.split("#");
        return splits[1];
    }

    public static String getPolyphoneRealHanzi(String inputString) {
        String[] splits = inputString.split("#");
        return splits[2];
    }
}
