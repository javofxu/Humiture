package com.example.humiture.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Skygge,  Date on 2019/4/19.
 * dec:
 */
public class TimeUtils {

    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DAY_TIME_FORMAT = "yyyy-MM-dd";
    public static final String DAY_FOR_TIME = "HH:mm:ss";
    public static final int LABEL_SECOND = 0x1001;
    public static final int LABEL_MINUTE = 0x1002;
    public static final int LABEL_HOUR = 0x1003;
    public static final int LABEL_DAY = 0x1004;
    public static final int LABEL_MONTH = 0x1005;
    public static final int LABEL_YEAR = 0x1006;
    public static final int LABEL_DAY_OF_YEAR = 0x1007;

    public static String getCurrentTime() {
        SimpleDateFormat currentTime = new SimpleDateFormat("H:mm:ss", Locale.getDefault());
        return currentTime.format(System.currentTimeMillis());
    }

    private static String getMinuteSoOnTime(Date time) {
        SimpleDateFormat currentTime = new SimpleDateFormat("H:mm:ss", Locale.getDefault());
        return currentTime.format(time);
    }

    public static String getCurrentStringTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT, Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    public static String getCurrentStringTime(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    public static Date getDateTime(String format, String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static String formatTime(String time, String beforeFormatType, String formatType) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType, Locale.getDefault());
            SimpleDateFormat beforeDateFormat = new SimpleDateFormat(beforeFormatType, Locale.getDefault());
            return simpleDateFormat.format(beforeDateFormat.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getCurrentStringTime();
    }

    public static int getYearAndMonthAndDay(int type, String timeString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT, Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(timeString));
            switch (type) {
                case LABEL_YEAR:
                    return calendar.get(Calendar.YEAR);
                case LABEL_MONTH:
                    return calendar.get(Calendar.MONTH) + 1;
                case LABEL_DAY:
                    return calendar.get(Calendar.DAY_OF_MONTH);
                case LABEL_HOUR:
                    return calendar.get(Calendar.HOUR_OF_DAY);
                case LABEL_DAY_OF_YEAR:
                    return calendar.get(Calendar.DAY_OF_YEAR);
                case LABEL_MINUTE:
                    return calendar.get(Calendar.MINUTE);
                case LABEL_SECOND:
                    return calendar.get(Calendar.SECOND);
                default:
                    return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取当天时间
     * @return
     */
    public static String getNowDay(){
        String currentTime = getCurrentStringTime();
        return formatTime(currentTime, TimeUtils.DEFAULT_TIME_FORMAT, TimeUtils.DAY_TIME_FORMAT);
    }

    /**
     * 获取前一天时间
     * @return
     */
    public static String getYesterday(){
        SimpleDateFormat sf = new SimpleDateFormat(DAY_TIME_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        String date = sf.format(calendar.getTime());
        return date;
    }

    /**
     * 获取选定时间的前一天
     * @param date 选定的时间
     * @return
     */
    public static String dataForYesterday(String date){
        SimpleDateFormat sf = new SimpleDateFormat(DAY_TIME_FORMAT);
        final long nd = 24 * 3600 * 1000;
        String yesterday = null;
        try {
            Date mDate =sf.parse(date);
            long time = mDate.getTime() - nd;
            Date day = new Date(time);
            yesterday = sf.format(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return yesterday;
    }

    /**
     * 获得当天零时零分零秒
     * @return
     */
    public static String todayFirstDate(){
        SimpleDateFormat sf = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        String day = sf.format(calendar.getTime());
        return day;
    }

    /**
     * 获得当天23点59分59秒
     * @return
     */
    public static String todayLastDate() {
        SimpleDateFormat sf = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return sf.format(calendar.getTime());
    }

    /**
     * 获得前一天零时零分零秒
     * @return
     */
    public static String yesterdayFirstDate() {
        SimpleDateFormat sf = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return sf.format(calendar.getTime());
    }

    /**
     * 将时间戳转换成字符串
     * @param milSecond
     * @return
     */
    public static String getDateToString(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        return format.format(date);
    }

    /**
     * 将字符串转换成时间戳
     * @param dateString
     * @param pattern
     * @return
     */
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try{
            date = dateFormat.parse(dateString);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }
}
