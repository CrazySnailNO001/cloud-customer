package com.xzh.customer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：xzh
 * @date ：Created in 2019-11-08 10:44
 * @description：
 * @modified By：
 * @version:
 */
public class DateUtil {
    @Autowired
    ObjectMapper objectMapper;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_PATTERN_CN1 = "yyyy年 MM月 dd日";
    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_MINUTE = "yyyy/MM/dd HH:mm";

    public static Instant getInstant(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day + 1, 0, 0, 0);
        return cal.getTime().toInstant();
    }

    /**
     * 不论是当前时间，还是历史时间  皆是时间点的T-N天
     * repeatDate 任意时间    param 前几天
     */
    public static Instant getPreviousTime(String repeatDate, int param) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        if (repeatDate == null || "".equals(repeatDate)) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - param);

        } else {
            int year = Integer.parseInt(repeatDate.substring(0, 4));
            String monthsString = repeatDate.substring(4, 6);
            int month;
            if ("0".equals(monthsString.substring(0, 1))) {
                month = Integer.parseInt(monthsString.substring(1, 2));
            } else {
                month = Integer.parseInt(monthsString.substring(0, 2));
            }
            String dateString = repeatDate.substring(6, 8);
            int date;
            if ("0".equals(dateString.subSequence(0, 1))) {
                date = Integer.parseInt(dateString.substring(1, 2));
            } else {
                date = Integer.parseInt(dateString.substring(0, 2));
            }
            cal.set(year, month - 1, date - param + 1);
            System.out.println(dft.format(cal.getTime()));
        }
        return cal.getTime().toInstant();
    }

    public static String zonedDateTimeFormat(ZonedDateTime zonedDateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_MINUTE);
        return simpleDateFormat.format(Date.from(zonedDateTime.toInstant()));
    }

    public static String getCurrentDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_DAY);
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public static String getCurrentDaySecond() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_SECOND);
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public static ZonedDateTime parseDateTime(String dateTime) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime, FORMATTER);
//    LocalDateTime localDateTime = LocalDateTime.parse(dateTime, FORMATTER);
        return zonedDateTime;
    }

    public static long parseMilliSecond(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli() / 1000;
    }

    public static String getCurrentTimeString() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(FORMATTER);
    }

    public static String getLocalDateTimeString(LocalDateTime localDateTime) {
        return FORMATTER.format(localDateTime);
    }

    public static ZonedDateTime getCurrentLocalDateTime() {
        return ZonedDateTime.now();
    }

    /*
     * ZonedDateTime util
     */
    private static Duration getOverTime(String time) {
        Instant timeInstant = ZonedDateTime.parse(time).toInstant();
        Instant now = Instant.now();
        return Duration.between(timeInstant, now);
    }

    private static Duration getOverTime(ZonedDateTime time) {
        Instant timeInstant = time.toInstant();
        Instant now = Instant.now();
        return Duration.between(timeInstant, now);
    }

    public static long getOverMills(String time) {
        return getOverTime(time).toMillis();
    }

    public static long getOverSeconds(String time) {
        return getOverTime(time).toSeconds();
    }

    public static long getOverMinutes(String time) {
        return getOverTime(time).toMinutes();
    }

    public static long getOverMills(ZonedDateTime time) {
        return getOverTime(time).toMillis();
    }

    public static long getOverSeconds(ZonedDateTime time) {
        return getOverTime(time).toSeconds();
    }

    public static long getOverMinutes(ZonedDateTime time) {
        return getOverTime(time).toMinutes();
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param diff 时间差
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(long diff) {

        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;

        day = diff / (24 * 60 * 60);
        hour = (diff / (60 * 60) - day * 24);
        min = ((diff / 60) - day * 24 * 60 - hour * 60);
        sec = (diff - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long[] times = {day, hour, min, sec};
        return times;
    }

    public static String getOverTimeDuration(long diff) {
        long[] distanceTimes = getDistanceTimes(diff);
        return distanceTimes[0] + "-DAY, " + distanceTimes[1] + "-HOUR, " + distanceTimes[2] + "-MIN, " + distanceTimes[3] + "-SEC";
    }

    public static String getFormateDateTime(long originTime, String pattern) {
        Date date = new Date(originTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * verify deliveryTime in 2 days
     * 判断deliveryTime 时候在payTime两天内
     **/
    public boolean verifyInTwoDays(Instant payTime, Instant deliveryTime) {
        Date truncate = DateUtils.truncate(Date.from(payTime.plus(Duration.ofDays(2))), Calendar.DATE);
        return truncate.getTime() >= deliveryTime.toEpochMilli();
    }

    /**
     * verify times in workTime(9:00-21:00)
     */
    public boolean verifydispatchTime(Instant orderTime) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");

        Date now = null;
        Date beginTime = null;
        Date endTime = null;
        try {
            now = df.parse(df.format(orderTime.toEpochMilli()));
            beginTime = df.parse("09:00");
            endTime = df.parse("21:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar date = Calendar.getInstance();
        date.setTime(now);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }


    /**
     * 获取当前时间
     *
     */
    public static String getNowTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * 判断当天是否为本月第一天
     *
     * @return
     */
    public static boolean isFirstDayOfMonth() {
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(calendar.DAY_OF_MONTH);
        if (1 == today) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取当前月份最后一天
     *
     * @return
     * @throws ParseException
     */
    public static String getMaxMonthDate() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     *
     * 描述:获取下一个月的第一天.
     *
     * @return
     */
    public static String getPerFirstDayOfMonth() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     *
     * 描述:获取上个月的最后一天.
     *
     * @return
     */
    public static String getLastMaxMonthDate() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     * 获取上一个月
     *
     * @return
     */
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     *
     * 描述:获取下一个月.
     *
     * @return
     */
    public static String getPreMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, 1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        String preMonth = dft.format(cal.getTime());
        return preMonth;
    }

    // 是否是最后一天
    public static boolean isLastDayOfMonth() {
        boolean flag = false;
        if (StringUtils.isNotBlank(getNowTime()) && StringUtils.isNotBlank(getMaxMonthDate()) && StringUtils.equals(getNowTime(), getMaxMonthDate())) { // getMaxMonthDate().equals(getNowTime())
            flag = true;
        }
        return flag;
    }

    /**
     * 获取任意时间的下一个月
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    public static String getPreMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(4, 6);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year,month,Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * 获取任意时间的上一个月
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    public static String getLastMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(4, 6);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year,month-2,Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }
    //
    /**
     * 获取任意时间的月的最后一天
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    private static String getMaxMonthDate(String repeatDate) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            if(StringUtils.isNotBlank(repeatDate) && !"null".equals(repeatDate)){
                calendar.setTime(dft.parse(repeatDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     * 获取任意时间的月第一天
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    private static String getMinMonthDate(String repeatDate){
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            if(StringUtils.isNotBlank(repeatDate) && !"null".equals(repeatDate)){
                calendar.setTime(dft.parse(repeatDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }
    /**
     * 不论是当前时间，还是历史时间  皆是时间点的前天
     * repeatDate  任意时间
     */
    public static String getModify2DaysAgo(String repeatDate) {
        Calendar cal = Calendar.getInstance();
        String daysAgo = "";
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        if (repeatDate == null || "".equals(repeatDate)) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 2);

        } else {
            int year = Integer.parseInt(repeatDate.substring(0, 4));
            String monthsString = repeatDate.substring(4, 6);
            int month;
            if ("0".equals(monthsString.substring(0, 1))) {
                month = Integer.parseInt(monthsString.substring(1, 2));
            } else {
                month = Integer.parseInt(monthsString.substring(0, 2));
            }
            String dateString = repeatDate.substring(6, 8);
            int date;
            if ("0".equals(dateString.subSequence(0, 1))) {
                date = Integer.parseInt(dateString.substring(1, 2));
            } else {
                date = Integer.parseInt(dateString.substring(0, 2));
            }
            cal.set(year, month-1, date - 1);
            System.out.println(dft.format(cal.getTime()));
        }
        daysAgo = dft.format(cal.getTime());
        return daysAgo;
    }

    /**
     * 不论是当前时间，还是历史时间  皆是时间点的T-N天
     * repeatDate 任意时间    param 数字 可以表示前几天
     */
    public static String getModifyNumDaysAgo(String repeatDate,int param) {
        Calendar cal = Calendar.getInstance();
        String daysAgo = "";
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        if (repeatDate == null || "".equals(repeatDate)) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - param);

        } else {
            int year = Integer.parseInt(repeatDate.substring(0, 4));
            String monthsString = repeatDate.substring(4, 6);
            int month;
            if ("0".equals(monthsString.substring(0, 1))) {
                month = Integer.parseInt(monthsString.substring(1, 2));
            } else {
                month = Integer.parseInt(monthsString.substring(0, 2));
            }
            String dateString = repeatDate.substring(6, 8);
            int date;
            if ("0".equals(dateString.subSequence(0, 1))) {
                date = Integer.parseInt(dateString.substring(1, 2));
            } else {
                date = Integer.parseInt(dateString.substring(0, 2));
            }
            cal.set(year, month-1, date - param+1);
            System.out.println(dft.format(cal.getTime()));
        }
        daysAgo = dft.format(cal.getTime());
        return daysAgo;
    }

}
