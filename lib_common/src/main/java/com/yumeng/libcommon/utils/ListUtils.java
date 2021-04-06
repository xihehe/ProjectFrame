package com.yumeng.libcommon.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Chauncey on 2018/1/31 16:11.
 */
public class ListUtils {
    /**
     * long 型list转换为  10000,20000,3000,40000 这种
     * @param strings
     * @return
     */
    public static String longTypeListToString(List<Long> strings) {
        String format;
        if (strings == null || strings.size() == 0) {
            format = null;
        } else {
            String temp = Arrays.toString(strings.toArray());
            format = temp.substring(1, temp.length() - 1);
        }
        return format;
    }

    /**
     * integer 型list转换为  10000,20000,3000,40000 这种
     * @param strings
     * @return
     */
    public static String integerListToString(List<Integer> strings) {
        String format;
        if (strings == null || strings.size() == 0) {
            format = null;
        } else {
            String temp = Arrays.toString(strings.toArray());
            format = temp.substring(1, temp.length() - 1);
        }
        return format;
    }


    /**
     * String 型list转换为  10000,20000,3000,40000 这种 可以用separator 更替 ,
     * @param strings
     * @param separator
     * @return
     */
    public static String listToString(List<String> strings, String separator) {
        if (strings == null) {
            return "";
        }
        String temp = Arrays.toString(strings.toArray());
        return temp.replace("[", "")
                .replace("]", "")
                .replace("null", "")
                .replace(",", separator);
    }
}
