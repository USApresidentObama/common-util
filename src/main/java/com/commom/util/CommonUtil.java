package com.commom.util;

public class CommonUtil {
    /**
     * 为空处理.如果t为空,则返回defaultValue.
     *
     * @param t
     *            参数
     * @param defaultValue
     *            默认值
     * @return
     */
    public static <T> T nullToDefault(T t, T defaultValue) {
        if (t != null) {
            return t;
        }
        return defaultValue;
    }
}
