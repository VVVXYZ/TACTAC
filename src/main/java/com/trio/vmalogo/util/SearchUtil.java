package com.trio.vmalogo.util;



import com.trio.vmalogo.pageModel.FilterRule;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by 锋情 on 2014/4/21.
 * changed by yy on 2015/9/12
 * changed by loser on 2015/12/29
 */
public class SearchUtil {
    private static Logger logger = Logger.getLogger(SearchUtil.class);
    public static StringBuilder getHql(StringBuilder stringBuilder, FilterRule filterRule, Map<String, Object> params){
        
        //判断rules 中的操作 op
        if (filterRule.getOp().equals("eq")) {
            params.put(getLastFiledName(filterRule.getField().toString()), filterRule.getData());
            stringBuilder.append(" t." + filterRule.getField() + " = " + ":" + getLastFiledName(filterRule.getField().toString()) + " and ");
        } else if (filterRule.getOp().equals("ne")) {
            params.put(getLastFiledName(filterRule.getField().toString()), filterRule.getData());
            stringBuilder.append(" t." + filterRule.getField() + " != " + ":" + getLastFiledName(filterRule.getField().toString()) + " and ");
        } else if (filterRule.getOp().equals("lt")) {

            if(filterRule.getField().endsWith("_beg") || filterRule.getField().endsWith("_end")) {
                stringBuilder.append(" t." + filterRule.getField().substring(0, filterRule.getField().length()-4) + " < " + ":" + getLastFiledName(filterRule.getField().toString()) + " and ");
            }else{
                stringBuilder.append(" t." + filterRule.getField() + " < " + ":" + getLastFiledName(filterRule.getField().toString()) + " and ");
            }
            params.put(getLastFiledName(filterRule.getField().toString()), filterRule.getData());
        } else if (filterRule.getOp().equals("le")) {
            if(filterRule.getField().endsWith("_beg") || filterRule.getField().endsWith("_end")) {
                stringBuilder.append(" t." + filterRule.getField().substring(0, filterRule.getField().length()-4) + " <= " + ":" + getLastFiledName(filterRule.getField().toString()) + " and ");
            }else{
                stringBuilder.append(" t." + filterRule.getField() + " <= " + ":" + getLastFiledName(filterRule.getField().toString()) + " and ");
            }
            params.put(getLastFiledName(filterRule.getField().toString()), filterRule.getData());
        } else if (filterRule.getOp().equals("gt")) {
            if(filterRule.getField().endsWith("_beg") || filterRule.getField().endsWith("_end")) {
                stringBuilder.append(" t." + filterRule.getField().substring(0, filterRule.getField().length()-4) + " > " + ":" + getLastFiledName(filterRule.getField().toString()) + " and ");
            }else{
                stringBuilder.append(" t." + filterRule.getField() + " > " + ":" + getLastFiledName(filterRule.getField().toString())+ " and ");
            }
            params.put(getLastFiledName(filterRule.getField().toString()), filterRule.getData());
        } else if (filterRule.getOp().equals("ge")) {
            if(filterRule.getField().endsWith("_beg") || filterRule.getField().endsWith("_end")) {
                stringBuilder.append(" t." + filterRule.getField().substring(0, filterRule.getField().length()-4) + " >= " + ":" + getLastFiledName( filterRule.getField().toString() ) + " and ");
            }else{
                stringBuilder.append(" t." + filterRule.getField() + " >= " + ":" + getLastFiledName(filterRule.getField().toString()) + " and ");
            }
            params.put(getLastFiledName(filterRule.getField().toString()), filterRule.getData());
        } else if (filterRule.getOp().equals("nu")) {
            params.put(getLastFiledName(filterRule.getField().toString()), filterRule.getData());
            stringBuilder.append(" t." + filterRule.getField() + " is null" + " and ");
        } else if (filterRule.getOp().equals("nn")) {
            params.put(getLastFiledName(filterRule.getField().toString()), filterRule.getData());
            stringBuilder.append(" t." + filterRule.getField() + " is not null" + " and ");
        } else if (filterRule.getOp().equals("in")) {
            params.put(getLastFiledName(filterRule.getField().toString()), "%" + filterRule.getData() + "%");
            stringBuilder.append(" t." + filterRule.getField() + " like " + ":" + getLastFiledName(filterRule.getField().toString()) + " and ");
        } else if (filterRule.getOp().equals("ni")) {
            params.put(getLastFiledName(filterRule.getField().toString()), "%" + filterRule.getData() + "%");
            stringBuilder.append(" t." + filterRule.getField() + " not like " + ":" + getLastFiledName(filterRule.getField().toString()) + " and ");
        } else if (filterRule.getOp().equals("cn")) {

            stringBuilder.append(" t." + filterRule.getField() + " in (" + filterRule.getData() + ")" + " and ");
        }
        else if (filterRule.getOp().equals("nc")) {
            stringBuilder.append(" t." + filterRule.getField() + " not in (" + filterRule.getData() + ")" + " and ");
        }
        return stringBuilder;
    }

    public static String getLastFiledName(String filedName){
        int begIndex = filedName.lastIndexOf(".")+1;
        return filedName.substring(begIndex,filedName.length());
    }
    
    public static Date stringToDateDay(String dateString) {
        SimpleDateFormat simpleDateFormat =   new SimpleDateFormat( "yyyy-MM" );

        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.error("条件查询中日期转换异常", e);
            System.out.println(e);
        }

        return date;
    }

    public static Date stringToDate(String dateString) {
        SimpleDateFormat simpleDateFormat =   new SimpleDateFormat( "yyyy-MM-dd" );

        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.error("条件查询中日期转换异常", e);
        }

        return date;
    }

    public static Date stringToDateMinute(String dateString) {
        SimpleDateFormat simpleDateFormat =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );

        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.error("条件查询中日期转换异常",e);
        }

        return date;
    }
}
