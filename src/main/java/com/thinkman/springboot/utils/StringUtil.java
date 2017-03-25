package com.thinkman.springboot.utils;

/**
 * Created by alex on 16-9-14.
 */
public class StringUtil {
    public static boolean isEmptyString(String str) {
        return null == str || str.trim().equals("");
    }

    public static String trimNullValue(String str) {
        if (str == null || "null".equalsIgnoreCase(str)) {
            return "";
        }
        return str;
    }

    public static String trimInvalidChar(String str) {
        str = trimNullValue(str);
        return str.replaceAll("\\s*", "");
    }

}
