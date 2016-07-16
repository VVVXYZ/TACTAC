package com.trio.breakFast.util;

/**
 * Created by 锋情 on 2015-04-10.
 */
public class ErrorCodeUtil {
    public static int EC_SUCCESS = 0; //成功
    public static int EC_UNKNOWN = -1; // 未知错误
    public static int EC_DATABASE = 10001;// 数据读取错误
    public static int EC_PARAMETERS_OUTOFBOUND = 10002;// 参数值不存在
    public static int EC_APPEAL_INEXISTENCE = 10003;// 诉求不存在
}
