package com.skyler.cobweb.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;


/**
 * Description:
 * <pre>
 *
 * </pre>
 * <p>
 * NB.
 * </p>
 * Created by skyler on 2019-07-25 at 14:44
 */
public class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final String ZERO_TIME = " 00:00:00";


    public static final int DATE_SIZE = 10;
    public static final int DATE_MINUTE = 16;
    public static final int DATETIME_SIZE = 19;

    public static final String[] WEEK_DAYS = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_DATE_FORMAT_2 = "yyyyMMddHHmmss";

    public static final String DEFAULT_MONTH_FORMAT = "yyyy-MM";

    public static final String DEFAULT_YEAR_FORMAT = "yyyy";

    public static final String DEFAULT_ONLY_MONTH_FORMAT = "MM";

    public static final String DEFAULT_ONLY_DAY_FORMAT = "dd";

    public static final String DEFAULT_DAY_FORMAT = "yyyy-MM-dd";

    public static final String DEFAULT_DAY_FORMAT_2 = "yyyyMMdd";

    public static final String DEFAULT_DAY_FORMAT_CHINA = "yyyy年MM月dd日";

    public static final String DEFAULT_DAY_FORMAT_CHINA_2 = "MM月dd日";

    public static final String DEFAULT_HOUR_FORMAT = "HH:mm:ss";

    public static final String HOUR_NO_SECOND_FORMAT = "HH:mm";

    public static final String formatToDay(Date date) {
        return format(date, DEFAULT_DAY_FORMAT);
    }

    public static String formatToDay(long millis) {
        return format(millis, DEFAULT_DAY_FORMAT);
    }

    public static String formatToHour(Date date) {
        return format(date, DEFAULT_HOUR_FORMAT);
    }

    public static String formatToHour(long millis) {
        return format(millis, DEFAULT_HOUR_FORMAT);
    }

    public static String format(Date date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }

    public static String format(long millis) {
        return format(millis, DEFAULT_DATE_FORMAT);
    }

    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    public static String format(long millis, String pattern) {
        return DateFormatUtils.format(millis, pattern);
    }

    public static Date parse(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error("【时间转换异常】 exception :{}", e);
        }
        return null;
    }

    /**
     * 计算两个日期的差值
     *
     * @param beginTime yyyy-MM-dd ex: 2019-07-20
     * @param endTime   yyyy-MM-dd ex: 2019-07-29
     * @return 正数：endTime > beginTime; 负数：endTime < beginTime; 0: ==
     */
    public static long numberOfDays(String beginTime, String endTime) throws Exception {
        LocalDateTime d1 = parse(beginTime);
        LocalDateTime d2 = parse(endTime);

        return d2.until(d1, ChronoUnit.DAYS);
    }

    public static long absNumberOfDays(String beginTime, String endTime) throws Exception {
        LocalDateTime d1 = parse(beginTime);
        LocalDateTime d2 = parse(endTime);

        return Math.abs(d2.until(d1, ChronoUnit.DAYS));
    }

    /**
     * <pre>
     *   desTime > sourceTime result > 0
     *   desTime < sourceTime result < 0
     *   desTime = sourceTime result = 0
     * </pre>
     *
     * @param desTime
     * @param sourceTime
     * @return
     */
    public static int compare(String desTime, String sourceTime) {
        return desTime.compareTo(sourceTime);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss截断成yyyy-MM-dd
     *
     * @param dateTime
     * @return
     */
    public static String cufOff(String dateTime) {

        if (StringUtils.isBlank(dateTime)) {
            return "";
        }
        if (dateTime.length() > DATE_SIZE) {
            return dateTime.substring(0, DATE_SIZE);
        }
        return dateTime;
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss截断成yyyy-MM-dd HH:mm
     *
     * @param dateTime
     * @return
     */
    public static String cufOffSecond(String dateTime) {
        if (dateTime.length() > DATE_MINUTE) {
            return dateTime.substring(0, DATE_MINUTE);
        }
        return dateTime;
    }

    public static LocalDateTime parse(String date) throws Exception {
        if (StringUtils.isBlank(date)) {
            throw new Exception("DateUtil.parse(date)输入参数不能为空");
        }
        if (date.length() < DATETIME_SIZE) {
            date = date + ZERO_TIME;
        }
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
        return dateTime;
    }

    public static boolean before(String targetTime, String sourceTime) throws Exception {
        LocalDateTime target = parse(targetTime);
        LocalDateTime source = parse(sourceTime);
        return target.isBefore(source);
    }

    public static boolean after(String targetTime, String sourceTime) throws Exception {
        LocalDateTime target = parse(targetTime);
        LocalDateTime source = parse(sourceTime);
        return target.isAfter(source);
    }


    /**
     * 比较两个日期大小；天维度比较
     *
     * @param targetDate
     * @param sourceDate
     */
    public static boolean afterWithDay(Date targetDate, Date sourceDate) {
        return afterWithDay(targetDate, sourceDate, 0);
    }

    /**
     * 比较两个日期大小；天维度比较
     *
     * @param targetDate
     * @param sourceDate
     * @param targetDayStep
     */
    public static boolean afterWithDay(Date targetDate, Date sourceDate, int targetDayStep) {
        LocalDate ld1 = targetDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ld2 = sourceDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ld1.plusDays(targetDayStep).isAfter(ld2);
    }

    /**
     * 两个日期的天数间隔
     *
     * @param l1
     * @param l2
     * @return l2 > l1 ==> 正数； l2 = l1 ==> 0； l2 < l1 ==> 负数
     */
    public static long distanceDays(LocalDate l1, LocalDate l2) {
        return l1.until(l2, ChronoUnit.DAYS);
    }

    public static String previewOneDate(String date) {
        return previewNDate(date, 1);
    }

    /**
     * @param date
     * @param n
     * @return
     */
    public static String previewNDate(String date, int n) {
        if (n < 0) {
            n = Math.abs(n);
        }
        LocalDate localDate = LocalDate.parse(date);
        return localDate.minusDays(n).format(DateTimeFormatter.ofPattern(DEFAULT_DAY_FORMAT));
    }

    public static String nextOneDate(String date) {
        return nextNDate(date, 1);
    }

    public static String nextNDate(String date, int n) {
        if (n < 0) {
            n = Math.abs(n);
        }
        LocalDate localDate = LocalDate.parse(date);
        return localDate.plusDays(n).format(DateTimeFormatter.ofPattern(DEFAULT_DAY_FORMAT));
    }

    /**
     * 获取指定日期的前一天日期
     *
     * @param date
     * @return
     */
    public static Date previewOneDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(localDateTime.minusDays(1).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定日期的前一天最大日期。例如：入参2019-10-10，前一天最大日期为2019-10-09 23:59:59
     *
     * @param date
     * @return
     */
    public static Date previewOneDateWithMax(Date date) {
        return someDateWithMax(date, 1);
    }

    public static Date currentDateWithMax(Date date) {
        return someDateWithMax(date, 0);
    }

    /**
     * 获取指定日期的前一天最大日期。
     * <pre>
     *    例如：
     *      date:2019-10-10
     *      days:1
     *    结果：
     *      前一天最大日期为2019-10-09 23:59:59
     * </pre>
     *
     * @param date 指定日期
     * @param days 天数跨度
     * @return
     */
    public static Date someDateWithMax(Date date, int days) {
        if(Objects.isNull(date)) {
            return null;
        }
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(days).atTime(23, 59,59);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定日期的前一天最小日期。
     * <pre>
     *    例如：
     *      date:2019-10-10
     *      days:1
     *    结果：
     *      前一天最大日期为2019-10-09 23:59:59
     * </pre>
     *
     * @param date 指定日期
     * @param days 天数跨度
     * @return
     */
    public static Date someDateWithMin(Date date, int days) {
        if(Objects.isNull(date)) {
            return null;
        }
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(days).atTime(0, 0,0);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date currentDateWithMin(Date date) {
        return someDateWithMin(date, 0);
    }

        /**
         * 获取指定日期的下一天日期
         *
         * @param date
         * @return
         */
    public static Date nextOneDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(localDateTime.plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDateTime localDateTime){
        if(Objects.isNull(localDateTime)) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDate localDate){
        if(Objects.isNull(localDate)) {
            return null;
        }
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime toLocalDateTime(Date date){
        if(Objects.isNull(date)) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate toLocalDate(Date date){
        if(Objects.isNull(date)) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 获取指定日期 精确到时分秒
     *
     * @param date 2019-07-20 or 2019-07-20 15:50:24
     * @param step
     * @return
     */
    public static String specialDate(String date, BigDecimal step) {
        if (StringUtils.isBlank(date)) {
            return "";
        }
        // pattern 匹配
        if (date.length() > 10) {
            return handleDateTime(date, step);
        }
        return handleDate(date, step);

    }

    /**
     * 获取指定日期 精确到时分秒
     *
     * @param date 2019-07-20
     * @param step
     * @return
     */
    private static String handleDate(String date, BigDecimal step) {
        LocalDate localDate = LocalDate.parse(date);
        long duration = step.multiply(new BigDecimal(3600 * 24)).longValue();
        return localDate.atStartOfDay().plusSeconds(duration).format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }

    /**
     * 获取指定日期 精确到时分秒
     *
     * @param date 2019-07-20 15:50:24
     * @param step
     * @return
     */
    private static String handleDateTime(String date, BigDecimal step) {
        LocalDateTime localDate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
        long duration = step.multiply(new BigDecimal(3600 * 24)).longValue();
        return localDate.plusSeconds(duration).format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }

    /**
     * 字符串转成日期
     *
     * @param dateStr 格式为1970-01-01 00:00:00
     * @return Date
     */
    public static Date str2Date(String dateStr) {
        return Date.from(LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)).toInstant(ZoneOffset.UTC));
    }

    /**
     * 获取日期纪元时间
     *
     * @return
     */
    public static Date getTimeEpoch() {
        return Date.from(LocalDateTime.parse("1970-01-01 00:00:00", DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取日期的最大时间
     *
     * @return
     */
    public static Date getMaxTime() {
        return Date.from(LocalDateTime.parse("9999-01-01 00:00:00", DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将传入date去掉时分秒
     *
     * @param date
     * @return
     */
    public static Date parseDateToDay(Date date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DAY_FORMAT);
        String s = sdf.format(date);
        Date dateResult = sdf.parse(s);
        return dateResult;
    }

    public static void main(String[] args) throws Exception{
        String beginTime = "2019-07-20 15:50:24";
        String endTime = "2019-07-29";
        //System.out.println("peroid:" + numberOfDays(beginTime, endTime));
        //System.out.println(cufOffSecond(beginTime));
        System.out.println(currentDateWithMin(new Date()));
    }


}
