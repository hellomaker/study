package com.hellomaker.web.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * 日期时间工具类
 * 注：禁止使用simpleDateFormat,推荐全面使用新的时间日期api。LocalDateTime,DateTimeFormatter,Instant.
 * @Author xianzhikun
 * @Date 2023/2/14 10:01
 * @Verion 2.0
 **/
public class DateUtil {

    static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter datePath = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static String nowTimeStr() {
        return LocalDateTime.now().format(format);
    }

    public static String nowDateStr() {
        return LocalDate.now().format(formatDate);
    }

    public static String datePath() {
        return LocalDate.now().format(datePath);
    }

    public static boolean isBeforeNowMinutes(Date date, int beforeMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, beforeMinutes);
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());
        if (calendar.compareTo(calendarNow) <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isBeforeNowHours(Date date, int beforeHours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, beforeHours);
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());
        if (calendar.compareTo(calendarNow) <= 0) {
            return true;
        }
        return false;
    }

    public static LocalDateTime after(LocalDateTime date, long time, TimeUnit timeUnit) {
        return date.plusSeconds(timeUnit.toSeconds(time));
    }

    public static String afterTimeStr(LocalDateTime date, long time, TimeUnit timeUnit) {
        return after(date, time, timeUnit).format(format);
    }

    /**
     *
     * @describe: date2 是否在 date 之前 time
     *
     * @param date
     * @param date2
     * @param time
     * @param timeUnit
     * @return  {{@link boolean}}
     * @author  xianzhikun
     * @date    2023/7/31 17:07
     */
    public static boolean isBefore(LocalDateTime date, LocalDateTime date2, long time, TimeUnit timeUnit) {
        if (date.plusSeconds(timeUnit.toSeconds(time)).isBefore(date2)) {
            return true;
        }
        return false;
    }

    public static boolean isBefore(String date, LocalDateTime date2, long time, TimeUnit timeUnit) throws DateTimeParseException {
        return isBefore(LocalDateTime.parse(date, format), date2, time, timeUnit);
    }

    public static boolean isBefore(LocalDateTime date, LocalDateTime date2) throws DateTimeParseException{
        return isBefore(date, date2, 0, TimeUnit.MILLISECONDS);
    }

    public static boolean isBefore(String date, LocalDateTime date2) throws DateTimeParseException {
        return isBefore(date, date2, 0, TimeUnit.MILLISECONDS);
    }

    public static boolean isBefore(String date, String date2) throws DateTimeParseException{
        return isBefore(date, date2, 0, TimeUnit.MILLISECONDS);
    }

    public static boolean isBefore(LocalDateTime date, String date2) throws DateTimeParseException{
        return isBefore(date, date2, 0, TimeUnit.MILLISECONDS);
    }

    public static boolean isBefore(String date, String date2, long time, TimeUnit timeUnit)  throws DateTimeParseException{
        return isBefore(LocalDateTime.parse(date, format), LocalDateTime.parse(date2, format), time, timeUnit);
    }

    public static boolean isBefore(LocalDateTime date, String date2, long time, TimeUnit timeUnit) throws DateTimeParseException {

        return isBefore(date, LocalDateTime.parse(date2, format), time, timeUnit);
    }
}
