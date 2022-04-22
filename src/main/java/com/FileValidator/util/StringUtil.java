package com.FileValidator.util;

public class StringUtil {

    private StringUtil() {

    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0 ? true : false;
    }
}
