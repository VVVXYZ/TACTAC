package com.asmx;

//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;
//import org.apache.axis.encoding.XMLType;
//
//import org.apache.axis.client.*;
//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;
//import javax.xml.namespace.QName;
//import javax.xml.rpc.ParameterMode;
//import javax.xml.rpc.JAXRPCException;
//import javax.xml.rpc.ServiceFactory;
//
//
//
//import javax.xml.rpc.ServiceException;
//import java.net.MalformedURLException;
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;

/**
 * @author xiaozhi
 * @ClassName: com.asmx
 * @Description:
 * @Date 2015/12/16 14:59
 * @History: <author>
 * <time>
 * <desc>
 */

public class Test {

//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        System.out.println("Start invoking....");
//        try
//        {
//            String endPoint="http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx";
//            Service service = new Service();
//            Call call = (Call)service.createCall();
//            call.setTargetEndpointAddress(new java.net.URL(endPoint));
//            call.setOperation("getVersionTime");
//            call.setUseSOAPAction(true);
//            call.setSOAPActionURI("");
//            call.setOperationName(new QName("www.webxml.com.cn","getVersionTime"));
//            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
//            String str=(String)call.invoke( new Object[]{});
//            System.out.println(str);
//        }catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//    @org.junit.Test
//    public void testCallAsmx() throws RemoteException, ServiceException,
//            MalformedURLException {
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("msg", "yjmyzz");
//
//        String result = callAsmxWebService(
//                "http://localhost:16638/asmx-service.asmx",
//                "http://yjmyzz.cnblogs.com/", "HelloWorld", params);
//
//        System.out.println(result);
//    }
//
//    String callAsmxWebService(String serviceUrl, String serviceNamespace,
//                              String methodName, Map<String, String> params)
//            throws ServiceException, RemoteException, MalformedURLException {
//
//        org.apache.axis.client.Service service = new org.apache.axis.client.Service();
//        Call call = (Call) service.createCall();
//        call.setTargetEndpointAddress(new java.net.URL(serviceUrl));
//        call.setOperationName(new QName(serviceNamespace, methodName));
//
//        ArrayList<String> paramValues = new ArrayList<String>();
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            call.addParameter(new QName(serviceNamespace, entry.getKey()),
//                    XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//            paramValues.add(entry.getValue());
//        }
//
//        call.setReturnType(XMLType.XSD_STRING);
//        call.setUseSOAPAction(true);
//        call.setSOAPActionURI(serviceNamespace + methodName);
//
//        return (String) call.invoke(new Object[] { paramValues.toArray() });
//
//    }
}
