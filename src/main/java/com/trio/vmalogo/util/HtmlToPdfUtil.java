package com.trio.vmalogo.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author //作者 fjfzuhqc
 * @ClassName: //类名/接口名/表名
 * @Description: //html转化为pdf
 * @Date    //创建/生成日期 2016/05/28 9:56
 * @History:// 历史修改记录
 * <author>  // 修改人1
 * <time>    // 修改时间
 * <desc>    // 描述修改内容
 * <author>  // 修改人2
 * <time>    // 修改时间
 * <desc>    // 描述修改内容
 */
public class HtmlToPdfUtil {
    private static String convert(String modelContent,Map<String, String> params){
        //得到实际内容
        for(String key : params.keySet()){
            modelContent = modelContent.replace(key, params.get(key));
        }
        return modelContent;
    }

    public static void main(String[] agrs)throws DocumentException, IOException {
        //页面大小
        Rectangle rect = new Rectangle(PageSize.B5.rotate());
        //页面背景色
        rect.setBackgroundColor(BaseColor.ORANGE);
        //Step 1—Create a Document.
        Document document = new Document(rect);
        //Step 2—Get a PdfWriter instance.
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\fjfzuhqc\\Desktop\\createSamplePDF.pdf"));
        //文档属性
        document.addTitle("Title@sample");
        document.addAuthor("Author@rensanning");
        document.addSubject("Subject@iText sample");
        document.addKeywords("Keywords@iText");
        document.addCreator("Creator@iText");
        //页边空白
        document.setMargins(10, 20, 30, 40);
        //Step 3—Open the Document.
        document.open();
        String relativelyPath = System.getProperty("user.dir");
        System.out.println(relativelyPath);
        BaseFont bfHei = BaseFont.createFont(relativelyPath+File.separator+"src"+File.separator+"main"+File.separator+"SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfHei,32);
        // BaseFont bfCN = BaseFont.createFont("C:\\Windows\\fonts\\STSongStd-Light", "UniGB-UCS2-H",false);
        // 中文字体定义
        Font chFont = new Font(bfHei, 12, Font.NORMAL, BaseColor.BLUE);
        Font secFont = new Font(bfHei, 12, Font.NORMAL, new BaseColor(0, 204, 255));
        //拼接字体文件的地址，--》说明下：不是每种字体都可以套进下面这个model,createFont 参数要设置
        //文件地址sources
//        String path = request.getSession().getServletContext().getRealPath("/");
//        BaseFont baseFont;
//        if (System.getProperties().getProperty("os.name").startsWith("Windows"))
//        {
//            baseFont = BaseFont.createFont(path+"\\SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//        } else {
//            baseFont = BaseFont.createFont(path+"/SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//        }
//
//        Font font = new Font(baseFont,19);//这个是中文大字体,设置大小只要该第二个参数，数字越大，字体越大
//        //还有第三个参数，用来设置粗体 Font.BOLD
//        Font font1 = new Font(baseFont,12);
//        //---->注意，想要输出中文，在创建paragraph，chunk,phrase 要在后面加上实现设置好的中文字体


        //Step 4—Add content.
        document.add(new Paragraph("Hello World"));
        document.add(new Paragraph("First page"));
        document.add(new Paragraph("中文", font));
        // html文件
       // InputStreamReader isr = new InputStreamReader(new FileInputStream("C:\\Users\\fjfzuhqc\\Desktop\\demo.txt"), "UTF-8");

        // 方法一：默认参数转换
//        XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, isr);

        document.newPage();
        document.add(new Paragraph("New page"));
        //Step 5—Close the Document.
        document.close();
    }

    public static ByteArrayInputStream createLoanAgreement(String modelContent,Map<String, String> params)throws DocumentException, IOException{
        modelContent = convert(modelContent,params);
        Document document = new Document(PageSize.LETTER);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        //BaseFont baseFont = BaseFont.createFont(relativelyPath+File.separator+"src"+File.separator+"main"+File.separator+"webapp"+File.separator+"SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        //BaseFont baseFont = BaseFont.createFont(File.separator+"src"+File.separator+"main"+File.separator+"SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
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
        return new ByteArrayInputStream(out.toByteArray());
    }
}