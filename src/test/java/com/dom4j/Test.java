package com.dom4j;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaozhi
 * @ClassName: com.dom4j
 * @Description:
 * @Date 2015/12/16 11:25
 * @History: <author>
 * <time>
 * <desc>
 */

public class Test {
    private static final Logger logger = Logger.getLogger(Test.class);

    public String formatXMLParameter(boolean setNewlines,boolean setIndent,
                                     Map<String,String> element,Map<String,String> cDATAElement ){

        //设置文件编码
        OutputFormat xmlFormat = new OutputFormat();
        xmlFormat.setEncoding("UTF-8");
//        xmlFormat.setEncoding(encoding);
        // 设置换行
        xmlFormat.setNewlines(setNewlines);
        // 生成缩进
        xmlFormat.setIndent(setIndent);
        // 使用4个空格进行缩进, 可以兼容文本编辑器
//        xmlFormat.setIndent("    ");

        //配置xml
        Document document = DocumentHelper.createDocument();
        //创建root
        Element root = document.addElement("pReq");
        if(cDATAElement != null){
            for(String key :cDATAElement.keySet()){
                root.addElement(key).addCDATA(cDATAElement.get(key));
//                dATAElement.addCDATA(cDATAElement.get(key));
            }
        }
        if(element != null) {
            for (String key : element.keySet()) {
                root.addElement(key).addText(element.get(key));
            }
        }
        //创建字符串缓冲区
        StringWriter stringWriter = new StringWriter();
        try{
            //创建写文件方法
            XMLWriter xmlWriter = new XMLWriter(stringWriter,xmlFormat);
            //写入文件
            xmlWriter.write(document);
            //关闭
            xmlWriter.close();
            String res = stringWriter.toString();
            logger.debug(res);
            return res;
        }catch (IOException e){
            logger.error("IOException",e);
            return null;
        }

    }

    @org.junit.Test
    public void testformatXMLParameter() throws Exception {
        Map element = new HashMap();
        Map cDATAElement = new HashMap();
        element.put("pMerBillNo","string");
        element.put("pRegDate","string");
        element.put("pLendAmt","string");
        element.put("pTrdLendRate","string");
        cDATAElement.put("pLendPurpose","pLendPurpose");
        cDATAElement.put("pRealName","pRealName");
        cDATAElement.put("pWebUrl","string");
       String str = formatXMLParameter(false,false,element,cDATAElement);
//        System.out.println("testformatXMLParameter  ="+str);
//        logger.debug("testformatXMLParameter /n ="+str);
    }

    @org.junit.Test
    public void testName() throws Exception {
        Document document = DocumentHelper.createDocument();
        //创建root
        Element root = document.addElement("parameters");
        //生成root的一个接点
        Element param = root.addElement("parameter");
        // 为节点添加属性
        param.addAttribute("key", "sys.username");
        // 为节点添加文本, 也可以用addText()
        param.addCDATA("中国");
        root.addElement("pMerBillNo").addText("text");



        //创建字符串缓冲区
        StringWriter stringWriter = new StringWriter();
        //设置文件编码
        OutputFormat xmlFormat = new OutputFormat();
        // 设置换行
        xmlFormat.setNewlines(false);
        // 生成缩进
        xmlFormat.setIndent(false);
        xmlFormat.setEncoding("UTF-8");

        // 使用4个空格进行缩进, 可以兼容文本编辑器
//        xmlFormat.setIndent("    ");
//        Writer fileWriter = new StringWriter();
        //创建写文件方法
        XMLWriter xmlWriter = new XMLWriter(stringWriter,xmlFormat);
        //写入文件
        xmlWriter.write(document);
        //关闭
        xmlWriter.close();
        // 输出xml
        System.out.println(stringWriter.toString());
        logger.debug(stringWriter.toString());
    }
}
