package com.trio.breakFast.util;/**
 * Created by weimao on 2016/5/22.
 */


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 返回一个pdf输入流
 */
public class PdfProduce {

    static public final String TEMPLATE_CONTENT_KEY = "templateContent";
    static public final String PLACEHOLDER_MAP_KEY = "placeholderMap";

    public static void createAgreement(String modelContent,Map<String, String> params,Document document)throws DocumentException, IOException{

        for(String key : params.keySet()){
            modelContent = modelContent.replace(key, params.get(key));
        }
        String stringS  = "\\{.*?\\}";
        Pattern patternS = Pattern.compile(stringS);
        Matcher matcherS = patternS.matcher(modelContent);
        while(matcherS.find()){
            modelContent = modelContent.replace(matcherS.group(),"");
        }
//        Document document = new Document(PageSize.LETTER);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        PdfWriter.getInstance(document, out);
        document.open();
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont,19);//这个是中文大字体,设置大小只要该第二个参数，数字越大，字体越大
        Font font2 = new Font(baseFont,12);

        String string  = "(?i)<p[^>]*?>[^<>]*?</p>";
        Pattern pattern = Pattern.compile(string);
        Matcher matcher = pattern.matcher(modelContent);
        int i=0;
        while(matcher.find()){
            String s = matcher.group();
            s = s.substring(3,s.length()-4);
            Paragraph p=new Paragraph(s,font2); //font2字体大小
            p.setAlignment(Element.ALIGN_LEFT);//这个参数是段落左对齐
            if (i==0)
            {
                p.setAlignment(Element.ALIGN_RIGHT); //这个参数是段落右对齐
            }
            if (i==2)
            {
                p = new Paragraph(s,font); //font字体大小
                p.setAlignment(Element.ALIGN_CENTER);//这个参数是段落居中
            }
            i++;
            document.add(p);
        }
        document.close();
    }
}
