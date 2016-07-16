package com.trio.breakFast;

public interface Constants {

//    public static final String USERNAME_PATTERN = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d_a-zA-Z][\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{1,19}$";
//    public static final String PASSWORD_PATTERN = "[\\w\\p{Punct}]{6,20}";
//    public static final String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
//    public static final String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";
//    public static final int USERNAME_MIN_LENGTH = 2;
//    public static final int USERNAME_MAX_LENGTH = 20;
//    public static final int PASSWORD_MIN_LENGTH = 5;
//    public static final int PASSWORD_MAX_LENGTH = 20;

    static final String USERNAME_PATTERN = "^[\\w]{5,20}$";
    static final int USERNAME_MIN_LENGTH = 5;
    static final int USERNAME_MAX_LENGTH = 20;

    static final String PASSWORD_PATTERN = "[\\w\\p{Punct}]{5,20}";
    static final int PASSWORD_MIN_LENGTH = 5;
    static final int PASSWORD_MAX_LENGTH = 16;

    //认证
    public static final String authenticationCacheName = "shiro-authenticationCacheName";
    //授权
    public static final String authorizationCacheName = "shiro-authorizationCacheName";

    public static final String tempRootpath = System.getProperty("user.dir") + "/temp/";
    public static final int excelPageSize = 1000;
    public static final String suffix = ".html";
    public static final String excelext = ".xls";
    public static final String exportexcel = "exportexcel";//是否是导出操作的key
    public static final String dataUpdate = "更新";
    public static final String dataSave = "保存";
    public static final String dataDelete = "删除";
    public static final String cacheKey = "simacache";
    public static final String qxCacheKey = "simaqxcache";
    public static final String tableExt = "_history_";
    public static final String frameTableAlias = "frameTableAlias";


    /**
     * 操作名称
     */
    String OP_NAME = "op";


    /**
     * 消息key
     */
    String MESSAGE = "message";

    /**
     * 错误key
     */
    String ERROR = "error";

    /**
     * 上个页面地址
     */
    String BACK_URL = "BackURL";

    String IGNORE_BACK_URL = "ignoreBackURL";

    /**
     * 当前请求的地址 带参数
     */
    String CURRENT_URL = "currentURL";

    /**
     * 当前请求的地址 不带参数
     */
    String NO_QUERYSTRING_CURRENT_URL = "noQueryStringCurrentURL";

    String CONTEXT_PATH = "ctx";

    /**
     * 当前登录的用户
     */
    String CURRENT_USER = "user";
    String CURRENT_USERNAME = "username";

    String ENCODING = "UTF-8";

    /**
     * 登录地址
     * */
    String ADMIN_LOGIN_PAGE_URL = "/admin/login";
    String CLIENT_LOGIN_PAGE_URL = "/client/login";
    String CLIENT_HOMEPAGE = "/user/index/frontindex";

    /**
     * token
     * */
    String TOKEN_NAME = "loginToken";
    int TOKEN_VALID_PERIOD = 7;

    /**
     * 用户角色权限
     */
    String MEMBER_ROLE = "MEMBER";
    String INDIVIDUAL_MEMBER_PERMISSION = "INDIVIDUAL";
    String ENTERPRISE_PERMISSION = "ENTERPRISE";

    /**
     * 身份标识前缀
     * */
    String ADMIN_PRINCIPAL_PREFIX = "管理员";
    String MEMBER_PRINCIPAL_PREFIX = "会员";
    String PRINCIPAL_INFO_SAPARATOR = ":";

    public class BAIDU_PUSH {
        /**
         * App Settings
         */
        public static final String API_KEY = "jk4F2Ch9iu9iv2Z7llBYWdpa";
        public static final String SECRET_KEY = "MG9yLineT81dXp4QoOLac59xjeaV1cOe";

        /**
         * 应用包名: 包名和AppKey必须与AndroidManifest.xml里配置的保持一致。
         */
        public static final String PACKAGE_NAME = "cn.simastudio.client.ihome";
    }
}
