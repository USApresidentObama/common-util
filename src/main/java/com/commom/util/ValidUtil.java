package com.commom.util;

import java.util.Calendar;
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
     * 是否为有效手机
     *
     * @param tel
     *            String 手机号码
     * @return boolean
     */
    public static boolean isValidTel(String tel) {
        if (StringUtil.isBlank(tel)) {
            return false;
        }
        if (MobileState.IS_MOBILE == MobileUtil.validate(tel)) {
            return true;
        }
        return false;
    }

    /**
     * 判断文件的webfile_id 是否为文件系统的id
     *
     * @param fileId
     *            lpfs文件Id
     * @return boolean
     */
    public static boolean isLpfsObjectId(String fileId) {
        if (StringUtil.isBlank(fileId)) {
            return false;
        }
        if (fileId.matches("\\w{27}\\.\\w+$")) {
            return true;
        }
        return false;
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

    /**
     * 判断手机号码是否有效
     *
     * @param mobile
     *            手机号码
     * @param withAmerica
     *            是否判断是否为海外号码
     * @return
     */
    public static boolean isValidMobile(String mobile, boolean withOversea) {
        if (StringUtil.isBlank(mobile)) {
            return false;
        }
        if (MobileUtil.validate(mobile) != MobileState.IS_MOBILE) {
            if (withOversea) {
                return MobileUtil.isAmericaMobile(mobile);
            }
            return false;
        }
        return true;
    }

    /**
     * 检查是否有效的身份证号
     *
     * @param idStr
     *            身份证号
     * @return
     */
    public static boolean isValidIDCard(String idStr) {
        return IDCheck.check(idStr);
    }

    /*********************************** 身份证验证开始 ****************************************/
    /**
     * <pre>
     * 18位身份证标准在国家质量技术监督局于1999年7月1日实施的GB11643-1999《公民身份号码》中做了明确的规定。
     * GB11643-1999《公民身份号码》为GB11643-1989《社会保障号码》的修订版，其中指出将原标准名称"社会保障号码"更名为"公民身份号码"，
     * 另外GB11643-1999《公民身份号码》从实施之日起代替GB11643-1989。
     * GB11643-1999《公民身份号码》主要内容如下：
     * 一、范围
     * 该标准规定了公民身份号码的编码对象、号码的结构和表现形式，使每个编码对象获得一个唯一的、不变的法定号码。
     * 二、编码对象
     * 公民身份号码的编码对象是具有中华人民共和国国籍的公民。
     * 三、号码的结构和表示形式
     * 1、号码的结构
     * 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * 2、地址码
     * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。
     * 3、出生日期码
     * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。
     * 4、顺序码
     * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配给女性。
     * 5、校验码
     * （1）十七位数字本体码加权求和公式
     * S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
     * Ai:表示第i位置上的身份证号码数字值
     * Wi:表示第i位置上的加权因子
     * Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * （2）计算模
     * Y = mod(S, 11)
     *
     * （3）通过模得到对应的校验码
     * Y: 0 1 2 3 4 5 6 7 8 9 10
     * 校验码: 1 0 X 9 8 7 6 5 4 3 2
     * </pre>
     */
    static final class IDCheck {
        final static Map<Integer, String> areaCodeMap = Maps.newHashMap();
        static {
            areaCodeMap.put(11, "北京");
            areaCodeMap.put(12, "天津");
            areaCodeMap.put(13, "河北");
            areaCodeMap.put(14, "山西");
            areaCodeMap.put(15, "内蒙古");
            areaCodeMap.put(21, "辽宁");
            areaCodeMap.put(22, "吉林");
            areaCodeMap.put(23, "黑龙江");
            areaCodeMap.put(31, "上海");
            areaCodeMap.put(32, "江苏");
            areaCodeMap.put(33, "浙江");
            areaCodeMap.put(34, "安徽");
            areaCodeMap.put(35, "福建");
            areaCodeMap.put(36, "江西");
            areaCodeMap.put(37, "山东");
            areaCodeMap.put(41, "河南");
            areaCodeMap.put(42, "湖北");
            areaCodeMap.put(43, "湖南");
            areaCodeMap.put(44, "广东");
            areaCodeMap.put(45, "广西");
            areaCodeMap.put(46, "海南");
            areaCodeMap.put(50, "重庆");
            areaCodeMap.put(51, "四川");
            areaCodeMap.put(52, "贵州");
            areaCodeMap.put(53, "云南");
            areaCodeMap.put(54, "西藏");
            areaCodeMap.put(61, "陕西");
            areaCodeMap.put(62, "甘肃");
            areaCodeMap.put(63, "青海");
            areaCodeMap.put(64, "宁夏");
            areaCodeMap.put(65, "新疆");
            areaCodeMap.put(71, "台湾");
            areaCodeMap.put(81, "香港");
            areaCodeMap.put(82, "澳门");
            areaCodeMap.put(91, "外国");
        }
        /** 验证码 */
        private final static char[] verificationCodeArr = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        /** 加权因子 */
        private final static int[] wiArr = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

        /**
         * 身份证验证
         *
         * @param certNo
         *            号码内容
         * @author <a href="zhangbw@liepin#com">Boswell</a>
         */
        public static boolean check(String certNo) {
            // ====== 长度检查 ======
            if (certNo == null || (certNo.length() != 15 && certNo.length() != 18)) {
                MonitorLogger.getInstance().log(
                        "校验身份证号失败#位数不正确.usereId:" + ThreadLocalUtil.getInstance().getCurrentUserId() + ",id:" + certNo);
                return false;
            }
            String ai = "";
            // ====== 数字 除最后一位都为数字 ======
            if (certNo.length() == 18) {
                ai = certNo.substring(0, 17);
            } else if (certNo.length() == 15) {
                // 15位的身份证编码把出生年扩展为4位 ,简单的就是增加一个19
                ai = certNo.substring(0, 6) + "19" + certNo.substring(6, 15);
            }
            try {
                Long.valueOf(ai);
            } catch (NumberFormatException e) {
                MonitorLogger.getInstance()
                        .log("校验身份证号失败#数字校验失败.usereId:" + ThreadLocalUtil.getInstance().getCurrentUserId() + ",id:"
                                + certNo);
                return false;
            }

            // ====== 出生日期的检查 ======
            String yearStr = ai.substring(6, 10);// 年份
            String monthStr = ai.substring(10, 12);// 月份
            String dayStr = ai.substring(12, 14);// 天

            // 检查年份
            final int yearInt = Integer.parseInt(yearStr);
            if (yearInt < 1900 || yearInt > Calendar.getInstance().get(Calendar.YEAR)) {
                MonitorLogger.getInstance().log(
                        "校验身份证号失败#年份不正确.usereId:" + ThreadLocalUtil.getInstance().getCurrentUserId() + ",id:" + certNo
                                + ",year:" + yearStr);
                return false;// 1900年的PASS,超过今年的PASS
            }
            // 检查月份
            final int monthInt = Integer.parseInt(monthStr);
            if (monthInt < 1 || monthInt > 12) {
                MonitorLogger.getInstance().log(
                        "校验身份证号失败#月份不正确.usereId:" + ThreadLocalUtil.getInstance().getCurrentUserId() + ",id:" + certNo
                                + ",month:" + monthStr);
                return false;
            }
            // 检查天数
            final int dayInt = Integer.parseInt(dayStr);
            if (dayInt < 1 || dayInt > 31) {
                MonitorLogger.getInstance().log(
                        "校验身份证号失败#天数不正确.usereId:" + ThreadLocalUtil.getInstance().getCurrentUserId() + ",id:" + certNo
                                + ",day:" + dayStr);
                return false;
            }

            // ====== 检查地址码 ======
            if (areaCodeMap.get(Integer.valueOf(ai.substring(0, 2))) == null) {
                MonitorLogger.getInstance()
                        .log("校验身份证号失败#地址码不正确.usereId:" + ThreadLocalUtil.getInstance().getCurrentUserId() + ",id:"
                                + certNo);
                return false;
            }

            // ====== 检查检验码 ======
            if (certNo.length() == 15) {
                return true;
            }

            // 十七位数字本体码加权求和
            final char[] cs = certNo.toCharArray();
            int s = 0;
            for (int i = 0; i < 17; i++) {
                s += (Integer.valueOf(String.valueOf(cs[i])) * wiArr[i]);
            }
            char last = cs[cs.length - 1];
            if (last == 'x') {
                last = 'X';
            }
            int y = s % 11;
            boolean isValidVerificationCode = (last == verificationCodeArr[y]);
            if (!isValidVerificationCode) {
                MonitorLogger.getInstance().log(
                        "校验身份证号失败#最后一位校验码不正确.usereId:" + ThreadLocalUtil.getInstance().getCurrentUserId() + ",id:"
                                + certNo);
            }
            return isValidVerificationCode;
        }
    }
}
