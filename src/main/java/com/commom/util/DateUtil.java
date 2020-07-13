package com.commom.util;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

    private static ThreadLocalDateFormat datetimeFormat = new ThreadLocalDateFormat();
    private static ThreadLocalDateFormat dateFormat = new ThreadLocalDateFormat("yyyyMMdd");

    /**
     * 获取当前时间，精确到秒。格式: yyyyMMddhh
     */
    public static String getCurrentDateHour() {
        Calendar calendar = Calendar.getInstance();
        return getCurrentDateTime(calendar, 10);
    }

    private static String getCurrentDateTime(final Calendar calendar, Integer dateTimeLength) {
        StringBuilder builder = new StringBuilder();

        builder.append(calendar.get(Calendar.YEAR));
        if (dateTimeLength == 4) {
            return builder.toString();
        }

        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            builder.append(0);
        }
        builder.append(month);
        if (dateTimeLength == 6) {
            return builder.toString();
        }

        int date = calendar.get(Calendar.DATE);
        if (date < 10) {
            builder.append(0);
        }
        builder.append(date);
        if (dateTimeLength == 8) {
            return builder.toString();
        }

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 10) {
            builder.append(0);
        }
        builder.append(hour);
        if (dateTimeLength == 10) {
            return builder.toString();
        }

        int min = calendar.get(Calendar.MINUTE);
        if (min < 10) {
            builder.append(0);
        }
        builder.append(min);
        if (dateTimeLength == 12) {
            return builder.toString();
        }

        int sec = calendar.get(Calendar.SECOND);
        if (sec < 10) {
            builder.append(0);
        }
        builder.append(sec);
        return builder.toString();
    }

    public static long daysBetween(String startdate, String enddate) {
        if (startdate == null || "".equals(startdate)) {
            return 0;
        }
        if (enddate == null || "".equals(enddate)) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date starttime = null;
        Date endtime = null;
        try {
            starttime = sdf.parse(startdate);
            endtime = sdf.parse(enddate);
        } catch (Exception e) {
            return 0;
        }

        cal.setTime(starttime);
        long l1 = cal.getTimeInMillis();

        cal.setTime(endtime);
        long l2 = cal.getTimeInMillis();

        return (l2 - l1) / (1000 * 60 * 60 * 24);
    }

    public static String getDateTimeStr(Calendar cal) {
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) {
            day = "0" + day;
        }

        String hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        if (hour.length() == 1) {
            hour = "0" + hour;
        }

        String min = String.valueOf(cal.get(Calendar.MINUTE));
        if (min.length() == 1) {
            min = "0" + min;
        }

        String sec = String.valueOf(cal.get(Calendar.SECOND));
        if (sec.length() == 1) {
            sec = "0" + sec;
        }
        return year + month + day + hour + min + sec;
    }

    /**
     * 获取当前时间，精确到秒。格式: yyyyMMddhhmmss
     */
    public static String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        return getCurrentDateTime(calendar);
    }

    /**
     * 获取当前时间。格式: yyyyMdd
     */
    public static String yyyymmddd(final Calendar calendar) {
        return "" + calendar.get(Calendar.YEAR) + (calendar.get(Calendar.MONTH) + 1)
                + calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getCurrentDateTime(final Calendar calendar) {
        StringBuilder builder = new StringBuilder();

        builder.append(calendar.get(Calendar.YEAR));
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            builder.append(0);
        }
        builder.append(month);
        int date = calendar.get(Calendar.DATE);
        if (date < 10) {
            builder.append(0);
        }
        builder.append(date);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 10) {
            builder.append(0);
        }
        builder.append(hour);
        int min = calendar.get(Calendar.MINUTE);
        if (min < 10) {
            builder.append(0);
        }
        builder.append(min);
        int sec = calendar.get(Calendar.SECOND);
        if (sec < 10) {
            builder.append(0);
        }
        builder.append(sec);
        return builder.toString();
    }

    public static String getDateStr(Calendar cal) {
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) {
            day = "0" + day;
        }

        return year + month + day;
    }

    /**
     * 获取当前时间，精确到日。格式: yyyyMMdd
     */
    public static String getCurrentDate() {
        StringBuilder builder = new StringBuilder();
        Calendar calendar = Calendar.getInstance();

        builder.append(calendar.get(Calendar.YEAR));
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            builder.append(0);
        }
        builder.append(month);
        int date = calendar.get(Calendar.DATE);
        if (date < 10) {
            builder.append(0);
        }
        builder.append(date);
        return builder.toString();
    }

    /**
     * 获取当前年份 格式: yyyy
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static String getDateStr(int offday) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, offday);

        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) {
            day = "0" + day;
        }

        return year + month + day;
    }

    public static String getDateOffMonth(int offmonth, String separator) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, offmonth);

        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) {
            day = "0" + day;
        }

        return year + separator + month + separator + day;
    }

    public static String getDateStr(Calendar cal, int offday) {
        cal.add(Calendar.DAY_OF_MONTH, offday);

        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) {
            day = "0" + day;
        }

        return year + month + day;
    }

    public static Calendar getCalendar(String date) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
            int month = Integer.parseInt(date.substring(4, 6)) - 1;
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6, 8)));
            if (date.length() >= 10) {
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(8, 10)));
            } else {
                cal.set(Calendar.HOUR_OF_DAY, 0);
            }
            if (date.length() >= 12) {
                cal.set(Calendar.MINUTE, Integer.parseInt(date.substring(10, 12)));
            } else {
                cal.set(Calendar.MINUTE, 0);
            }
            if (date.length() >= 14) {
                cal.set(Calendar.SECOND, Integer.parseInt(date.substring(12, 14)));
            } else {
                cal.set(Calendar.SECOND, 0);
            }
            return cal;
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getDifferDays(String strBegin, String strEnd) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null, date2 = null;
        int days = 0;
        try {
            date1 = f.parse(strBegin);
            date2 = f.parse(strEnd);
            days = (int) ((date2.getTime() - date1.getTime()) / 86400000);
        } catch (Exception e) {
            return null;
        }

        return days;

    }

    public static Integer getDifferMinutes(String strBegin, String strEnd) {
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
        Date time1 = null, time2 = null;
        int minutes = 0;
        try {
            time1 = f.parse(strBegin);
            time2 = f.parse(strEnd);
            minutes = (int) ((time2.getTime() - time1.getTime()) / 60000);
        } catch (Exception e) {
            return null;
        }

        return minutes;

    }

    public static String formatDateTime12(String time) {
        return formatDateTime12(time, "-");
    }

    public static String formatDateTime12(String time, String Separator) {
        if (time == null) {
            return "";
        }
        StringBuffer ret = new StringBuffer();
        if (time != null && time.length() == 6) {
            ret.append("20" + time.substring(0, 2));
            ret.append(Separator);
            ret.append(time.substring(2, 4));
            ret.append(Separator);
            ret.append(time.substring(4, 6));
            ret.append(" ");
            ret.append("00");
            ret.append(":");
            ret.append("00");
            return ret.toString();
        } else if (time != null && time.length() == 8) {
            ret.append(time.substring(0, 4));
            ret.append(Separator);
            ret.append(time.substring(4, 6));
            ret.append(Separator);
            ret.append(time.substring(6, 8));
            ret.append(" ");
            ret.append("00");
            ret.append(":");
            ret.append("00");
            return ret.toString();
        } else if (time != null && time.length() == 14) {
            ret.append(time.substring(0, 4));
            ret.append(Separator);
            ret.append(time.substring(4, 6));
            ret.append(Separator);
            ret.append(time.substring(6, 8));
            ret.append(" ");
            ret.append(time.substring(8, 10));
            ret.append(":");
            ret.append(time.substring(10, 12));
            return ret.toString();
        } else {
            return time;
        }
    }

    public static String formatDateTime8(String time) {
        return formatDateTime8(time, "-");
    }

    public static String formatDateTime8(String time, String Separator) {
        if (time == null) {
            return "";
        }
        StringBuffer ret = new StringBuffer();
        if (time != null && time.length() == 6) {
            ret.append("20" + time.substring(0, 2));
            ret.append(Separator);
            ret.append(time.substring(2, 4));
            ret.append(Separator);
            ret.append(time.substring(4, 6));
            return ret.toString();
        } else if (time != null && time.length() >= 8) {
            ret.append(time.substring(0, 4));
            ret.append(Separator);
            ret.append(time.substring(4, 6));
            ret.append(Separator);
            ret.append(time.substring(6, 8));
            return ret.toString();
        } else {
            return time;
        }
    }

    public static String formatDateTime6(String time, char c) {
        return formatDateTime6(time, c, "cn");
    }

    public static String formatDateTime6(String time, char c, String langkind) {
        StringBuffer ret = new StringBuffer();
        if (time != null && time.length() >= 6) {
            if ("999999".equals(time)) {
                if ("en".equals(langkind)) {
                    ret.append("To present");
                } else {
                    ret.append("至今");
                }
            } else {
                ret.append(time.substring(0, 4));
                ret.append(c);
                ret.append(time.substring(4, 6));
            }
            return ret.toString();
        } else {
            return time;
        }
    }

    public static Integer getNum2Year(Integer num) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -num);

        return cal.get(Calendar.YEAR);
    }

    public static Integer getYear2Num(Integer year) {
        if (year == null || year <= 1900) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        int age = cal.get(Calendar.YEAR) - year;

        return age;
    }

    public static Integer getYear2Num(String year) {
        try {
            Integer _year = new Integer(year);
            return getYear2Num(_year);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTimeDescribe(String time) {
        String currentDate = DateUtil.formatDateTime8(DateUtil.getCurrentDate());
        String desc = null;
        if (time == null) {
            desc = "";
        } else if (time.length() == 8) {
            String descDate = DateUtil.formatDateTime8(time);

            int diff = getDifferDays(currentDate, descDate);

            if (diff == 0) {
                desc = "今天";
            } else if (diff == -1) {
                desc = "昨天";
            } else if (diff == -2) {
                desc = "前天";
            } else {
                desc = formatDateTime8(time);
            }
        } else if (time.length() == 14) {
            String currentTime = getCurrentDateTime();
            int diff = getDifferMinutes(time, currentTime);

            if (diff >= 0 && diff <= 5) {
                desc = "刚刚";
            } else if (diff > 5 && diff <= 59) {
                desc = diff + "分钟前";
            } else if (diff > 59 && diff <= 1440) {
                desc = diff / 60 + "小时前";
            } else if (diff > 1440) {
                String descDate = DateUtil.formatDateTime8(time);

                diff = getDifferDays(currentDate, descDate);

                if (diff == 0) {
                    desc = "今天";
                } else if (diff == -1) {
                    desc = "昨天";
                } else if (diff == -2) {
                    desc = "前天";
                } else {
                    desc = formatDateTime8(time);
                }
            }
        } else {
            desc = formatDateTime8(time);
        }
        return desc;
    }

    /**
     * 获取当前日期加offday后的日期
     *
     * @param offday
     * @return
     * @author cuilei
     * @date Aug 22, 2011
     */
    public static String getDate(int offday) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, offday);

        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) {
            day = "0" + day;
        }

        return year + month + day;
    }

    /**
     * 描述：将时间字符串转为时间描述字符串。如“20110904121316”转换为“今天 12:13”
     *
     * @author {zhourx}
     * @Sep 4, 2011
     * @param timeStr
     * @return
     */
    public static String convertTimeStr4Show(String timeStr) {
        if (StringUtil.isBlank(timeStr) || timeStr.trim().length() < 14) {
            return timeStr;
        }
        StringBuilder resultBu = new StringBuilder("");
        String todayStr = getCurrentDate();
        if (todayStr.equals(timeStr.substring(0, 8))) {
            resultBu.append("今天 ");
        } else {
            String yesterdayStr = getDate(-1);
            if (yesterdayStr.equals(timeStr.substring(0, 8))) {
                resultBu.append("昨天 ");
            } else {
                resultBu.append(timeStr.substring(0, 4)).append("-");
                resultBu.append(timeStr.substring(4, 6)).append("-");
                resultBu.append(timeStr.substring(6, 8)).append(" ");
            }
        }
        resultBu.append(timeStr.substring(8, 10)).append(":");
        resultBu.append(timeStr.substring(10, 12));

        return resultBu.toString();
    }

    /**
     * 得到指定日期的最小时间
     *
     * @param date 格式: yyyyMMdd
     * @return String 格式: yyyyMMdd000000
     */
    public static String getDateMin(String date) {
        return date + "000000";
    }

    /**
     * 得到指定日期的最大时间
     *
     * @param date 格式: yyyyMMdd
     * @return String 格式: yyyyMMdd235959
     */
    public static String getDateMax(String date) {
        return date + "235959";
    }

    /**
     * 得到指定日期的年
     *
     * @param date 格式: yyyyMMdd
     * @return int
     */
    public static int getYear(String date) {
        if (date.length() != 8) {
            return -1;
        }
        return Integer.parseInt(date.substring(0, 4));
    }

    /**
     * 得到指定日期的月
     *
     * @param date 格式: yyyyMMdd
     * @return int
     */
    public static int getMonth(String date) {
        if (date.length() != 8) {
            return -1;
        }
        return Integer.parseInt(date.substring(4, 6));
    }

    /**
     * 得到指定日期的日
     *
     * @param date 格式: yyyyMMdd
     * @return int
     */
    public static int getDate(String date) {
        if (date.length() != 8) {
            return -1;
        }
        return Integer.parseInt(date.substring(6, 8));
    }

    private static final int[] WEEK = { -1, 7, 1, 2, 3, 4, 5, 6 };

    /**
     * 得到指定日期是星期几
     *
     * @param value 格式: yyyyMMdd
     * @return int 星期天:7,星期一:1,星期二:2,星期三:3,星期四:4,星期五:5,星期六:6
     */
    public static int getDayOfWeek(String value) {
        int w = WEEK[0];
        Date date = null;
        try {
            date = dateFormat.get().parse(value);
        } catch (ParseException e) {
//            logger.warn(value + " parse \"yyyyMMdd\" fail", e);
        }
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            w = WEEK[calendar.get(Calendar.DAY_OF_WEEK)];
        }
        return w;
    }

    /**
     * 增加 减少 分钟的计算
     *
     * @param value 格式: yyyyMMddhhmmss
     * @param amount 为添加的时间量,可负数
     */
    public static String addMinTime(String value, int amount) {
        return addTime(value, Calendar.MINUTE, amount);
    }

    /**
     * 增加 减少 秒的计算
     *
     * @param value 格式: yyyyMMddhhmmss
     * @param amount 为添加的时间量,可负数
     */
    public static String addSecTime(String value, int amount) {
        return addTime(value, Calendar.SECOND, amount);
    }

    private static String addTime(String value, int field, int amount) {
        String result = null;
        Date date = null;
        try {
            date = datetimeFormat.get().parse(value);
        } catch (ParseException e) {
//            logger.warn(value + " parse \"yyyyMMdd\" fail", e);
        }
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(field, amount);
            result = datetimeFormat.get().format(calendar.getTime());
        }
        return result;
    }

    /**
     * 20120413104741 => 2012-04-13
     */
    public static String yyyymmdd(String value) {
        if (value == null || value.length() < 8) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(value.substring(0, 4));
        builder.append("-");
        builder.append(value.substring(4, 6));
        builder.append("-");
        builder.append(value.substring(6, 8));
        return builder.toString();
    }

    /**
     * 20120413104741 => 2012-04-13 10:47
     */
    public static String yyyymmddhhmm(String value) {
        if (value == null || value.length() < 12) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(value.substring(0, 4));
        builder.append("-");
        builder.append(value.substring(4, 6));
        builder.append("-");
        builder.append(value.substring(6, 8));
        builder.append(" ");
        builder.append(value.substring(8, 10));
        builder.append(":");
        builder.append(value.substring(10, 12));
        return builder.toString();
    }

    private static final int[][] SEASON_ARR = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };

    /**
     * 取得季度开始终了日 Key: 季度开始日-startDate 季度终了日-endDate 季度-season
     *
     * @param month
     * @return
     */
    public static Map<String, String> getThisSeasonTime(String month) {
        int season = 1;
        switch (Integer.valueOf(month)) {
        case 1:
        case 2:
        case 3:
            season = 1;
            break;
        case 4:
        case 5:
        case 6:
            season = 2;
            break;
        case 7:
        case 8:
        case 9:
            season = 3;
            break;
        case 10:
        case 11:
        case 12:
            season = 4;
            break;
        }
        int start_month = SEASON_ARR[season - 1][0];
        int end_month = SEASON_ARR[season - 1][2];

        String yearStr = getCurrentDate();
        int year = getYear(yearStr);

        int end_day = getLastDayOfMonth(year, end_month);

        StringBuilder startBuilder = new StringBuilder();
        startBuilder.append(year);
        startBuilder.append((start_month < 10) ? "0" + start_month : start_month);
        startBuilder.append("01");

        StringBuilder endBuilder = new StringBuilder();
        endBuilder.append(year);
        endBuilder.append((end_month < 10) ? "0" + end_month : end_month);
        endBuilder.append(end_day);

        Map<String, String> map = new HashMap<String, String>();
        map.put("startDate", startBuilder.toString());
        map.put("endDate", endBuilder.toString());
        map.put("season", String.valueOf(season));
        return map;
    }

    /**
     * 取得本月最后一日
     *
     * @param year
     * @param month
     * @return
     */
    public static int getLastDayOfMonth(int year, int month) {
        switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return 31;
        case 4:
        case 6:
        case 9:
        case 11:
            return 30;
        case 2:
            return isLeapYear(year) ? 29 : 28;
        default:
            return 0;
        }
    }

    /**
     * 闰年判断
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * 增加几天
     *
     * @param dateStr
     *            日期字符串(格式:yyyy-MM-dd)
     * @param days
     *            增加天数
     * @return 调整后的日期串(格式:yyyy-MM-dd)
     */
    public static String addDay(String dateStr, int days) {

        // return DateTime.parse(dateStr).plus(days).toString("yyyy-MM-dd");

        return addDay(dateStr, days, "yyyy-MM-dd");
    }

    /**
     * 根据指定格式增加天数.
     *
     *
     * @param dateStr
     *            日期字符串
     * @param days
     *            增加天数
     * @param pattern
     *            日期格式
     * @return String date
     */
    public static String addDay(String dateStr, int days, String pattern) {
        if (StringUtil.isBlank(dateStr) || StringUtil.isBlank(pattern)) {
            return "";
        }

        // XXX 待测试对比,替换成下面的实现方式.
        // return DateTime.parse(dateStr,
        // DateTimeFormat.forPattern(pattern)).plusDays(days).toString(pattern);

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);// 不把月份超过12的数据转换成下一年
        try {
            calendar.setTime(dateFormat.parse(dateStr));
        } catch (ParseException e) {
//            debugLog.log("解析日期格式错误:" + dateStr, e);
            return "";
        }
        calendar.add(Calendar.DAY_OF_MONTH, days);// 增加天数
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 距离offDays天还有多少秒(到夜里12点)
     *
     * @param offsetDays
     *            偏移天数(大于0,如小于等于0则计算当前日期距离当天日期23:59:59剩余秒数)
     * @return 秒数
     */
    public static int getLeftSeconds(int offsetDays) {
        // 当前时间
        DateTime nowDateTime = DateTime.now();
        // 当日23:59:59
        DateTime endDateTime = new DateTime(nowDateTime.getYear(), nowDateTime.getMonthOfYear(),
                nowDateTime.getDayOfMonth(), 23, 59, 59);
        if (offsetDays > 0) {
            endDateTime = endDateTime.plusDays(offsetDays);
        }
        return Seconds.secondsBetween(nowDateTime, endDateTime).getSeconds();
    }

    /**
     * 获取到指定日期的秒数(23:59)
     *
     * @param endDate
     *            结束日期 (yyyyMMdd[HHmmss])
     * @return 秒数
     * @throws NumberFormatException
     *             截取日期出错时抛出
     */
    public static int getLeftSeconds(String endDate) {
        if (StringUtil.isBlank(endDate) || endDate.length() < 8) {
            return 0;
        }
        return Seconds.secondsBetween(DateTime.now(), new DateTime(Integer.parseInt(endDate.substring(0, 4)),
                Integer.parseInt(endDate.substring(4, 6)), Integer.parseInt(endDate.substring(6, 8)), 23, 59, 59))
                .getSeconds();
    }
}
