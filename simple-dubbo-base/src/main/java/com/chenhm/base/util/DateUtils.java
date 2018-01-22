package com.chenhm.base.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @author jianyun.zheng
 * @Type DateUtils
 * @Desc
 * @date 2012-9-26
 * @Version V1.0
 */
public class DateUtils {

    public static final DateTimeFormatter YMDHMS = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter YMDHMS_POINT = DateTimeFormat.forPattern("yyyy.MM.dd HH:mm:ss");
    public static final DateTimeFormatter YMDHMS_SHORT = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter YMD = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static final DateTimeFormatter MD = DateTimeFormat.forPattern("MM.dd");
    public static final DateTimeFormatter YMD_SHORT = DateTimeFormat.forPattern("yyyyMMdd");
    /**
     * 将日期毫秒数转换成指定格式的字符串
     * @param millis        毫秒数
     * @param formatter     转换器
     * @return
     */
    public static String formatLongToString(Long millis, DateTimeFormatter formatter) {
        return formatter.print(millis);
    }

    /**
     * 将日期转换成指定格式的字符串
     * @param date
     * @param formatter
     * @return
     */
    public static String formatDateToString(Date date, DateTimeFormatter formatter) {
        if (date == null) {
            return null;
        }
        return formatter.print(date.getTime());
    }

    /**
     * 字符串转换为Date
     * @param dateStr
     * @param formatter
     * @return
     */
    public static Date formatStringToDate(String dateStr, DateTimeFormatter formatter) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        return formatter.parseDateTime(dateStr).toDate();
    }

    /**
     * 日期格式转换
     * @param dateStr            日期字符串
     * @param fromFormatter     原始格式
     * @param toFormatter       结果格式
     * @return
     */
    public static String formatStrToStr(String dateStr, DateTimeFormatter fromFormatter, DateTimeFormatter toFormatter) {
        if (StringUtils.isBlank(dateStr)) {
            return dateStr;
        }
        DateTime dateTime = fromFormatter.parseDateTime(dateStr);
        return toFormatter.print(dateTime.toDate().getTime());
    }

    /**
     * 获取当前时间
     * Tue Dec 05 18:59:03 CST 2017
     * @return
     */
    public static Date getNow() {
        return DateTime.now().toDate();
    }

    /**
     * 获取当前时间与旧时间间隔
     * @param oldTimeInMillis
     * @return
     */
    public static Long getInterval(Long oldTimeInMillis) {
        return DateTime.now().getMillis() - oldTimeInMillis;
    }

    /**
     * 是否今天
     * @param millis
     * @param formatter
     * @return
     */
    public static Boolean isToday(Long millis, DateTimeFormatter formatter) {
        String dateStr = formatLongToString(millis, formatter);
        return dateStr.equals(DateTime.now().toString(formatter));
    }

}
