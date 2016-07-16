package com.trio.breakFast.util.ips;

/**
 * @author //作者 fjfzuhqc
 * @ClassName: //类名/接口名/表名
 * @Description: // 环迅接口信息、配置
 * @Date    //创建/生成日期 2016/03/08 21:54
 * @History:// 历史修改记录
 * <author>  // 修改人1
 * <time>    // 修改时间
 * <desc>    // 描述修改内容
 * <author>  // 修改人2
 * <time>    // 修改时间
 * <desc>    // 描述修改内容
 */
public class IpsConfig {
    //环迅各种接口url，后缀_TEST的为测试环境下使用，其他的为正式环境下使用
    public static final String WS_URL_TEST = "http://p2p.ips.net.cn:8013/CreditWS/Service.asmx";
    public static final String WS_URL = "http://p2p.ips.com.cn/CreditWS/Service.asmx";
    public static final String POST_URL_TEST = "http://p2p.ips.net.cn:8011/CreditWeb/";
    public static final String POST_URL = "http://p2p.ips.com.cn/CreditWeb/";
    public static final String QUERY_WS_URL_TEST = "http://p2p.ips.net.cn:8013/CreditWSQuery/Service.asmx";
    public static final String QUERY_WS_URL = "http://p2p.ips.com.cn/CreditWSQuery/Service.asmx";
    public static final String BONU_WS_URL_TEST = "http://p2p.ips.net.cn:8013/CreditWS/ServiceBonu.asmx";
    public static final String BONU_WS_URL_ = "http://p2p.ips.com.cn/CreditWS/ServiceBonu.asmx";
    public static final String DEBTSTRANSFE_URL_TEST = "http://p2p.ips.net.cn:8013/CreditWS/Service.asmx";
    public static final String DEBTSTRANSFER_URL = "http://p2p.ips.com.cn/CreditWS/Service.asmx";


    public static final String SERVICE_NAMESPACE_TEST = "http://tempuri.org/";  //WebService命名空间,测试环境
    public static final String SERVICE_NAMESPACE = "http://microsoft.com/webservices/";  //WebService命名空间


    public static final String pWebUrl = "http://breakFast.lczyfz.com/p2pDemo2/test";//web方式返回
    public static final String pS2SUrl = "http://breakFast.lczyfz.com/p2pDemo2/wstest";//s2s方式返回


    /**
     * 环迅接口返回的浏览器地址常量
     */
    public static final String OpenAccountUrl = "http://breakFast.lczyfz.com/breakFast/callback/CreateNewIpsAcct/handleHxAccountInfo";//开户信息返回url
    public static final String hxRechargeUrl = "http://breakFast.lczyfz.com/breakFast/callback/RechargeAndWithdraw/handleHxRechargeInfo";//环迅充值返回url
    public static final String hxWithDrawUrl = "http://breakFast.lczyfz.com/breakFast/callback/RechargeAndWithdraw/handleHxWithdrawInfo";//环迅提现返回url
    public static final String hxManualRepaymentUrl = "http://breakFast.lczyfz.com/breakFast/callback/Bid/Repayment/handleHxRepayManuallyInfo";//环迅还款同步返回url
    public static final String hxManualRepaymentUrlAsyn = "http://breakFast.lczyfz.com/breakFast/callback/Bid/Repayment/handleHxRepayManuallyInfoAsyn";//环迅还款异步返回url
    public static final String registerSubjectUrl = "http://breakFast.lczyfz.com/breakFast/callback/Bid/RegistrationAndFailure/handleHxBidRegistrationInfo";//登记标返回url
    public static final String failureBidUrl = "http://breakFast.lczyfz.com/breakFast/callback/Bid/RegistrationAndFailure/handleHxBidFailureInfo";//流标返回url
    public static final String fullBidLoanUrl = "http://breakFast.lczyfz.com/breakFast/callback/Bid/Investment/handleHxTransferAfterBidFinishInfo";//满标放款url
    public static final String investLoanUrl = "http://breakFast.lczyfz.com/breakFast/callback/Bid/Investment/handleHxBidManuallyInfo";//投标url
    public static final String hxDebtsTransfeUrl = "http://breakFast.lczyfz.com/breakFast/callback/Bid/CreditAssignment/handleHxCreditAsgnRegInfo";//债权转让url
    public static final String hxDebtsFundTransfeUrl = "http://breakFast.lczyfz.com/breakFast/callback/Bid/CreditAssignment/handleHxFundsTransferInfo";//债权转让资金转让url


    /* 商户平台信息 */
    //平台商户号：808801
    public static final String CERT_IPS_ACC = "808801";
    //IPS 证书
    public static final String CERT_MD5 = "GPhKt7sh4dxQQZZkINGFtefRKNPyAj8S00cgAwtRyy0ufD7alNC28xCBKpa6IU7u54zzWSAv4PqUDKMgpOnM7fucO1wuwMi4RgPAnietmqYIhHXZ3TqTGKNzkxA55qYH";
    //3des 密钥
    public static final String DES_KEY = "ICHuQplJ0YR9l7XeVNKi6FMn";
    // 3des 向量
    public static final String DES_IV = "2EDxsEfp";
}
