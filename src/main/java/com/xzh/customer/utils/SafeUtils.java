package com.xzh.customer.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by IntelliJ IDEA. User: Administrator Date: 2008-12-30 Time: 16:06:05
 * To change this template use File | Settings | File Templates.
 */
public class SafeUtils {
    protected static final String DATE_FORMAT = "yyyy-MM-dd";
    protected static final String TIME_FORMAT = "HH:mm:ss";
    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    private static Log log = LogFactory.getLog(SafeUtils.class.getName());

    public static String formatCurrency(BigDecimal bdval, int decimalnum) {
        String tmpcurrency = "0.00";

        //tmpcurrency=""+ bdval.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        try {
            DecimalFormat df = new DecimalFormat();
            df.setGroupingUsed(false);
            df.setMaximumFractionDigits(2);
            df.setMinimumFractionDigits(2);
            tmpcurrency = df.format(bdval);
        } catch (Exception ex) {
            log.error(ex.getMessage());

        }
        return tmpcurrency;
    }

    /**
     * 获取本月第一天的Unixtime
     *
     * @return
     */
    public static Integer getMonthFirstDayToUnixTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE, 1);
        SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
        return SafeUtils.getUnixTimeFromString(simpleFormate.format(calendar.getTime()), "yyyy-MM-dd");

    }

    /**
     * 获取本月最后一天的UnixTime
     *
     * @return
     */
    public static Integer getMonthLastDayToUnixTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
        return SafeUtils.getUnixTimeFromString(simpleFormate.format(calendar.getTime()), "yyyy-MM-dd");

    }

    public static Boolean getBoolean(Object obj) {
        boolean tmpret = false;
        if (obj != null) {
            try {
                tmpret = Boolean.parseBoolean(obj.toString());
            } catch (Exception ex) {

            }
        }
        return tmpret;
    }

    public static BigDecimal getBigDecimal(Object obj) {
        BigDecimal tmpval = new BigDecimal(0);
        if (obj != null) {
            try {
                String tmpstrval = obj.toString();
                tmpstrval = tmpstrval.replace(",", "");//去掉，符号
                tmpval = new BigDecimal(tmpstrval);
            } catch (Exception ex) {

            }
        }
        return tmpval;
    }

    public static int getLocalIPInt() {
        int tmpip = 0;
        String tmpipstr = getLocalIP();
        if (tmpipstr != null) {
            String[] tmpips = tmpipstr.split(".");
            if (tmpips.length == 4) {
                tmpip = Integer.parseInt(tmpips[0]) * 16777216
                        + Integer.parseInt(tmpips[1]) * 65536
                        * +Integer.parseInt(tmpips[2]) * 256
                        + Integer.parseInt(tmpips[3]);
            }
        }
        return tmpip;
    }

    public static String getLocalIP() {
        String tmpip = "";
        try {
            Enumeration allNetInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
                        .nextElement();
                // System.out.println(netInterface.getName());
                if (netInterface.getName().indexOf("eth") != -1) {
                    Enumeration addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = (InetAddress) addresses.nextElement();
                        if (!ip.isLoopbackAddress() && (ip instanceof java.net.Inet4Address)) {
                            tmpip = ip.getHostAddress();
                            break;
                        }
                    }
                }
                if (!tmpip.equals(""))
                    break;
            }
        } catch (Exception ex) {

        }
        if (tmpip.equals("")) tmpip = "127.0.0.1";
        System.out.println(tmpip);
        return tmpip;
    }

    public static String generateGUID() {
        UUID tmpuuid = UUID.randomUUID();
        String tmpstr = tmpuuid.toString();
        tmpstr = tmpstr.replaceAll("-", "");
        return tmpstr;
    }

    public static final String getFormatDate(Date date, String format) {
        String tmpstr = "";
        if (format == null)
            format = "yyyy-MM-dd HH:mm:ss";
        DateFormat format1 = new SimpleDateFormat(format);
        tmpstr = format1.format(date);
        return tmpstr;
    }

    public static final String getCurrentTimeStr(String format) {
        Date tmpdate = getCurrentTime();
        return getFormatDate(tmpdate, format);
    }

    public static final String GetTodayStr() {
        return getCurrentTimeStr("yyyy-MM-dd");
    }

    public static final String getDayStrByOffset(int dayoffset) {
        Calendar tmpcalendar = Calendar.getInstance();
        tmpcalendar.add(Calendar.DAY_OF_YEAR, dayoffset);
        Date tmpdate = tmpcalendar.getTime();
        return getFormatDate(tmpdate, "yyyyMMdd");
    }

    public static final String getDayStrByOffset(String ymd, int dayoffset) {
        Calendar tmpcalendar = Calendar.getInstance();
        Date tmptime = SafeUtils.getDateFromString(ymd + "080000",
                "yyyyMMddhhmmss");
        tmpcalendar.setTime(tmptime);
        tmpcalendar.add(Calendar.DAY_OF_YEAR, dayoffset);
        Date tmpdate = tmpcalendar.getTime();
        return getFormatDate(tmpdate, "yyyyMMdd");
    }

    public static Integer getUnixTimeFromString(String str, String format) {
        Integer tmpunixtime = 0;
        try {
            if ((format == null) || (format.equals("")))
                format = DATE_TIME_FORMAT;
            SimpleDateFormat tmpfmt = new SimpleDateFormat(format);
            Date tmpdate = tmpfmt.parse(str);
            Timestamp unixTime = new Timestamp(tmpdate.getTime());
            tmpunixtime = (int) (unixTime.getTime() / 1000);
        } catch (Exception ex) {

        }
        return tmpunixtime;
    }

    public static int getInt(Object obj) {
        int tmpret = 0;
        if (obj != null) {
            try {
                tmpret = Integer.parseInt(obj.toString());
            } catch (Exception ex) {

            }
        }
        return tmpret;
    }

    public static Long getLong(Object obj, Long defvalue) {
        Long tmpret = defvalue;
        if (obj != null) {
            try {
                tmpret = Long.parseLong(obj.toString());
            } catch (Exception ex) {
            }
        }
        return tmpret;
    }

    public static Long getLong(Object obj) {
        Long tmpret = 0L;
        if (isNotEmpty(obj.toString())) {
            tmpret = Long.parseLong(obj.toString());
        }
        return tmpret;
    }

    public static int getInt(Object obj, int defvalue) {
        int tmpret = defvalue;
        if (obj != null) {
            try {
                if (!obj.toString().equals(""))
                    tmpret = Integer.parseInt(obj.toString());
            } catch (Exception ex) {

            }
        }
        return tmpret;
    }

    public static Integer getInteger(Object obj) {
        Integer tmpret = 0;
        if (obj != null) {
            try {
                tmpret = Integer.valueOf(obj.toString());
            } catch (Exception ex) {
                log.error("getInteger:" + ex);
            }
        }
        return tmpret;
    }

    public static Integer getInteger(Object obj, int defaultValue) {
        Integer tmpret = defaultValue;
        if (obj != null) {
            try {
                tmpret = Integer.valueOf(obj.toString());
            } catch (Exception ex) {
                log.error("getInteger:" + ex);
            }
        }
        return tmpret;
    }

    public static Date getDateFromTimeStamp(Object obj) {
        Date d = null;
        if (obj != null) {
            Timestamp timestamp = (Timestamp) obj;
            d = new Date(timestamp.getTime());
        }
        return d;
    }

    public static Timestamp getTimeStampFromSQL(Object obj) {
        Timestamp timestamp = (Timestamp) obj;
        return timestamp;
    }

    public static Date getDateFromString(Object obj, String format) {
        Date tmpret = null;
        if (obj != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                tmpret = sdf.parse(obj.toString());
            } catch (Exception ex) {

            }
        }
        return tmpret;
    }

    /**
     * 判断对象是否可安全转为为整数
     */
    public static boolean isDia(Object obj) {
        boolean result = false;
        if (obj == null || ("" + obj).trim().equals("")) {
            return false;
        }
        String s = "" + obj;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] > '9' || c[i] < '0') {
                return false;
            }
        }
        return true;
    }

    public static double getDouble(Object obj) {
        double tmpvalue = 0;
        try {
            tmpvalue = Double.parseDouble(obj.toString());
        } catch (Exception ex) {
            log.error("getDouble :" + ex);
        }
        return tmpvalue;
    }

    public static double getDouble(Object obj, double defvalue) {
        double tmpvalue = defvalue;
        try {
            tmpvalue = Double.parseDouble(obj.toString());
        } catch (Exception ex) {
            log.error("getDouble :" + ex);
        }
        return tmpvalue;
    }

    public static String getRequestValue(Object obj) {
        if (obj == null) return "";
        else if (obj instanceof String[]) {
            String value = "";
            String[] values = (String[]) obj;
            for (int i = 0; i < values.length; i++) {
                value = values[i] + ",";
            }
            value = value.substring(0, value.length() - 1);
            return value;
        } else return obj.toString();
    }

    public static String urlDecode(String str) {
        String tmpret = str;
        try {
            tmpret = URLDecoder.decode(str, "utf-8");
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return tmpret;
    }

    public static String urlDecode(String str, String charset) {
        String tmpret = str;
        try {
            tmpret = URLDecoder.decode(str, charset);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return tmpret;
    }

    public static String getUrl(String url, String servletPath) {
        if (url.startsWith("http") || url.startsWith("https")) {
            return url;
        }

        //如果serveletPath末尾有"/",删除
        if (servletPath.endsWith("/")) {
            if (servletPath.length() > 0 && servletPath.charAt(servletPath.length() - 1) == 'x') {
                servletPath = servletPath.substring(0, servletPath.length() - 1);
            }
        }
        //如果url开头没有"/",增加
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        return servletPath + url;
    }

    public static String getString(Object obj) {
        return getString(obj, "");
    }

    public static String getString(Object obj, String defaultString) {
        String tmpret = defaultString;
        if (obj != null)
            tmpret = obj.toString();
        return tmpret.trim();
    }

    public static boolean isNotNull(Object obj) {
        if (obj != null)
            return true;
        return false;
    }

    public static boolean isNull(Object obj) {
        if (obj != null)
            return false;
        return true;
    }

    public static String getStringWithUrlEncode(Object obj) {
        String tmpret = "";
        if (obj != null) {
            try {
                tmpret = URLDecoder.decode(obj.toString(), "utf-8");
            } catch (Exception ex) {
                log.error("getStringWithUrlEncode:" + ex);
            }
        }
        return tmpret.trim();
    }

    public static java.sql.Date getSqlDate(Object obj) {
        java.sql.Date tmpdate = null;
        if (obj != null) {
            try {
                tmpdate = java.sql.Date.valueOf(obj.toString());
            } catch (Exception ex) {
                log.error("getDate :" + ex);
            }
        }
        return tmpdate;
    }

    public static java.sql.Date getSqlDate(String obj) {
        java.sql.Date tmpdate = null;
        if (obj != null) {
            try {
                tmpdate = java.sql.Date.valueOf(obj.toString());
            } catch (Exception ex) {
                log.error("getDate :" + ex);
            }
        }
        return tmpdate;
    }

    public static java.sql.Date getSqlDate(Object obj, java.sql.Date defvalue) {
        java.sql.Date tmpdate = defvalue;
        if (obj != null) {
            try {
                tmpdate = java.sql.Date.valueOf(obj.toString());
            } catch (Exception ex) {
                log.error("getDate :" + ex);
            }
        }
        return tmpdate;
    }

    public static String getFormatDateTimeFromUnixTime(Integer unixtime,
                                                       String format) {
        String tmpdatestr = "";
        try {
            if ((unixtime != null) && (unixtime != 0)) {
                Timestamp tmpdate = new Timestamp(unixtime * 1000L);
                SimpleDateFormat tmpfmt = new SimpleDateFormat(format);
                tmpdatestr = tmpfmt.format(tmpdate);
            }
        } catch (Exception ex) {

        }
        return tmpdatestr;
    }

    public static String getFormatDateTimeFromUnixTime(int unixtime,
                                                       String format) {
        String tmpdatestr = "";
        try {
            if (unixtime != 0) {
                Timestamp tmpdate = new Timestamp(unixtime * 1000L);
                SimpleDateFormat tmpfmt = new SimpleDateFormat(format);
                tmpdatestr = tmpfmt.format(tmpdate);
            }
        } catch (Exception ex) {

        }
        return tmpdatestr;
    }

    public static String getFormatDateTimeFromUnixTime(String unixtimestr,
                                                       String format) {
        String tmpdatestr = "";
        try {
            int unixtime = Integer.parseInt(unixtimestr);
            if (unixtime != 0) {
                Timestamp tmpdate = new Timestamp(unixtime * 1000L);
                SimpleDateFormat tmpfmt = new SimpleDateFormat(format);
                tmpdatestr = tmpfmt.format(tmpdate);
            }
        } catch (Exception ex) {

        }
        return tmpdatestr;
    }

    public static String getFormatUnixTimeFromObject(int unixtime, String format) {
        String tmpdatestr = "";
        try {
            if (unixtime != 0) {
                Timestamp tmpdate = new Timestamp(unixtime * 1000L);
                SimpleDateFormat tmpfmt = new SimpleDateFormat(format);
                tmpdatestr = tmpfmt.format(tmpdate);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return tmpdatestr;
    }

    public static String getFormatUnixTimeFromObject(Object obj, String format) {
        String tmpdatestr = "";
        if (obj != null) {
            try {
                int unixtime = Integer.parseInt(obj.toString());
                if (unixtime != 0) {
                    Timestamp tmpdate = new Timestamp(unixtime * 1000L);
                    SimpleDateFormat tmpfmt = new SimpleDateFormat(format);
                    tmpdatestr = tmpfmt.format(tmpdate);
                }
            } catch (Exception ex) {

            }
        }
        return tmpdatestr;
    }

    public static String getFormatUnixTimeFromObjectByOffset(Object obj,
                                                             String format, int offsetseconds) {
        String tmpdatestr = "";
        if (obj != null) {
            try {
                int unixtime = Integer.parseInt(obj.toString());
                if (unixtime != 0) {
                    unixtime += offsetseconds;
                    Timestamp tmpdate = new Timestamp(unixtime * 1000L);
                    SimpleDateFormat tmpfmt = new SimpleDateFormat(format);
                    tmpdatestr = tmpfmt.format(tmpdate);
                }
            } catch (Exception ex) {

            }
        }
        return tmpdatestr;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static int getCurrentUnixTime() {
        int tmpCurrentTime = (int) (System.currentTimeMillis() / 1000);
        return tmpCurrentTime;
    }

    public static Date getCurrentTime() {
        Calendar ca = Calendar.getInstance();
        Date tmpnow = new Date(ca.getTimeInMillis());
        return tmpnow;
    }

    public static java.sql.Date getSqlCurrentTime() {
        Calendar ca = Calendar.getInstance();
        java.sql.Date tmpnow = new java.sql.Date(ca.getTimeInMillis());
        return tmpnow;
    }

    public static String getSqlCurrentTimeStr(String format) {
        String tmpret = "";
        try {
            Calendar ca = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            tmpret = sdf.format(ca.getTime());
        } catch (Exception ex) {
            log.error("getCurrentTimeStr:" + ex);
        }
        return tmpret;
    }

    public static String getSqlCurrentTimeStr(int seconds, String format) {
        String tmpret = "";
        try {
            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.SECOND, seconds);
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            tmpret = sdf.format(ca.getTime());
        } catch (Exception ex) {
            log.error("getCurrentTimeStr:" + ex);
        }
        return tmpret;
    }

    public static String formatDate(java.sql.Date date, String format) {
        String tmpdatestr = "";
        try {
            SimpleDateFormat f = new SimpleDateFormat(format);
            tmpdatestr = f.format(date);
        } catch (Exception ex) {
            log.error("formatDate :" + ex);
        }
        return tmpdatestr;
    }

    public static String formatDate(Date date, String format) {
        String tmpdatestr = "";
        try {
            SimpleDateFormat f = new SimpleDateFormat(format);
            tmpdatestr = f.format(date);
        } catch (Exception ex) {
            log.error("formatDate :" + ex);
        }
        return tmpdatestr;
    }

    public static Date getUtilDate(java.sql.Date date) {
        Date tmpUtilDate = new Date(date.getTime());
        return tmpUtilDate;
    }

    public static java.sql.Time getSqlTime(java.sql.Date date) {
        java.sql.Time sTime = new java.sql.Time(date.getTime());
        return sTime;
    }

    public static Timestamp getSqlTimeStamp(java.sql.Date date) {
        Timestamp stp = new Timestamp(date.getTime());
        return stp;
    }

    public static String formatDouble(double number, String format) {
        String tmpret = "";
        try {
            DecimalFormat df = new DecimalFormat(format);
            df.format(number);
        } catch (Exception ex) {
            log.error(" formatDobule:" + ex);
        }
        return tmpret;
    }

    public static Map removeNullObject(String string, Object[] object) {
        Map map = new HashMap();
        String str = string;
        Object[] obj = object;
        map.put("string", str);
        map.put("objects", obj);
        if (string == null || "".equals(string)) {
            return map;
        }
        String[] s = str.split("[?]");
        if (s.length <= 1) {
            return map;
        }
        List list = new ArrayList();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length; i++) {
            if (i + 1 < obj.length && obj[i] == null) {
                // 若下个有逗号的话，则去掉，若上个有=号的话，则去掉往前到空格或逗号的地方为止。
                s[i] = removePre("" + s[i]);
                s[i + 1] = removeNext("" + s[i + 1]);
            }
        }

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null || i == obj.length - 1) {
                list.add(obj[i]);
            }
        }

        for (int i = 0; i < s.length; i++) {
            if (s[i].trim().length() > 1
                    && (s[i].trim().endsWith("+") || s[i].trim().endsWith("=")
                    || s[i].trim().endsWith(",") || s[i].trim()
                    .endsWith("("))) {
                sb.append(" " + s[i].trim() + "?");
            } else {
                sb.append(s[i].trim());
            }
        }
        String sql = removeDouhao(sb.toString());
        map.put("string", sql);
        map.put("objects", list.toArray());
        return map;

    }

    private static String removeDouhao(String str) {
        String[] s = str.split("WHERE|where");
        String string = s[0];
        if (s[0].trim().endsWith(",")) {
            string = new String(s[0].trim().toCharArray(), 0, s[0].trim()
                    .toCharArray().length - 1);
        }
        return string + " WHERE " + s[1];
    }

    private static String removePre(String str) {
        if (str == null || str.trim().equals("") || !str.contains("=")) {
            return str;
        }
        String regEx = "\\w*\\s+=|\\w*=";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        boolean result = m.find();
        if (result) {
            str = str.trim();
            char[] ch = str.toCharArray();
            if (ch[ch.length - 1] != '=') {
                return str;
            }
            label1:
            for (int i = ch.length - 1; i >= 0; i--) {
                if (ch[i] == ' ' || ch[i] == ',') {
                    break label1;
                } else
                    ch[i] = ' ';
            }
            ch[ch.length - 1] = ' ';
            return new String(ch);
        }
        return str;
    }

    private static String removeNext(String str) {
        if (str == null || str.trim().equals("") || !str.contains(",")) {
            return str;
        }
        String regEx = ",\\w|,\\W|,";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        boolean result = m.find();
        if (result) {
            return str.replaceFirst(",", " ");
        }

        return str;
    }

    public static String formatInt(int val, int len) {
        String tmpstr = "";
        tmpstr = "" + val;
        int tmporglen = tmpstr.length();
        for (int i = tmporglen; i < len; i++)
            tmpstr = "0" + tmpstr;
        return tmpstr;
    }

    public static Date parseDateString(String str, String format) {
        Date tmpret = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            tmpret = sdf.parse(str);
        } catch (Exception ex) {

        }
        return tmpret;
    }

    public static int getByHourMiniuteSecond(String hms) {
        int tmpret = 0;
        if ((hms != null) && (hms.length() == 6)) {
            tmpret = SafeUtils.getInt(hms.substring(0, 2)) * 3600
                    + SafeUtils.getInt(hms.substring(2, 4)) * 60
                    + SafeUtils.getInt(hms.substring(4, 6));
        }
        return tmpret;
    }

    public static String formatDuration(int seconds) {
        String tmpret = "";
        int tmphours = seconds / 3600;
        int tmpminutes = (seconds - tmphours * 3600) / 60;
        int tmpseconds = seconds - tmphours * 3600 - tmpminutes * 60;
        tmpret = SafeUtils.formatInt(tmphours, 2)
                + SafeUtils.formatInt(tmpminutes, 2)
                + SafeUtils.formatInt(tmpseconds, 2);
        return tmpret;
    }

    public static void makesurePathExists(String path) {
        java.io.File file = new java.io.File(path);
        if (file.isDirectory()) {

        } else {
            file.mkdir();
        }
    }

    public static boolean isNotEmpty(String str) {
        if (str == null || str.equals("")) return false;
        else return true;
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.equals("")) return true;
        else return false;
    }


    public static String unixTime2Date(String timestampString, String formats) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats).format(new Date(timestamp));
        return date;
    }

    public static String unixTime2Date(Long timestampString, String formats) {
        String date = new SimpleDateFormat(formats).format(new Date(timestampString * 1000));
        return date;
    }

    public static final BigDecimal KeepTwoBigdecimalPlaces(BigDecimal bigDecimal) {
        return new BigDecimal(new DecimalFormat("#.00").format(bigDecimal));
    }

    /**
     * 根据身份证号获取年龄
     *
     * @param idCardNo
     * @return
     */
    public static int getAgeByIdCard(String idCardNo) {
        // 获取出生日期
        String birthday = idCardNo.substring(6, 10);
        /**
         Date birthdate = null;
         try {
         birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
         } catch (ParseException e) {
         e.printStackTrace();
         }
         //Date birthday = birthdate;
         GregorianCalendar currentDay = new GregorianCalendar();
         currentDay.setTime(birthdate);
         int year = currentDay.get(Calendar.YEAR);
         //int month = currentDay.get(Calendar.MONTH) + 1;
         //int day = currentDay.get(Calendar.DAY_OF_MONTH);
         **/
        //获取年龄
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String currentYear = simpleDateFormat.format(new Date());
        int age = Integer.parseInt(currentYear) - Integer.parseInt(birthday);
        return age;
    }
}
