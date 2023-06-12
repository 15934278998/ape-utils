package com.ape9527.utils.date;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
public class DateUtil {

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前Date型日期
     *
     * @return 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @param format 指定格式
     * @return 格式话后日期
     */
    public static String getDate(String format) {
        if (format == null) {
            format = YYYY_MM_DD;
        }
        return parseDateToStr(format, getNowDate());
    }

    /**
     * 获取当前日期时间, 默认格式为yyyy-MM-dd HH:mm:ss
     *
     * @param format 指定格式
     * @return 格式话后日期
     */
    public static String getDateTime(String format) {
        if (format == null) {
            format = YYYY_MM_DD_HH_MM_SS;
        }
        return parseDateToStr(format, getNowDate());
    }

    /**
     * 格式化日期为字符串
     *
     * @param format 格式
     * @param date   日期
     * @return 格式化后字符串
     */
    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式化字符串为日期
     *
     * @param format 格式
     * @param ts     字符串
     * @return 格式化后日期
     */
    public static final Date parseStrToDate(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取服务器启动时间
     *
     * @return 启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     *
     * @param endDate 结束时间
     * @param nowDate 开始时间
     * @return 相差时间
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获取某一天的某个时间点
     *
     * @param date 某一天
     * @param time 时间点，HH:mm:ss格式
     * @return 时间
     */
    public static Date getSomedayTime(Date date, String time) {
        String format = DateFormatUtils.format(date, "yyyy-MM-dd");
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format + " " + time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到几天后的时间
     *
     * @param date 时间
     * @param day 天数
     * @return 时间
     */
    public static Date getDateAfter(Date date, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 计算现在的时间时候在今天的某个时间点之前
     * * 如果是，返回今日这个时间点
     * * 如果不是，返回明天的这个时间点
     *
     * @param time 时间
     * @return 时间
     */
    public static Date getNextTime(String time) {
        Date nowDate = getNowDate();
        String format = DateFormatUtils.format(nowDate, "yyyy-MM-dd");
        format += " " + time;
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (nowDate.getTime() < date.getTime()) {
            return date;
        } else {
            Date dateAfter = getDateAfter(nowDate, 1);
            return getSomedayTime(dateAfter, time);
        }
    }

    /**
     * 计算某个时间是否在当天的某个时间点之前
     * * 如果是，返回今日这个时间点
     * * 如果不是，返回明天的这个时间点
     *
     * @param date 某个时间
     * @param time 某个时间点，HH:mm:ss格式
     * @return 计算后的时间
     */
    public static Date getSomedayNextTime(Date date, String time) {
        String format = DateFormatUtils.format(date, "yyyy-MM-dd");
        format += " " + time;
        Date newDate = null;
        try {
            newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format);
            if (date.getTime() < newDate.getTime()) {
                return newDate;
            } else {
                Date dateAfter = getDateAfter(date, 1);
                return getSomedayTime(dateAfter, time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改date的时区
     *
     * @param date     一个日期
     * @param timeZone 时区：北京时间，Asia/Shanghai；东京时间，Asia/Tokyo；伦敦时间，Europe/London
     * @return 格式化后的日期
     */
    public static String setDateZone(Date date, String timeZone) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone(timeZone));
        return format.format(date);
    }

}
