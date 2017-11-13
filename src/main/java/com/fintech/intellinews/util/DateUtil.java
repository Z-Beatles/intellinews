package com.fintech.intellinews.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author waynechu
 * Created 2017-10-19 11:20
 */
public class DateUtil {

    /**
     * 计算详情显示时间
     */
    private static String[] TIME_UNIT = {"秒", "分钟", "小时"};

    private DateUtil() {
    }

    public static LocalDateTime toLocalDateTimeFromJdkDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static Date toJdkDateFromLocalDateTime(LocalDateTime date) {
        Instant instant = date.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDate toLocalDateFromDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

    public static Date toJdkDateFromLocalDate(LocalDate date) {
        Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalTime toLocalTimeFromJdkDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
    }

    public static Date toJdkDateFromLocalTime(LocalTime date) {
        Instant instant = date.atDate(LocalDate.of(2000, 1, 1)).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDate toLocalDateFromString(String text, String format) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(format));
    }

    public static LocalDate toLocalDateFromString(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        } else if (StringUtils.containsAny(text, "年月日")) {
            return toLocalDateFromString(text, "uuuu年MM月dd日");
        } else if (StringUtils.contains(text, "-")) {
            return toLocalDateFromString(text, "uuuu-MM-dd");
        }
        return LocalDate.parse(text);
    }

    public static String toStringFromLocalDate(LocalDate date, String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    public static String toStringFromLocalDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        return toStringFromLocalDate(date, "uuuu-MM-dd");
    }

    public static LocalDateTime toLocalDateTimeFromString(String text, String format) {
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(format));
    }

    public static LocalDateTime toLocalDateTimeFromString(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        } else if (StringUtils.contains(text, "-")) {
            return toLocalDateTimeFromString(text, "uuuu-MM-dd HH:mm:ss");
        }
        return LocalDateTime.parse(text);
    }

    public static String toStringFromLocalDateTime(LocalDateTime date, String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    public static String toStringFromLocalDateTime(LocalDateTime date) {
        if (date == null) {
            return "";
        }
        return toStringFromLocalDateTime(date, "uuuu-MM-dd HH:mm:ss");
    }

    public static String toStringFromDate(Date date, String format) {
        return DateFormatUtils.format(date, format);
    }

    public static String toStringFromDate(Date date) {
        if (date == null) {
            return "";
        }
        return toStringFromDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String toCustomStringFromDate(Date date) {
        if (date == null) {
            return "";
        }
        long dateTime = (System.currentTimeMillis() - date.getTime()) / 1000;
        if (dateTime <= 0) {
            return "刚刚发布";
        } else if (dateTime < 60) {
            return dateTime + "秒前";
        } else if (dateTime < 3600) {
            return dateTime / 60 + "分钟前";
        } else if (dateTime < 86400) {
            return dateTime / 3600 + "小时前";
        } else {
            return toStringFromDate(date, "yyyy-MM-dd");
        }
    }

    public static String toDetailTimeString(Date date){
        Long current = System.currentTimeMillis();
        Long interval = current - date.getTime();
        Long time = interval / 1000;
        //如果创建时间大于一天则直接显示日期
        if (time > 60 * 60 * 24) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        }
        //创建时间小于一天
        StringBuilder timeDesc = new StringBuilder();
        Long temp;
        for (int i = 0; i < TIME_UNIT.length; i++) {
            temp = time % 60;
            time /= 60;
            if (temp < 60 && time == 0) {
                timeDesc.insert(0, temp + TIME_UNIT[i]);
                break;
            }
            timeDesc.insert(0, temp + TIME_UNIT[i]);
        }
        return timeDesc.toString();
    }
}
