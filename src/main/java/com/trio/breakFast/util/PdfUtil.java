package com.trio.breakFast.util;


import com.google.common.base.Strings;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.trio.breakFast.enums.LogoStyle;
import com.trio.breakFast.model.Questionnaire;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by lzw on 2015/8/19.
 */
public class PdfUtil {
    static public final String QUESTIONNAIRE_KEY = "questionnaireKey";
    static public final String QUESTIONNAIRE_FILE_NAME = "questionnaireFileName";
    static public final String QUESTIONNAIRE_PICTURE_PATH = "questionnairePicturePath";
    private static Logger logger = Logger.getLogger(PdfUtil.class);

    public static void createPDF(Questionnaire questionnaire, String path, Document document) throws Exception{
        document.open();
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

        // step 4:
        Font font = new Font(baseFont,15,Font.BOLD);//这个是中文大字体,设置大小只要该第二个参数，数字越大，字体越大
        //还有第三个参数，用来设置粗体 Font.BOLD
        Font font1 = new Font(baseFont,12);
        //---->注意，想要输出中文，在创建paragraph，chunk,phrase 要在后面加上实现设置好的中文字体
        Paragraph p1=new Paragraph("微码Logo设计需求提交",font); //这个是pdf标题，后期要修改
        p1.setAlignment(Element.ALIGN_CENTER); //这个参数是段落居中
        document.add(p1);
        //--->说明：一个paragraph 可以包含chunk,phrase;-->用add()来添加，用来段落排版
        Paragraph p2=new Paragraph("初稿提交时间:LOGO设计时间是2-4个工作日（工作日是不包含周末的）",font1);
        p2.setAlignment(Element.ALIGN_CENTER); //这个参数是段落居中
        document.add(p2);
        Paragraph p3=new Paragraph("【注意事项】\n" +
                "1.供2套初稿方案\n" +
                "2.选择其中一套初稿，修改到满意为止，确认好的意见不得无故推翻\n" +
                "初稿阶段：雇主对初稿不满意，单方交易终止。我司承诺退款70%\n" +
                "修稿阶段：二稿不满意可退款50%，二稿继续修改后可退款30%\n" +
                "定稿阶段：定稿并交接设计源文件后不予退款\n" +
                "3.以上退款协议均为不进行文档索要的情况下适用\n" +
                "另任何时候客户出现不满意情况且未提出退款申请之前，我司均承诺修稿到满意为止。如需总监亲自操刀设计，需额外加收费用800元。",font1);
        p3.setAlignment(Element.ALIGN_LEFT);
        document.add(p3);
        Paragraph p4=new Paragraph(" 1、标志(logo)中文名称:     "+questionnaire.getChinese()+"        标志(logo)英文名称:  "+questionnaire.getEnglish()+"",font1);
        document.add(p4);
        document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
        document.add(new Paragraph(" 2、公司（或品牌）经营范围和详细内容:     "+questionnaire.getMainBusiness()+"                       ",font1));
        document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
        document.add( new Paragraph("3、您希望设计的标志(logo)由：    "+questionnaire.getLogoComponent().getChoice()+"   （这处只可单选，有疑问联系客服）     ",font1));
        Image jpg3 = Image.getInstance(path+ File.separator+"vmajpg"+File.separator+"3.png");
        jpg3.scaleToFit(500,400);
        jpg3.setAlignment(Element.ALIGN_LEFT);
        document.add(jpg3);
        document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
        document.add( new Paragraph(" 4、您更倾向于哪种标志(logo)风格：   "+getStyle(questionnaire.getLogoStyles())+"   \n" +
                "            A.专业稳重   B.时尚动感   C.热情亲和   D.民族特色   E.国际化风格   F.清新自然   G.传统老字号",font1));
        document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));

        document.add( new Paragraph("5、标志色彩范围选定：  "+questionnaire.getLogoColor().getChoice()+"    ",font1));
        Image jpg5 = Image.getInstance(path+ File.separator+"vmajpg"+File.separator+"5.png");
        jpg5.scaleToFit(500,400);
        jpg5.setAlignment(Element.ALIGN_LEFT);
        document.add(jpg5);
        document.add(new Paragraph("禁忌色【此处请填入您禁忌的颜色 如：绿色】__  "+questionnaire.getColourForbidDetail()+"   end",font1));
        document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
        document.add( new Paragraph("6.标志创作出发点： ___   "+questionnaire.getLogoStartpoint().getChoice()+"  ______",font1));

        Image jpg6 = Image.getInstance(path+ File.separator+"vmajpg"+File.separator+"6.png");
        jpg6.scaleToFit(500,400);
        jpg6.setAlignment(Element.ALIGN_LEFT);
        document.add(jpg6);

        document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
        document.add( new Paragraph("7、您的联系QQ：  "+questionnaire.getQq()+"         您的联系电话：   "+questionnaire.getCellphone()+"  __",font1));
        document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
        document.add(new Paragraph("8、请提供您喜欢的LOGO样式，或者同行业中您欣赏的LOGO案例，有利于设计师准确定位（必填项,可截图）：\n" +
                "有其他要求填写于此",font1));
        document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
        getImg(document,questionnaire.getAttachmentUrls());

        document.add(new Paragraph("注：为我们合作的更加愉快，设计前您如有特殊设计要求（比如融入指定图形，规定指定颜色等等），您一定要在表格中反映出来，一旦设计方案出来后，我们将不接受附加要求。"));
        document.close();
    }


    public static ByteArrayInputStream creatQuePDF(Questionnaire questionnaire,String path){

        // step 1: creation of a document-object
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String relativelyPath=System.getProperty("user.dir");
        logger.debug("path="+path);
        logger.debug("relativelyPath="+relativelyPath);

        try {
//
            PdfWriter writer = PdfWriter.getInstance(document, out);
            writer.setStrictImageSequence(true);
            // step 3: we open the document
            document.open();

            BaseFont baseFont = BaseFont.createFont(path+ File.separator+"SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);


            // step 4:
            Font font = new Font(baseFont,15,Font.BOLD);//这个是中文大字体,设置大小只要该第二个参数，数字越大，字体越大
            //还有第三个参数，用来设置粗体 Font.BOLD
            Font font1 = new Font(baseFont,12);
            //---->注意，想要输出中文，在创建paragraph，chunk,phrase 要在后面加上实现设置好的中文字体
            Paragraph p1=new Paragraph("微码Logo设计需求提交",font); //这个是pdf标题，后期要修改
            p1.setAlignment(Element.ALIGN_CENTER); //这个参数是段落居中
            document.add(p1);
            //--->说明：一个paragraph 可以包含chunk,phrase;-->用add()来添加，用来段落排版
            Paragraph p2=new Paragraph("初稿提交时间:LOGO设计时间是2-4个工作日（工作日是不包含周末的）",font1);
            p2.setAlignment(Element.ALIGN_CENTER); //这个参数是段落居中
            document.add(p2);
            Paragraph p3=new Paragraph("【注意事项】\n" +
                    "1.供2套初稿方案\n" +
                    "2.选择其中一套初稿，修改到满意为止，确认好的意见不得无故推翻\n" +
                    "初稿阶段：雇主对初稿不满意，单方交易终止。我司承诺退款70%\n" +
                    "修稿阶段：二稿不满意可退款50%，二稿继续修改后可退款30%\n" +
                    "定稿阶段：定稿并交接设计源文件后不予退款\n" +
                    "3.以上退款协议均为不进行文档索要的情况下适用\n" +
                    "另任何时候客户出现不满意情况且未提出退款申请之前，我司均承诺修稿到满意为止。如需总监亲自操刀设计，需额外加收费用800元。",font1);
            p3.setAlignment(Element.ALIGN_LEFT);
            document.add(p3);
            Paragraph p4=new Paragraph(" 1、标志(logo)中文名称:     "+questionnaire.getChinese()+"        标志(logo)英文名称:  "+questionnaire.getEnglish()+"",font1);
            document.add(p4);
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph(" 2、公司（或品牌）经营范围和详细内容:     "+questionnaire.getMainBusiness()+"                       ",font1));
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add( new Paragraph("3、您希望设计的标志(logo)由：    "+questionnaire.getLogoComponent().getChoice()+"   （这处只可单选，有疑问联系客服）     ",font1));
            Image jpg3 = Image.getInstance(path+ File.separator+"vmajpg"+File.separator+"3.png");
            jpg3.scaleToFit(500,400);
            jpg3.setAlignment(Element.ALIGN_LEFT);
            document.add(jpg3);
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add( new Paragraph(" 4、您更倾向于哪种标志(logo)风格：   "+getStyle(questionnaire.getLogoStyles())+"   \n" +
                    "            A.专业稳重   B.时尚动感   C.热情亲和   D.民族特色   E.国际化风格   F.清新自然   G.传统老字号",font1));
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));

            document.add( new Paragraph("5、标志色彩范围选定：  "+questionnaire.getLogoColor().getChoice()+"    ",font1));
            Image jpg5 = Image.getInstance(path+ File.separator+"vmajpg"+File.separator+"5.png");
            jpg5.scaleToFit(500,400);
            jpg5.setAlignment(Element.ALIGN_LEFT);
            document.add(jpg5);
            document.add(new Paragraph("禁忌色【此处请填入您禁忌的颜色 如：绿色】__  "+questionnaire.getColourForbidDetail()+"   end",font1));
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add( new Paragraph("6.标志创作出发点： ___   "+questionnaire.getLogoStartpoint().getChoice()+"  ______",font1));

            Image jpg6 = Image.getInstance(path+ File.separator+"vmajpg"+File.separator+"6.png");
            jpg6.scaleToFit(500,400);
            jpg6.setAlignment(Element.ALIGN_LEFT);
            document.add(jpg6);

            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add( new Paragraph("7、您的联系QQ：  "+questionnaire.getQq()+"         您的联系电话：   "+questionnaire.getCellphone()+"  __",font1));
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph("8、请提供您喜欢的LOGO样式，或者同行业中您欣赏的LOGO案例，有利于设计师准确定位（必填项,可截图）：\n" +
                    "有其他要求填写于此",font1));
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            getImg(document,questionnaire.getAttachmentUrls());

            document.add(new Paragraph("注：为我们合作的更加愉快，设计前您如有特殊设计要求（比如融入指定图形，规定指定颜色等等），您一定要在表格中反映出来，一旦设计方案出来后，我们将不接受附加要求。"));
            document.close();
            out.close();
        }
        catch(Exception de) {
            logger.debug("Exception",de);

        }

        return new ByteArrayInputStream(out.toByteArray());

    }


    public static String getStyle(String input){
        String result ="";
       for(LogoStyle logoStyle: LogoStyle.valueOfArray(input)){
           result +=logoStyle.getChoice()+",";
       }
        result = result.substring(0,result.lastIndexOf(","));
        return result;
    }

    public static void getImg(Document document,String input) throws Exception{
        if(Strings.isNullOrEmpty(input))
            return ;
        for (String url :input.split(",")){
            Image jpg = Image.getInstance(url);
            jpg.scaleToFit(150,150);
            jpg.setAlignment(Element.AUTHOR);
            document.add(jpg);
        }
    }
}
