package com.example.kotlindemo.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间工具类
 * Created by chenhongfei on 2015/8/17.
 */
public class DatetimeUtil {

    /**
     * 把时间戳转成标准的datetime 格式 YYYY-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String getFormatDatetime(long time) {
        String datetime = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            datetime = sdf.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datetime;
    }


    /**
     * 把毫秒数转换成时分秒
     *
     * @param millis
     * @return
     */
    public static String millisToStringShort(long millis) {
        StringBuilder strBuilder = new StringBuilder();
        long temp = millis;
        long hper = 60 * 60 * 1000;
        long mper = 60 * 1000;
        long sper = 1000;
        if (temp / hper > 0) {
            strBuilder.append(temp / hper).append("小时");
        }
        temp = temp % hper;

        if (temp / mper > 0) {
            strBuilder.append(temp / mper).append("分钟");
        }
        temp = temp % mper;
        if (temp / sper > 0) {
            strBuilder.append(temp / sper).append("秒");
        }
        return strBuilder.toString();
    }


    /**
     * 将毫秒数转换成 时分秒  00：00：00形式（如果不够时则只显示分秒）
     *
     * @param millis
     * @return
     */
    public static String millisToHMS(long millis) {
        StringBuilder strBuilder = new StringBuilder();
        long temp = millis;
        long hper = 60 * 60 * 1000; //时
        long mper = 60 * 1000; //分
        long sper = 1000;//秒
        if (temp / hper > 0) {
            if (temp / hper > 9) {
                strBuilder.append(temp / hper).append(":");
            } else {
                strBuilder.append("0").append(temp / hper).append(":");
            }
        }
        temp = temp % hper;

        if (temp / mper > 0) {
            if (temp / mper > 9) {
                strBuilder.append(temp / mper).append(":");
            } else {
                strBuilder.append("0").append(temp / mper).append(":");
            }
        } else {
            strBuilder.append("00:");
        }
        temp = temp % mper;
        if (temp / sper > 0) {
            if (temp / sper > 9) {
                strBuilder.append(temp / sper).append("");
            } else {
                strBuilder.append("0").append(temp / sper).append("");
            }
        }
        return strBuilder.toString();
    }

    /**
     * 把时间戳转成标准的datetime 格式 YYYY-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String getFormatDatetime2(long time) {
        String datetime = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
            datetime = sdf.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datetime;
    }

    public static String getFormatDatetime2(long time, String format) {
        String datetime = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            datetime = sdf.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datetime;
    }

    /**
     * 根据秒数获取时间串
     *
     * @param second (eg: 100s)
     * @return (eg : 00 : 01 : 40)
     */
    public static String getTimeStrBySecond(int second) {
        if (second <= 0) {

            return "00:00:00";
        }

        StringBuilder sb = new StringBuilder();
        int hours = second / (60 * 60);
        if (hours > 0) {

            second -= hours * (60 * 60);
        }

        int minutes = second / 60;
        if (minutes > 0) {

            second -= minutes * 60;
        }

        return (hours >= 10 ? (hours + "")
                : ("0" + hours) + ":" + (minutes >= 10 ? (minutes + "") : ("0" + minutes)) + ":"
                + (second >= 10 ? (second + "") : ("0" + second)));
    }

    /**
     * Java将Unix戳转换成指定格式日期字符串时间
     *
     * @param timestampString 时间戳 如："1473048265";
     * @param formats         要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String TimeStamp2Date(String timestampString, String formats) {
        if (TextUtils.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    /**
     * 戳转换成指定格式日期字符串时间
     *
     * @param times
     * @param formats
     * @return
     */
    public static String TimeStamp2Date(long times, String formats) {
        if (TextUtils.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(times));
        return date;
    }


    //毫秒数转日期
    public static String TimeStamp3Date(String timestampString, String formats) {
        if (TextUtils.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString);
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }


    //xx分xx秒
    public static String formattime(long time) {

        String min = (time / (1000 * 60)) + "";

        String second = (time % (1000 * 60) / 1000) + "";

        if (min.length() < 2) {
            min = 0 + min;
        }
        if (second.length() < 2) {
            second = 0 + second;
        }
        return min + "分" + second + "秒";
    }

    //xx：xx
    public static String formattime2(long time) {

        String min = (time / (1000 * 60)) + "";

        String second = (time % (1000 * 60) / 1000) + "";

        if (min.length() < 2) {
            min = 0 + min;
        }
        if (second.length() < 2) {
            second = 0 + second;
        }
        return min + ":" + second;
    }

    /**
     * 日期转星期
     *
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime, String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    /**
     * 时间戳转换成星期几
     *
     * @param timeStamp
     * @return
     */
    public static String getWeekDay(long timeStamp) {
        Calendar cal = zeroFromHour(timeStamp);
        String whatDay = "";
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            whatDay = "星期六";
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            whatDay = "星期日";
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            whatDay = "星期一";
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            whatDay = "星期二";
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            whatDay = "星期三";
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            whatDay = "星期四";
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            whatDay = "星期五";
        }
        return whatDay;
    }

    /**
     * 将时间戳转换成当天零点的时间戳
     *
     * @param milliseconds
     * @return
     */
    private static Calendar zeroFromHour(long milliseconds) {
        Calendar calendar = Calendar.getInstance(); // 获得一个日历

        calendar.setTimeInMillis(completMilliseconds(milliseconds));
        zeroFromHour(calendar);
        return calendar;
    }

    /**
     * 将时，分，秒，以及毫秒值设置为0
     *
     * @param calendar
     */
    private static void zeroFromHour(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 由于服务器返回的是10位，手机端使用需要补全3位
     *
     * @param milliseconds
     * @return
     */
    private static long completMilliseconds(long milliseconds) {
        String milStr = Long.toString(milliseconds);
        if (milStr.length() == 10) {
            milliseconds = milliseconds * 1000;
        }
        return milliseconds;
    }


    /**
     * 自定义日期时间格式
     *
     * @param time
     * @param format
     * @return
     */
    public static String getFormatDatetime(long time, String format) {
        String datetime = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            datetime = sdf.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datetime;
    }

    /**
     * 给定一个时间缀，返回该时间为上午或下午
     *
     * @param time
     * @return
     */
    public static String getAM_PM(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        String format = simpleDateFormat.format(new Date(time));
        if (Integer.parseInt(format) < 12) {
            //上午
            return "上午";
        } else {
            //下午
            return "下午";
        }
    }

    /**
     * 自定义当前日期时间格式
     *
     * @param format
     * @return
     */
    public static String getCurrentFormatDatetime(String format) {
        String datetime = "";
        try {
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            datetime = sdf.format(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datetime;
    }

    /**
     * 获取当前的日期时间
     *
     * @return
     */
    public static String getCurrentDatetime(String format) {
        String datetime = "";
        try {
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            datetime = sdf.format(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datetime;
    }

    /**
     * 获取当前的日期时间,精确到毫秒
     *
     * @return
     */
    public static String getCurrentDatetimeMilli() {
        return getCurrentFormatDatetime("yyyy-MM-dd HH:mm:ss.S");
    }


    /**
     * 获取明天的中文短时间格式 ：yyyy 年 mm 月 dd 日 00：00
     *
     * @return
     */
    public static String getNextDayShortCHDatetime() {
        Calendar calendar = Calendar.getInstance();
        String shortDatetime = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月" + (calendar.get(Calendar.DAY_OF_MONTH) + 1) + "日"
                + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);

        return shortDatetime;
    }

    /**
     * yyyy年MM月dd日 HH:mm 转成timestamp 单位是毫秒
     *
     * @param strDatetime
     * @return
     */
    public static long datetimeToTimestamp(String strDatetime, String format) {
        long timestamp = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date d = sdf.parse(strDatetime);
            timestamp = d.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return timestamp;
    }


    public static long datetimeToTimestamp(String strDatetime) {
        return datetimeToTimestamp(strDatetime, "yyyy-MM-dd HH:mm");
    }

    /**
     * 获取二个日期时间的间隔时间 ，单位毫秒
     */
    public static long getTimeInterval(String datetime1, String datetime2) {
        long interval = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        try {
            Date date1 = sdf.parse(datetime1);
            Date date2 = sdf.parse(datetime2);
            interval = Math.abs((date1.getTime() - date2.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return interval;
    }

    /**
     * 能力评估，格式化日期
     *
     * @param datetime
     * @return
     */
    public static String formatAbilityForecastDate(String datetime) {
        String formatRet = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdfResult = new SimpleDateFormat("MM-dd");
        try {
            Date date = sdf.parse(datetime);
            formatRet = sdfResult.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return formatRet;
    }

    public static boolean isToday(long time) {
        boolean result = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(new Date());
            String date = sdf.format(new Date(time));
            result = today.equals(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isYesterday(long time) {
        boolean result = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String yesterday = sdf.format(new Date(System.currentTimeMillis() - 24 * 3600 * 1000));
            String date = sdf.format(new Date(time));

            result = yesterday.equals(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static long HMS2Time(String datetime) {
        long timestamp = 0L;
        if (TextUtils.isEmpty(datetime)) {
            return timestamp;
        }
        String[] tArray = datetime.split(":");
        if (null == tArray || tArray.length <= 0) {
            return timestamp;
        }
        if (tArray.length >= 1) {
            timestamp += 3600 * Integer.parseInt(tArray[0]);
        }
        if (tArray.length >= 2) {
            timestamp += 60 * Integer.parseInt(tArray[1]);
        }
        if (tArray.length >= 3) {
            timestamp += Integer.parseInt(tArray[2]);
        }

        return timestamp;
    }

    // 获得当前日期与本周一相差的天数
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * i 为了0，当前周一，i为6，当前周日
     *
     * @param i
     * @return
     */
    public static String getCurrentFormatWeekDate(int i, String format) {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + i);
        Date monday = currentDate.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String weekDate = sdf.format(monday);
        return weekDate;
    }

    public static long getWeekDayTime(int i) {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + i);
        Date date = currentDate.getTime();
        long timestamp = date.getTime();
        return timestamp;
    }

    public static int getCurrentWeekDay() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int weekDay = cd.get(Calendar.DAY_OF_WEEK) - 1;

        return weekDay;
    }

    public static boolean isInTwoTime(String datetime, String minDatetime, String maxDatetime, String format) {
        long time = datetimeToTimestamp(datetime, format);
        long minTime = datetimeToTimestamp(minDatetime, format);
        long maxTime = datetimeToTimestamp(maxDatetime, format);
        if (time >= minTime && time <= maxTime) {
            return true;
        }

        return false;
    }

    public static String toCHWeekDay(int weekDay) {
        String cnWeekDay = "";
        switch (weekDay) {
            case 0:
                cnWeekDay = "日";
                break;
            case 1:
                cnWeekDay = "一";
                break;
            case 2:
                cnWeekDay = "二";
                break;
            case 3:
                cnWeekDay = "三";
                break;
            case 4:
                cnWeekDay = "四";
                break;
            case 5:
                cnWeekDay = "五";
                break;
            case 6:
                cnWeekDay = "六";
                break;
        }

        return cnWeekDay;
    }

    public static String toHMS(long time) {
        String h = time / 3600 > 9 ? "" + time / 3600 : "0" + time / 3600;
        String m = (time % 3600) / 60 > 9 ? "" + (time % 3600) / 60 : "0" + (time % 3600) / 60;
        String s = (time % 60) > 9 ? "" + (time % 60) : "0" + (time % 60);

        String ret = String.format("%s:%s:%s", h, m, s);
//        AspLog.v("jsonjson", ret);
        return ret;
    }

    public static String toMS(long time) {
        String h = time / 3600 > 9 ? "" + time / 3600 : "0" + time / 3600;
        String m = (time % 3600) / 60 > 9 ? "" + (time % 3600) / 60 : "0" + (time % 3600) / 60;
        String s = (time % 60) > 9 ? "" + (time % 60) : "0" + (time % 60);

        String ret = String.format("%s:%s", m, s);
//        AspLog.v("jsonjson", String.format("time:%d, ret:%s", time, ret));
        return ret;
    }

    /**
     * 获取人类可读的周几
     *
     * @param time
     * @return
     */
    public static String toHMWeekday(long time) {
        String week = getWeekOfTimestamp(time);
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTime().getTime();

        long oneDay = 24 * 3600 * 1000;
        long todayZero = currentTime / (oneDay) * (oneDay) - TimeZone.getDefault().getRawOffset();
        long todayEnd = todayZero + oneDay - 1;
        long yesterdayZero = todayZero - oneDay;
        long yesterdayEnd = yesterdayZero + oneDay - 1;
        long qiantianZero = yesterdayZero - oneDay;
        long qiantianEnd = qiantianZero + oneDay - 1;
        if (time >= todayZero && time <= todayEnd) {
            week = "今天";
        } else if (time >= yesterdayZero && time <= yesterdayEnd) {
            week = "昨天";
        } else if (time >= qiantianZero && time <= qiantianEnd) {
            week = "前天";
        }
        return week;
    }


    /**
     * 判断选择的日期是否是本周
     */
    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }


    /**
     * 返回消息列表规定时间格式
     * 时间格式：
     * 当天：显示HH-mm
     * 昨天：显示昨天
     * 一周内：显示星期几（从今天算起）
     * 其他：显示：yyyy/MM/dd
     */
    public static String toTimeByMessage(long time) {
        if (isThisWeek(time)) {
            //本周
            if (isToday(time)) {
                //是否为今天，直接返回当天时间
                return TimeStamp2Date(time, "H:mm");
            } else if (isYesterday(time)) {
                return "昨天";
            } else {
                //本周内返回周几
                return getWeekDay(time);
            }
        } else {
            return TimeStamp2Date(time, "yyyy/M/dd");
        }
    }

    /**
     * 返回消息列表规定时间格式
     * 时间格式：
     * 当天：显示今天HH-mm
     * 昨天：显示昨天HH-mm
     * 一周内：显示星期几 HH-mm（从今天算起）
     * 其他：显示：MM月dd日HH-mm
     */
    public static String toTimeBySingleMessage(long time) {
        if (isThisWeek(time)) {
            //本周
            if (isToday(time)) {
                //是否为今天，直接返回当天时间
                return "今天" + TimeStamp2Date(time, "HH:mm");
            } else if (isYesterday(time)) {
                return "昨天" + TimeStamp2Date(time, "HH:mm");
            } else {
                //本周内返回周几
                return getWeekDay(time) + TimeStamp2Date(time, "HH:mm");
            }
        } else {
            return TimeStamp2Date(time, "M月dd日 HH:mm");
        }
    }

    /**
     * 返回消息列表规定时间格式
     * 时间格式：
     * 当天：显示今天HH-mm
     * 昨天：显示昨天HH-mm
     * 一周内：显示星期几 HH-mm（从今天算起）
     * 其他：显示：MM月dd日HH-mm
     */
    public static String toTimeBySingleMessage3(long time) {

        return TimeStamp2Date(time, "M月dd日");
    }

    public static String toTimeBySingleMessage2(long time) {
        if (isThisWeek(time)) {
            //本周
            if (isToday(time)) {
                //是否为今天，直接返回当天时间
                return "今天";
            } else if (isYesterday(time)) {
                return "昨天";
            } else {
                //本周内返回周几
                return getWeekDay(time);
            }
        } else {
            return TimeStamp2Date(time, "M月dd日");
        }
    }

    public static String toDayDesc(long time) {
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTime().getTime();

        long oneDay = 24 * 3600 * 1000;
        long todayZero = currentTime / (oneDay) * (oneDay) - TimeZone.getDefault().getRawOffset();
        long todayEnd = todayZero + oneDay - 1;
        long yesterdayZero = todayZero - oneDay;
        long yesterdayEnd = yesterdayZero + oneDay - 1;
        long qiantianZero = yesterdayZero - oneDay;
        long qiantianEnd = qiantianZero + oneDay - 1;
        String timeDesc;
        if (time >= todayZero && time <= todayEnd) {
            timeDesc = "今天";
        } else if (time >= yesterdayZero && time <= yesterdayEnd) {
            timeDesc = "昨天";
        } else if (time >= qiantianZero && time <= qiantianEnd) {
            timeDesc = "前天";
        } else if (belongThisYear(time)) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            timeDesc = sdf.format(new Date(time));
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            timeDesc = sdf.format(new Date(time));
        }
        return timeDesc;
    }

    public static boolean belongThisYear(long time) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year, 0, 0);
        long yearTime = calendar.getTimeInMillis();
        return time >= yearTime;
    }

    public static String getWeekOfTimestamp(long time) {
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        if (time > 0) {
            Date date = new Date(time);
            calendar.setTime(date);
        }

        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }


    /**
     * 仿微信朋友圈时间格式
     *
     * @param
     * @return
     */
    public static String timeLogic(long targetTime) {
        Calendar calendar = Calendar.getInstance();
        long now = calendar.getTimeInMillis();

        // 相差的秒数
        long time = (now - targetTime) / 1000;

        String dateStr = getFormatDatetime(targetTime, "yyyy-MM-dd");

        StringBuffer sb = new StringBuffer();
        if (time > 0 && time < 60) { // 1小时内
            return sb.append(time + "秒前").toString();
        } else if (time > 60 && time < 3600) {
            return sb.append(time / 60 + "分钟前").toString();
        } else if (time >= 3600 && time < 3600 * 24) {
            return sb.append(time / 3600 + "小时前").toString();
        } else if (time >= 3600 * 24 && time < 3600 * 48) {
            return sb.append("昨天").toString();
        } else if (time >= 3600 * 48 && time < 3600 * 72) {
            return sb.append("前天").toString();
        } else if (time >= 3600 * 72 && isThisYear(dateStr.substring(0, 4))) {
            return dateStr.substring(5, 10);
        }
        return dateStr.substring(0, 10);
    }

    /**
     * 仿微信朋友圈时间格式
     *
     * @param dateStr 计算时间差
     * @return
     */
    public static String timeLogic(String dateStr, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
        long now = calendar.getTimeInMillis();
        Date date = strToDate(dateStr, format);
        calendar.setTime(date);
        long past = calendar.getTimeInMillis();

        // 相差的秒数
        long time = (now - past) / 1000;

        StringBuffer sb = new StringBuffer();
        if (time > 0 && time < 60) { // 1小时内
            return sb.append(time + "秒前").toString();
        } else if (time > 60 && time < 3600) {
            return sb.append(time / 60 + "分钟前").toString();
        } else if (time >= 3600 && time < 3600 * 24) {
            return sb.append(time / 3600 + "小时前").toString();
        } else if (time >= 3600 * 24 && time < 3600 * 48) {
            return sb.append("昨天").toString();
        } else if (time >= 3600 * 48 && time < 3600 * 72) {
            return sb.append("前天").toString();
        } else if (time >= 3600 * 72 && isThisYear(dateStr.substring(0, 4))) {
            return dateStr.substring(5, 10);
        }
        return dateStr.substring(0, 10);
    }

    /**
     * 日期字符串转换为Date
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date strToDate(String dateStr, String format) {
        Date date = null;

        if (!TextUtils.isEmpty(dateStr)) {
            DateFormat df = new SimpleDateFormat(format);
            try {
                date = df.parse(dateStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static boolean isThisYear(String year) {
        return formatYearTime().equals(year);
    }

    public static String formatYearTime() {
        String ret = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            ret = sdf.format(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;

    }

    /**
     * 毫秒转换为指定格式的日期
     *
     * @param second
     * @param patten
     * @return
     */
    public static String secondToDate(long second, String patten) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second);//转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String dateString = format.format(date);
        return dateString;
    }

    /**
     * 返回日时分秒
     *
     * @param second
     * @return
     */
    public static String secondToTime(long second) {
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        if (0 < days) {
            return days + "天" + hours + "小时" + minutes + "分";
        } else {
            if (hours == 0) {
                if (second < 1) {
                    return second + "秒";
                } else {
                    return minutes + "分" + second + "秒";
                }
            }
            return hours + "小时" + minutes + "分";
        }
    }

    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;

    }
}
