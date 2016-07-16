package com.trio.vmalogo.util.ips;
/* callAsmxWebService方法依赖包，必选 */

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/* 以下是main方法中测试所需依赖，可选 */


/**
 * @ClassName: AsmxUtil

 * @Description: 调用环迅WebService的工具类
 * @author //作者 xiaozhi
 * @Date	//创建/生成日期 2016/03/15 14:45
 * @History:// 历史修改记录
 *	<author>  // 修改人1
 *   <time>    // 修改时间
 *   <desc>    // 描述修改内容
 *  <author>  // 修改人2
 *   <time>    // 修改时间
 *   <desc>    // 描述修改内容
 **/
public class AsmxUtil {

    /* 用于测试 */
    public static void main(String[] args) {
        /***
         //         * 拼接加密参数
         //         *
         //         * ***/
        Map<String, String> params = new HashMap<String, String>();
        params.put("bankProId", "1100");
        String serviceUrl = "http://120.26.136.97:8887/WebService2.asmx";
        String serviceNamespace = IpsConfig.SERVICE_NAMESPACE;
        String methodName = "GetBankCity";
        String result = "";
        /**
         * 调用AsmxWebService
         * */
        try {
            result = callAsmxWebService(serviceUrl, serviceNamespace, methodName, params);
        } catch (Exception e) {
            logger.debug("Exception", e);
        }
        logger.debug("result="+result);
//       String  strxml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
//                + "<pReq>"+
//                "<pMerBillNo>"+pMerBillNo+"</pMerBillNo>"+
//                "<pSigningDate>" + pSigningDate + "</pSigningDate>" +
//                "<pIdentNo>" + pIdentNo + "</pIdentNo>" +
//                "<pRealName>" + pRealName + "</pRealName>" +
//                "<pIpsAcctNo>" + pIpsAcctNo + "</pIpsAcctNo>" +
//                "<pValidType>" + pValidType + "</pValidType>" +
//                "<pValidDate>" + pValidDate + "</pValidDate>" +
//                "<pTrdCycleType>" + pTrdCycleType + "</pTrdCycleType>" +
//                "<pSTrdCycleValue><![CDATA[" + pSTrdCycleValue + "]]></pSTrdCycleValue>" +
//                "<pETrdCycleValue><![CDATA[" + pETrdCycleValue + "]]></pETrdCycleValue>" +
//                "<pSAmtQuota><![CDATA[" + pSAmtQuota + "]]></pSAmtQuota>" +
//                "<pEAmtQuota><![CDATA[" + pEAmtQuota + "]]></pEAmtQuota>" +
//                "<pSIRQuota><![CDATA[" + pSIRQuota + "]]></pSIRQuota>" +
//                "<pEIRQuota><![CDATA[" + pEIRQuota + "]]></pEIRQuota>" +
//                "<pWebUrl><![CDATA[" + pWebUrl + "]]></pWebUrl>" +
//                "<pS2SUrl><![CDATA[" + pS2SUrl + "]]></pS2SUrl>" +
//                "<pMemo1>" + pMemo1 + "</pMemo1>" +
//                "<pMemo2>" + pMemo2 + "</pMemo2>" +
//                "<pMemo3>" + pMemo3 + "</pMemo3>" +
//                "</pReq>";
//        String  str3DesXmlPana = strxml ;
//        logger.debug("DesXmlPana = " + str3DesXmlPana);


//        str3DesXmlPana = com.ips.security.utility.IpsCrypto.triDesEncrypt(strxml, IpsConfig.DES_KEY, IpsConfig.DES_IV);
        //str3DesXmlPana = com.ips.commons.security.DES.encrypt3DES(strxml,Config.DES_KEY,Config.DES_IV);
//        str3DesXmlPana = str3DesXmlPana.replaceAll("\r\n", "");


//        /***
//         * 拼接加密参数
//         *
//         * ***/
//        String argMerCode = IpsConfig.CERT_IPS_ACC;
//        String argSign = com.ips.security.utility.IpsCrypto.md5Sign(argMerCode + IpsConfig.CERT_MD5);
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("argMerCode", argMerCode);
//        params.put("argSign", argSign);
//        String serviceUrl = IpsConfig.WS_URL_TEST;
//        String serviceNamespace = IpsConfig.SERVICE_NAMESPACE;
//        String methodName = "GetBankList";
//        String result = "";
//        /**
//         * 调用AsmxWebService
//         * */
//        try {
//            result = callAsmxWebService(serviceUrl, serviceNamespace, methodName, params);
//        } catch (Exception e) {
//            logger.debug("Exception", e);
//        }
//        /**
//         * 验证信息  1,MD5验签通过！2 解密  3 解析数据写入数据库
//         * */
//        if (result != "") {
//            XmlTool xmlTool = new XmlTool();
//            xmlTool.SetDocument(result);
//
//            String pMerCode = xmlTool.getNodeValue("pMerCode");
//            String pErrCode = xmlTool.getNodeValue("pErrCode");
//            String pErrMsg = xmlTool.getNodeValue("pErrMsg");
//            String pBankList = xmlTool.getNodeValue("pBankList");
//            String pSign = xmlTool.getNodeValue("pSign");
//            String signPlainText = "<pMerCode>" + pMerCode + "</pMerCode>" + "<pErrCode>" + pErrCode + "</pErrCode>"
//                    + "<pErrMsg>" + pErrMsg + "</pErrMsg>" + "<pBankList>" + pBankList + "</pBankList>"
//                    + IpsConfig.CERT_MD5;
//            String localSign = com.ips.security.utility.IpsCrypto.md5Sign(signPlainText);
//
//            if (localSign.equals(pSign)) {
//                logger.debug("localSign="+localSign);
//                logger.debug("pSign="+pSign);
//                logger.debug("MD5验签通过！");
//                String[] bankarry = pBankList.split("#");//银行名称|银行卡别名|银行卡编号#银行名称|银行卡别名|银行卡编号#
//                //中国工商银行|网上签约注册用户(全国范围)|00004#中国工商银行|工商银行|02009#中国银行|银行卡支付(全国范围)|00083#
//
//                logger.debug("bankarry= "+bankarry);
//                for(String bankStr :bankarry){
//                    String[] bank = bankStr.split("\\|");
//                    logger.debug("bank = ！"+bank);
//                    SmBankInfo smBankInfo = new SmBankInfo();
//                    smBankInfo.setBankName(bank[0]);
//                    smBankInfo.setBankNickName(bank[1]);
//                    smBankInfo.setBankNo(bank[2]);
//                    logger.debug("smBankInfo"+ smBankInfo);
//                }
//
//            }
//
//        }
//        String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
//                "<pReq>" +
//                "<pDetails>" +
//                 "<pRow>" +
//                  "<pOriMerBillNo>string</pOriMerBillNo>" +
//                  "<pTrdAmt>string</pTrdAmt>" +
//                 "</pRow>" +
//                "</pDetails>" +
//                "</pReq>";
//        XmlTool xmlTool = new XmlTool();
//        xmlTool.SetDocument(str);
//        List<Map<String, String>> pDetailRows = xmlTool.getMapList("pRow");
//        String pDetailsStr = xmlTool.getNodeValue("pDetails");
    }






    static Logger logger = Logger.getLogger(AsmxUtil.class);

    /**
     * 〈callAsmxWebService方法的功能描述〉：调用环迅提供的asmx服务
     *
     * @Date	//创建/生成日期 2016/03/15 14:45
     * @author //作者 limer2
     * @methodName [方法名] callAsmxWebService
     * @param  //参数  serviceUrl [String,服务url], serviceNamespace [String,服务命名空间], methodName [String,要调用的服务方法名], params [Map<String,String>,请求参数]
     * @return [String] 失败返回null
     * @throws [Exception]
     * @History:// 历史修改记录
     *	<author>  // 修改人1
     *   <time>    // 修改时间
     *   <desc>    // 描述修改内容
     **/
    public static String callAsmxWebService(String serviceUrl, String serviceNamespace, String methodName, Map<String, String> params) throws Exception {
        logger.debug("serviceUrl="+serviceUrl);
        logger.debug("serviceNamespace="+serviceNamespace);
        logger.debug("methodName="+methodName);
        logger.debug("params="+params);

        Service service = new Service();
        Call call;
        try {
            call = (Call) service.createCall();
        } catch (ServiceException sex) {
            logger.debug("org.apache.axis.client.Call实例创建失败："+sex.getMessage());
            throw new Exception(sex);
        }
        try {
            call.setTargetEndpointAddress(new java.net.URL(serviceUrl));
        } catch (MalformedURLException muex) {
            logger.debug("serviceUrl不正确："+muex.getMessage());
            throw new Exception(muex);
        }
        call.setOperationName(new QName(serviceNamespace, methodName));

        // 设置参数名称，具体参照从浏览器中看到的
        ArrayList<String> paraValueList  = new ArrayList<String>();
        String[] paraValueArray;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            call.addParameter(new QName(serviceNamespace, entry.getKey()), XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            paraValueList.add(entry.getValue());
        }
        paraValueArray = paraValueList.toArray(new String[paraValueList.size()]);
        logger.debug("paraValueArray="+paraValueArray.toString());

        // 要返回的数据类型
        call.setReturnType(XMLType.XSD_STRING);

        call.setUseSOAPAction(true);
        call.setSOAPActionURI(serviceNamespace + methodName);

        String ipsResult = null;
        try {
            ipsResult = (String) call.invoke(paraValueArray);
        } catch (RemoteException rex) {
            logger.debug("服务调用失败："+rex.getMessage());
            throw new Exception(rex);
        }
        logger.debug("ipsResult="+ipsResult);

        return ipsResult;
    }
}
