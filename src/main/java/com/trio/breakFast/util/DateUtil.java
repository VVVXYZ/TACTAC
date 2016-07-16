package com.trio.breakFast.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author loser
 * @ClassName DateUtil
 * @Description  日期工具类
 * @Date 2016/02/26 15:27
 * @History :
 * <author>
 * <time>
 * <desc>
 */

public class DateUtil {

    public static final String YEAR = "yyyy";
    public static final String MONTH = "MM";
    public static final String DAY = "dd";
    public static final String HOUR = "HH";
    public static final String MINUTE = "mm";
    public static final String SECOND = "ss";

    //下划线
    public static final String SEPARATOR_UNDERLINE = "_";
    //中划线
    public static final String SEPARATOR_HYPHEN = "-";
    //斜杠
    public static final String SEPARATOR_OBLIQUE= "/";
    //冒号
    public static final String SEPARATOR_COLON = ":";
    //空格
    public static final String SEPARATOR_BLANK = " ";

    //默认以-作为日期分隔符  以:作为时间分隔符  日期与时间之间以空格相隔
    public static final Pattern DEFAULT_YEAR_PATTERN  = Pattern.compile("^\\d{4}$");
    public static final DateFormat DEFAULT_YEAR_FORMAT  = new SimpleDateFormat("yyyy");
    public static final String DEFAULT_YEAR_STRING  = "yyyy";

    public static final Pattern DEFAULT_MONTH_PATTERN = Pattern.compile("^\\d{4}-\\d{2}$");
    public static final DateFormat DEFAULT_MONTH_FORMAT = new SimpleDateFormat("yyyy-MM");
    public static final String DEFAULT_MONTH_STRING  = "yyyy-MM";

    public static final Pattern DEFAULT_DAY_PATTERN = Pattern.compile("^\\d{4}(-\\d{2}){2}$");
    public static final DateFormat DEFAULT_DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final String DEFAULT_DAY_STRING  = "yyyy-MM-dd";

    public static final Pattern DEFAULT_TIME_PATTERN = Pattern.compile("^\\d{2}(:\\d{2}){2}$");
    public static final DateFormat DEFAULT_TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    public static final String DEFAULT_TIME_STRING  = "HH:mm:ss";

    public static final Pattern DEFAULT_HOUR_PATTERN = Pattern.compile("^\\d{4}(-\\d{2}){2}\\s\\d{2}$");
    public static final DateFormat DEFAULT_HOUR_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH");
    public static final String DEFAULT_HOUR_STRING  = "yyyy-MM-dd HH";

    public static final Pattern DEFAULT_MINUTE_PATTERN = Pattern.compile("^\\d{4}(-\\d{2}){2}\\s\\d{2}:\\d{2}$");
    public static final DateFormat DEFAULT_MINUTE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final String DEFAULT_MINUTE_STRING = "yyyy-MM-dd HH:mm";

    public static final Pattern DEFAULT_SECOND_PATTERN = Pattern.compile("^\\d{4}(-\\d{2}){2}\\s\\d{2}(:\\d{2}){2}$");
    public static final DateFormat DEFAULT_SECOND_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String DEFAULT_SECOND_STRING  = "yyyy-MM-dd HH:mm:ss";

    public static Date string2Date(String source){

        if(DEFAULT_DAY_PATTERN.matcher(source).matches()){
            return string2Date(source, DEFAULT_DAY_FORMAT);
        }else if(DEFAULT_MONTH_PATTERN.matcher(source).matches()){
            return string2Date(source, DEFAULT_MONTH_FORMAT);
        }else if(DEFAULT_MINUTE_PATTERN.matcher(source).matches()){
            return string2Date(source, DEFAULT_MINUTE_FORMAT);
        }else if(DEFAULT_SECOND_PATTERN.matcher(source).matches()){
            return string2Date(source, DEFAULT_SECOND_FORMAT);
        }else if(DEFAULT_TIME_PATTERN.matcher(source).matches()){
            return string2Date(source, DEFAULT_TIME_FORMAT);
        }else if(DEFAULT_HOUR_PATTERN.matcher(source).matches()){
            return string2Date(source, DEFAULT_HOUR_FORMAT);
        }else if(DEFAULT_YEAR_PATTERN.matcher(source).matches()){
            return string2Date(source, DEFAULT_YEAR_FORMAT);
        }

        return null;
    }

    public static String date2String(Date source, String formatString){
        return date2String(source, new SimpleDateFormat(formatString));
    }

    public static String date2String(Date source, DateFormat format){
        try{
            return format.format(source);
        }catch (Exception e){
            return "";
        }
    }

    public static Date string2Date(String source, DateFormat format){

        Date target = null;

        try {
            target = format.parse(source);
        }catch (Exception e){
            e.printStackTrace();
        }

        return target;
    }

    public static Date string2Date(String source, String formatString){
        return string2Date(source, new SimpleDateFormat(formatString));
    }

    public static Date timestamp2Date(long source){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(source);
        return calendar.getTime();
    }

    public static String timestamp2String(long source, DateFormat format){
        return date2String(timestamp2Date(source), format);
    }

    public static String timestamp2String(long source, String formatString){
        return date2String(timestamp2Date(source), new SimpleDateFormat(formatString));
    }
}
