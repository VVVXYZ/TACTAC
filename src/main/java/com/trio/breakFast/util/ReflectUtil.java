package com.trio.breakFast.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author loser
 * @ClassName ReflectUtil
 * @Description
 * @Date 2016/02/29 15:24
 * @History :
 * <author>
 * <time>
 * <desc>
 */

public class ReflectUtil {

    /*
    * @author loser
    * @date 2016/2/29
    * @des 获得对象父类的第一个model类
    *
    * @param source 源对象
    * */
    public static Class getSuperFirstTemplateClass(Object source){
        Type baseType = source.getClass().getGenericSuperclass();
        return (Class)((ParameterizedType) baseType).getActualTypeArguments()[0];
    }

    /*
    * @author loser
    * @date 2016/2/29
    * @des 通过对象的属性名获得值
    *
    * @param source 源对象
    * @param propertyName 属性名
    * */
    public static Object getPropertyValueByPropertyName(Object source, String propertyName){
        try {
            Field field = source.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            return field.get(source);
        }catch (Exception e){
            LogUtil.logError(source.getClass().getSimpleName()+"."+propertyName+"初始化失败", e);
            return null;
        }
    }

    /*
    * @author loser
    * @date 2016/2/29
    * @des 通过实体类和对象的程序层级名获得属性值
    *
    * @param source 源对象
    * @param modelClass 实体类
    * @param levelName 程序层级名
    * */
    public static Object getPropertyValueForProgramLevelName(Object source, Class modelClass, String programLevelName){
        String modelClassName = modelClass.getSimpleName();
        String propertyName = modelClassName.substring(0, 1).toLowerCase()+modelClassName.substring(1)+programLevelName;
        return getPropertyValueByPropertyName(source, propertyName);
    }
}
