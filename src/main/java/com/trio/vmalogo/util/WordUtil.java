package com.trio.vmalogo.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;

/**
 * Created by xiaozhi on 2016/7/3.
 */
public class WordUtil {
//    private Configuration configuration = null;
//    Map<String, Object> dataMap = new HashMap<String, Object>();
//    public WordUtil() {
//        configuration = new Configuration();
//        configuration.setDefaultEncoding("utf-8");
//    }

    public static void main(String[] args){
        String temp = "temp";
        System.out.println("ImageSequence");
        // step 1: creation of a document-object
        Document document = new Document();
        FileOutputStream out=null;

        try {
            if(System.getProperties().getProperty("os.name").startsWith("Windows"))
            {
                out=new FileOutputStream("D:\\logs\\inSequence.pdf");
            }else {
                out=new FileOutputStream("D:\\logs\\inSequence.pdf");
            }
            PdfWriter writer = PdfWriter.getInstance(document, out);
            writer.setStrictImageSequence(true);
            // step 3: we open the document
            document.open();
            BaseFont baseFont;
            String relativelyPath=System.getProperty("user.dir");
            if (System.getProperties().getProperty("os.name").startsWith("Windows"))
            {
                baseFont = BaseFont.createFont(relativelyPath+"\\src\\main\\resources"+"\\SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            } else {
                baseFont = BaseFont.createFont(relativelyPath+"\\src\\main\\resources"+"\\SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            }
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
                    "1.提供2套初稿方案\n" +
                    "2.选择其中一套初稿，修改到满意为止，确认好的意见不得无故推翻\n" +
                    "初稿阶段：雇主对初稿不满意，单方交易终止。我司承诺退款70%\n" +
                    "修稿阶段：二稿不满意可退款50%，二稿继续修改后可退款30%\n" +
                    "定稿阶段：定稿并交接设计源文件后不予退款\n" +
                    "3.以上退款协议均为不进行文档索要的情况下适用\n" +
                    "另任何时候客户出现不满意情况且未提出退款申请之前，我司均承诺修稿到满意为止。如需总监亲自操刀设计，需额外加收费用800元。",font1);
            p3.setAlignment(Element.ALIGN_LEFT);
            document.add(p3);
            Paragraph p4=new Paragraph(" 1、标志(logo)中文名称:     "+temp+"        标志(logo)英文名称:  "+temp+"",font1);
            document.add(p4);
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph(" 2、公司（或品牌）经营范围和详细内容:     "+temp+"                       ",font1));
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add( new Paragraph("3、您希望设计的标志(logo)由：    "+temp+"   （这处只可单选，有疑问联系客服）     ",font1));
            Image jpg3 = Image.getInstance(relativelyPath+"\\src\\main\\resources"+"\\vmajpg\\3.png");
            jpg3.scaleToFit(500,400);
            jpg3.setAlignment(Element.ALIGN_LEFT);
            document.add(jpg3);
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add( new Paragraph(" 4、您更倾向于哪种标志(logo)风格：  您更倾向于哪种标志(logo)风格   "+temp+"   \n" +
                    "            A.专业稳重   B.时尚动感   C.热情亲和   D.民族特色   E.国际化风格   F.清新自然   G.传统老字号",font1));
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));

            document.add( new Paragraph("5、标志色彩范围选定：  "+temp+"      禁忌色【此处请填入您禁忌的颜色 如：绿色】__  "+temp+"   ",font1));
            Image jpg5 = Image.getInstance(relativelyPath+"\\src\\main\\resources"+"\\vmajpg\\5.png");
            jpg5.scaleToFit(500,400);
            jpg5.setAlignment(Element.ALIGN_LEFT);
            document.add(jpg5);
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add( new Paragraph("6.标志创作出发点： ___   "+temp+"  ______",font1));

            Image jpg6 = Image.getInstance(relativelyPath+"\\src\\main\\resources"+"\\vmajpg\\6.png");
            jpg6.scaleToFit(500,400);
            jpg6.setAlignment(Element.ALIGN_LEFT);
            document.add(jpg6);
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add( new Paragraph("7、您的联系QQ：  "+temp+"         您的联系电话：   "+temp+"  __",font1));
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph("8、请提供您喜欢的LOGO样式，或者同行业中您欣赏的LOGO案例，有利于设计师准确定位（必填项,可截图）：\n" +
                    "有其他要求填写于此",font1));
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            for(int i=0;i<5;i++){
                Image jpg = Image.getInstance("http://b.hiphotos.baidu.com/image/pic/item/b3119313b07eca8074895e21952397dda0448363.jpg");
                jpg.scaleToFit(150,150);
                jpg.setAlignment(Element.AUTHOR);
                document.add(jpg);


            }

            document.add(new Paragraph("注：为我们合作的更加愉快，设计前您如有特殊设计要求（比如融入指定图形，规定指定颜色等等），您一定要在表格中反映出来，一旦设计方案出来后，我们将不接受附加要求。"));
            document.close();
            out.close();
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        // step 5: we close the document

    }
//    public static void main(String[] args){
//
//        WordUtil wordUtil = new WordUtil();
//        try {
//            wordUtil.creatWord();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }
//
//    }

//    private String getImageStr() {
//        String imgFile = "D:\\logs\\tmp.jpg";
//        InputStream in = null;
//        byte[] data = null;
//        try {
//            in = new FileInputStream(imgFile);
//            data = new byte[in.available()];
//            in.read(data);
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(data);
//    }
//
//    public Template getTemplate(){
//        configuration.setClassForTemplateLoading(this.getClass(),
//                "src/template");
//        Template t = null;
//        try {
//        // test.ftl为要装载的模板
//            t = configuration.getTemplate("sg.xml");
//            t.setEncoding("utf-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return t;
//    }
//
//    public void creatWord() throws IOException,TemplateException{
//        dataMap.put("chinese", "中文");
//        dataMap.put("english", "sada");
//        dataMap.put("main_business", "公司（或品牌）经营范围和详细内容");
//        dataMap.put("logo_component", "您希望设计的标志(logo)由");
//        dataMap.put("logo_style", "您更倾向于哪种标志(logo)风格");
//        dataMap.put("logo_colour", "标志色彩范围选定");
//        dataMap.put("colour_forbid", "禁忌色");
//        dataMap.put("colour_forbid_detail", "禁忌色详情");
//        dataMap.put("logo_startpoint", "标志创作出发点");
//        dataMap.put("logo_startpoint_detail", "标志创作出发点详情");
//        dataMap.put("cellphone", "联系电话");
//        dataMap.put("qq", "联系QQ");
//        dataMap.put("others", "其他要求");
//        dataMap.put("logo_startpoint_detail", "标志创作出发点详情");
//        dataMap.put("image", getImageStr());
////        String templePath = "";
//////        templePath=System.getProperty("user.dir");
//////        templePath = templePath+"\\src\\main\\resources"+"\\VMALOGO_QUA.ftl";
////        templePath = "D:\\logs\\";
////        System.out.println("templePath="+this.getClass().getResource("/").getPath());
////        if (System.getProperties().getProperty("os.name").startsWith("Windows"))
////        {
////            baseFont = BaseFont.createFont(path+"\\SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
////        } else {
////            baseFont = BaseFont.createFont(path+"/SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
////        }
//        File file = new File("D:\\logs");
////        configuration.setClassForTemplateLoading(this.getClass(), "D:\\logs"); // FTL文件所存在的位置
//        configuration.setDirectoryForTemplateLoading(file);
//        Template template = configuration.getTemplate("vmalogo.ftl");
//
//        File outFile = new File("D:\\logs\\logo.doc");
////        FileOutputStream out=null;
////        if(System.getProperties().getProperty("os.name").startsWith("Windows"))
////        {
////            out=new FileOutputStream(path+"\\upload\\"+pdfName);
////        }else {
////            out=new FileOutputStream(path+"/upload/"+pdfName);
////        }
//
//        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
//        template.process(dataMap, out);
//        out.close();
//
//
//    }



}
