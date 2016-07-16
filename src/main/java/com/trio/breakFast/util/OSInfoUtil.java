package com.trio.breakFast.util;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> Get the OperateSystem information.
 * <p> Created at 2014/6/8 21:36
 *
 * @author Charkey
 * @version 1.0
 */
public class OSInfoUtil {

    public static String getOperateSystemInfo(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        String operateSystem = "";
        int windowsIndex = userAgent.indexOf("Windows NT");
        int androidIndex = userAgent.indexOf("Android");
        if (windowsIndex > 0) {
            String osVersion = userAgent.substring(windowsIndex + 11, windowsIndex + 14);
            if (osVersion.equals("5.0")) {
                operateSystem = "Windows 2000";
            } else if (osVersion.equals("5.1")) {
                operateSystem = "Windows XP";
            } else if (osVersion.equals("5.2")) {
                operateSystem = "Windows 2003";
            } else if (osVersion.equals("6.0")) {
                operateSystem = "Windows Vista/2008";
            } else if (osVersion.equals("6.1")) {
                operateSystem = "Windows 7";
            } else if (osVersion.equals("6.2")) {
                operateSystem = "Windows 8";
            } else if (osVersion.equals("6.3")) {
                operateSystem = "Windows 8.1";
            }
            if (userAgent.indexOf("WOW64") > 0) {
                operateSystem += " (X64)";
            } else {
                operateSystem += " (X86)";
            }
        } else if (userAgent.indexOf("Android") > 0) {
            operateSystem = userAgent.substring(androidIndex, androidIndex + 4);
        } else if (userAgent.indexOf("Linux") > 0) {
            if (userAgent.indexOf("i686") > 0) {
                operateSystem = "Linux X86";
            } else {
                operateSystem = "Linux";
            }
        } else if (userAgent.indexOf("Macintosh") > 0) {
            operateSystem = "Mac";
        } else if (userAgent.indexOf("IOS") > 0) {
            operateSystem = "iOS";
        } else if (userAgent.indexOf("ZTE") > 0) {
            operateSystem = "ZTE";
        } else {
            operateSystem = "未知系统";
        }
        return operateSystem;
    }
}
