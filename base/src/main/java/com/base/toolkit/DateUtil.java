package com.base.toolkit;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author: LIU ZEJUN
 * Date: 2018-12-04 15:19:00
 * Comment:
 */


public class DateUtil extends DateUtils {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取月份的第一天
     * @param date date
     * @return 第一天
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        setDayStart(calendar);
        return calendar.getTime();
    }

    /**
     * 获取月份的最后一天
     * @param date date
     * @return 最后一天
     */
    public static Date getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setDayLast(calendar);
        return calendar.getTime();
    }

    public static Date getDayStartTime(Date date) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        setDayStart(todayStart);
        return todayStart.getTime();
    }

    public static Date getDayEndTime(Date date) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        setDayLast(todayEnd);
        return todayEnd.getTime();
    }

    private static void setDayStart(Calendar todayStart) {
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
    }

    private static void setDayLast(Calendar todayEnd) {
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
    }

    /**
     * 获取当前时间的字符串
     * @return
     */
    public static String currentDateTime() {
        return new SimpleDateFormat(DEFAULT_PATTERN).format(new Date());
    }
}
