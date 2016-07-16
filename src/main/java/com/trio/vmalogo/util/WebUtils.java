package com.trio.vmalogo.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author loser
 * @ClassName WebUtils
 * @Description
 * @Date 2016/04/22 1:48
 * @History :
 * <author>
 * <time>
 * <desc>
 */

public class WebUtils {
    /**
     * 判断是否是ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request){
        return request!=null && request.getHeader("X-Requested-With")!=null;
    }
}
