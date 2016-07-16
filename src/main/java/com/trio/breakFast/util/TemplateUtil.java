package com.trio.breakFast.util;

/**
 * @author lw
 * @ClassName ControllerUtil
 * @Description
 * @Date 2016/03/12 14:44
 * @History :
 * <author>
 * <time>
 * <desc>
 */

public class TemplateUtil {


    /*** 替换占位符
     * placeholder  占位符
     * replaceContent 替换的内容
     * templateContent   模板内容
     * **/
    public static String replace(String[] placeholder, String[] replaceContent, String templateContent) {
        for (int i = 0; i < placeholder.length; i++) {
            templateContent = templateContent.replace(placeholder[i], replaceContent[i]);
        }
        return templateContent;

    }


}
