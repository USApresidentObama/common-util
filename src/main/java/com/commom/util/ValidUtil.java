package com.commom.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 校验工具类
 *
 */
public class ValidUtil {

    /**
     * 编译mail和tel的正则
     */
    private static final Pattern mailPattern = Pattern
            .compile("^[a-zA-Z0-9][A-Za-z0-9!#$%&'*+/=?^_`{|}~-]*(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+([A-Za-z](?:[A-Za-z0-9-]*[A-Za-z0-9])?|[0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?[A-Za-z](?:[A-Za-z0-9-]*[A-Za-z0-9])?)$");

    /**
     * 纳税人识别号正则
     */
    private static final Pattern taxpayerIdPattern = Pattern.compile("^[a-zA-Z0-9]{15,20}$");

    // --------------------------基本数据/集合/...检验

    /**
     * Integer是否有效(非空且大于0)
     *
     * @param i
     *            {@link Integer}
     * @return boolean
     */
    public static boolean isValid(Integer i) {
        return i != null && i > 0;
    }

    /**
     * Long是否有效(非空且大于0)
     *
     * @param i
     *            {@link Long}
     * @return boolean
     */
    public static boolean isValid(Long i) {
        return i != null && i > 0;
    }

    /**
     * Float是否有效(非空且大于0)
     *
     * @param i
     *            {@link Float}
     * @return boolean
     */
    public static boolean isValid(Float i) {
        return i != null && i > 0;
    }

    /**
     * Integer是否无效(为空或小于等于0)
     *
     * @param i
     *            {@link Integer}
     * @return boolean
     */
    public static boolean isInvalid(Integer i) {
        return !isValid(i);
    }

    /**
     * Float是否无效(为空或小于等于0)
     *
     * @param i
     *            {@link Float}
     * @return boolean
     */
    public static boolean isInvalid(Float i) {
        return !isValid(i);
    }

    /**
     * Double是否无效(为空或小于等于0)
     *
     * @param doubleArray
     *            Double数组
     * @return boolean
     */
    public static boolean isInvalid(Double... doubleArray) {
        if (doubleArray == null || doubleArray.length == 0) {
            return false;
        }
        for (int i = 0; i < doubleArray.length; i++) {
            if (doubleArray[i] == null || doubleArray[i] <= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证是否错误的Integer,如果为空或小于0则视为无效
     *
     * @param intArray
     *            Integer 数组
     * @return boolean
     */
    public static boolean isInvalid(Integer... intArray) {
        if (intArray == null || intArray.length == 0) {
            return false;
        }
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == null || intArray[i] <= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证是否错误的Long ，如果为空或小于0则视为无效
     *
     * @param intArray
     *            {@link Long}数组
     * @return boolean
     */
    public static boolean isInvalid(Long... intArray) {
        if (intArray == null || intArray.length == 0) {
            return false;
        }
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == null || intArray[i] <= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证集合是否为空
     *
     * @param collection
     *            {@link Collection}
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 校验 Map 是否为空
     *
     * @param map
     *            待校验的 {@link Map}
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 验证集合是否非空
     *
     *
     * @param collection
     *            {@link Collection}
     * @return boolean
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 检查是否为true
     *
     * @param bool
     *            {@link Boolean}对象
     * @return boolean
     */
    public static boolean check(Boolean bool) {
        return bool != null && bool.booleanValue();
    }

    /**
     * 检验 Map是不为空
     *
     * @param map
     *            待校验的 {@link Map}
     * @return boolean
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断字符串是否为空<br>
     * 有一个为""或者 null即返回为true
     *
     * @param strArray
     *            字符串数组
     * @return
     */
    public static boolean isBlank(String... strArray) {
        if (strArray == null || strArray.length == 0) {
            return true;
        }
        for (String str : strArray) {
            if (StringUtil.isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串非空<br>
     * 所有都不为空
     *
     * @param strArray
     *            字符串数组
     * @return
     */
    public static boolean isNotBlank(String... strArray) {
        return !isBlank(strArray);
    }
    // -------------------------- 业务类

    /**
     * 是否为有效邮箱
     *
     * @param mail
     *            String邮箱地址
     * @return boolean
     */
    public static boolean isValidEmail(String mail) {
        if (StringUtil.isBlank(mail)) {
            return false;
        }
        if (!mailPattern.matcher(mail).matches()) {
            return false;
        }
        return true;
    }


    /**
     * 是否为纳税人ID
     *
     * @param str
     * @return
     */
    public static boolean isTaxpayerId(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        if (!taxpayerIdPattern.matcher(str).matches()) {
            return false;
        }
        return true;
    }
}
