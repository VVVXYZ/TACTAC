package com.trio.breakFast.util;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> Get the browser information.
 * <p> Created at 2014/6/8 20:35
 *
 * @author Charkey
 * @version 1.0
 */
public class BrowserInfoUtil {

    public static String getBrowserInfo(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        String browser = "";
        if (userAgent.indexOf("Maxthon") > 0) {
            if (userAgent.indexOf("AppleWebKit") > 0) {
                browser = "遨游浏览器(极速模式)";
            } else if (userAgent.indexOf("Trident") > 0) {
                browser = "遨游浏览器(兼容模式)";
            } else if (userAgent.indexOf("MAXTHON 2.0") > 0) {
                browser = "遨游浏览器2.0";
            }
        } else if (userAgent.indexOf("Firefox") > 0) {
            browser = "火狐浏览器";
        } else if (userAgent.indexOf("Opera") == 0 && userAgent.indexOf("Presto") > 0) {
            browser = "Opera";
        } else if (userAgent.indexOf("BIDUBrowser") > 0) {
            if (userAgent.indexOf("Trident") > 0) {
                browser = "百度浏览器(兼容模式)";
            } else if (userAgent.indexOf("AppleWebKit") > 0) {
                browser = "百度浏览器(极速模式)";
            }
        } else if (userAgent.indexOf("Ruibin") > 0) {
            browser = "瑞影浏览器";
        } else if (userAgent.indexOf("qihu theworld") > 0) {
            if (userAgent.indexOf("Trident") > 0) {
                browser = "世界之窗浏览器";
            } else if (userAgent.indexOf("AppleWebKit") > 0) {
                browser = "世界之窗浏览器(极速模式)";
            }
        } else if (userAgent.indexOf("MetaSr") > 0) {
            if (userAgent.indexOf("Trident") > 0) {
                browser = "搜狗高速浏览器(兼容模式)";
            } else if (userAgent.indexOf("AppleWebKit") > 0) {
                browser = "搜狗高速浏览器(极速模式)";
            }
        } else if (userAgent.indexOf("LBBROWSER") > 0) {
            if (userAgent.indexOf("Trident") > 0) {
                browser = "猎豹浏览器(兼容模式)";
            } else if (userAgent.indexOf("AppleWebKit") > 0) {
                browser = "猎豹浏览器(极速模式)";
            }
        } else if (userAgent.indexOf("YLMFBR") > 0) {
            browser = "115浏览器";
        } else if (userAgent.indexOf("QQBrowser") > 0) {
            if (userAgent.indexOf("Trident") > 0) {
                browser = "QQ浏览器(兼容模式)";
            } else if (userAgent.indexOf("AppleWebKit") > 0) {
                browser = "QQ浏览器(极速模式)";
            }
        } else if (userAgent.indexOf("TencentTraveler") > 0) {
            browser = "腾讯TT浏览器";
        } else if (userAgent.indexOf("TaoBrowser") > 0) {
            if (userAgent.indexOf("Trident") > 0) {
                browser = "淘宝浏览器(兼容模式)";
            } else if (userAgent.indexOf("AppleWebKit") > 0) {
                browser = "淘宝浏览器(极速模式)";
            }
        } else if (userAgent.indexOf("CoolNovo") > 0) {
            browser = "枫树浏览器";
        } else if (userAgent.indexOf("SaaYaa") > 0) {
            browser = "闪游浏览器";
        } else if (userAgent.indexOf("360SE") > 0) {
            browser = "360安全浏览器";
        } else if (userAgent.indexOf("360EE") > 0) {
            if (userAgent.indexOf("Trident") > 0) {
                browser = "360极速浏览器(兼容模式)";
            } else if (userAgent.indexOf("AppleWebKit") > 0) {
                browser = "360极速浏览器(极速模式)";
            }
        } else if (userAgent.indexOf("Konqueror") > 0) {
            browser = "Konqueror";
        } else if (userAgent.indexOf("Chrome") > 0) {
            browser = "谷歌浏览器";
        } else if (userAgent.indexOf("Safari") > 0) {
            browser = "Safari";
        } else if (userAgent.indexOf("MSIE") > 0) {
            String ver = userAgent.substring(userAgent.indexOf("MSIE") + 5, userAgent.indexOf("MSIE") + 9);
            browser = "IE " + ver.split(";")[0];
        } else if (userAgent.indexOf("rv") > 0 && userAgent.indexOf("Gecko") > 0) {
            String ver = userAgent.substring(userAgent.indexOf("rv") + 2, userAgent.indexOf("rv") + 7);
            browser = "IE " + ver;
        } else if (userAgent.indexOf("UCWEB") > 0) {
            browser = "UCWEB浏览器";
        } else if (userAgent.indexOf("WAP") > 0) {
            browser = "Mobile浏览器";
        } else {
            browser = userAgent;
        }
        if (browser.equals("")) {
            browser = "未知浏览器";
        }
        return browser;
    }

}
