package com.saber.webserver;


//import java.io.FileOutputStream;
//import java.util.Calendar;
//import java.util.Date;
//import com.trio.vmalogo.model.*;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfContentByte;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.draw.DottedLineSeparator;
//import com.itextpdf.text.pdf.draw.LineSeparator;
//import com.itextpdf.text.pdf.draw.VerticalPositionMark;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.trio.vmalogo.service.impl.KjyLeaseContractServiceImpl;

/**
 * Created by lzw on 2015/8/19.
 */
public class PdfProduce {

//    public static void main(String[] agrs)throws DocumentException, IOException {
        /*String relativelyPath=System.getProperty("user.dir");
        System.out.println("relativelyPath="+relativelyPath+"\\src\\main\\resources"+"\\SIMYOU.TTF");

//        pdf.produce_1();*/
//        PdfProduce pdf=new PdfProduce();
//
//        KjyLeaseContract vmalogo=new KjyLeaseContract();
//        vmalogo.setBillingArea(111f);
//        vmalogo.setLeaseContractNo(vmalogo.getLeaseContractNo());
//        vmalogo.setManageFee(110f);
//        vmalogo.setBillingPrice(239f);
//        vmalogo.setLeaseContractNo(vmalogo.getLeaseContractNo());
//        vmalogo.setPartyAAgent("甲方代理");
//        vmalogo.setPartyACompany("甲方公司");
//        vmalogo.setPartyARepresent("甲方代表");
//        vmalogo.setPartyBAgent("乙方代理");
//        vmalogo.setPartyBCompany("乙方公司");
//        vmalogo.setPartyBRepresent("乙方代表");
//        vmalogo.setSigningDate(new Date());
//        vmalogo.setStartDate(new Date());
//        vmalogo.setExpirationDate(new Date());
//
//        KjyHatchingAgreement kjy2=new KjyHatchingAgreement();
//        kjy2.setPartyACompany("甲方公司");
//        kjy2.setPartyBCompany("乙方公司");
//        kjy2.setEndHatchTime(new Date());
//        kjy2.setStartHatchTime(new Date());
//        kjy2.setPartyBRepresent("乙方代表");
//        kjy2.setPartyARepresent("甲方代表");
//
//
//        pdf.produce2(vmalogo);
//        pdf.produce_1(kjy2);
//    }
//    /**
//     * 用来创建孵化器企业孵化协议书.pdf 其中孵化主要项目为，盖章，和签约时间没有
//     * */
//    public void produce_1(KjyHatchingAgreement vmalogo)throws DocumentException, IOException
//    {    //Date-->Calendar
//        Calendar StartHatchTime = Calendar.getInstance();
//        StartHatchTime.setTime(vmalogo.getStartHatchTime());
//        Calendar EndHatchTime = Calendar.getInstance();
//        StartHatchTime.setTime(vmalogo.getEndHatchTime());
//
//        //开始创建pdf
//        Document document=new Document();
//        FileOutputStream out=new FileOutputStream("孵化器企业孵化协议书.pdf");
//        PdfWriter writer = PdfWriter.getInstance(document, out);
//        document.open();
//        //项目相对地址
//        String relativelyPath=System.getProperty("user.dir");
//        //拼接字体文件的地址，--》说明下：不是每种字体都可以套进下面这个model,createFont 参数要设置
//        //文件地址sources
//        BaseFont baseFont = BaseFont.createFont(relativelyPath+"\\src\\main\\resources"+"\\SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//        Font font = new Font(baseFont,19);//这个是中文大字体,设置大小只要该第二个参数，数字越大，字体越大
//        //还有第三个参数，用来设置粗体 Font.BOLD
//        Font font1 = new Font(baseFont,12);
//        //---->注意，想要输出中文，在创建paragraph，chunk,phrase 要在后面加上实现设置好的中文字体
//        Paragraph p1=new Paragraph("福州大学科技企业孵化器企业孵化协议书",font); //这个是pdf标题，后期要修改
//        p1.setAlignment(Element.ALIGN_CENTER); //这个参数是段落居中
//        document.add(p1);
//        //--->说明：一个paragraph 可以包含chunk,phrase;-->用add()来添加，用来段落排版
//        Paragraph  p2=new Paragraph("甲方：",font1);
//        Chunk ph1=new Chunk(vmalogo.getPartyACompany(),font1);
//        p2.add(ph1);
//        document.add(p2);
//        Paragraph p3=new Paragraph("乙方：",font1);
//        Phrase ph2=new Phrase(vmalogo.getPartyBCompany(),font1);
//        p3.add(ph2);
//        document.add(p3);
//        Paragraph p4=new Paragraph("为了促进高科技产业发展，扶持物联网相关科技型中小企业迅速成长，加快科技成果转化，更好地为科技创业企业提供孵化服务，甲方全面负责科技企业孵化器的建设工作，全权负责孵化企业的管理。乙方自筹资金，开办科技企业，实行独立核算，自主经营，自负盈亏，为使所拥有的科技成果尽快转化，自愿接受甲方孵化管理，并进入甲方孵化器内创业。双方经充分协商，达成如下协议，共同遵守。",font1);
//       //设置首行缩进的大小 --》这里是30f
//        p4.setFirstLineIndent(30f);
//        document.add(p4);
//        Paragraph p5=new Paragraph("第一条 甲方同意乙方作为孵化对象进驻嘉定区科技企业孵化器，孵化主要项目为：",font1);
//        p5.setFirstLineIndent(30f);
//        Phrase ph3=new Phrase(""); //---->项目,待填
//        p5.add(ph3);
//        document.add(p5);
//        Paragraph p6=new Paragraph("第二条 甲方对乙方提供的服务内容：",font1);
//        p6.setFirstLineIndent(30f);
//        document.add(p6);
//        Paragraph p7=new Paragraph("1、甲方同意在其所有的孵化基地，为乙方提供孵化场地，房屋租赁的相关事宜以双方签订的《孵化场地租赁合同》为准。",font1);
//        p7.setFirstLineIndent(30f);
//        document.add(p7);
//        Paragraph p8=new Paragraph("2、甲方及时向乙方传达国家和地方政府的有关政策和规定，提供政策指导，协助企业落实各项优惠政策。",font1);
//        p8.setFirstLineIndent(30f);
//        document.add(p8);
//        Paragraph p9=new Paragraph("3、甲方就下列服务给予乙方必要协助：工商注册及税务登记、银行开户、代理记账、专利申请、企业管理咨询、商务服务、培训服务、法律咨询及物业管理等。",font1);
//        p9.setFirstLineIndent(30f);
//        document.add(p9);
//        Paragraph p10=new Paragraph("4、乙方经申请并经甲方审查通过后，可享受甲方关于促进孵化企业发展的各项优惠、扶持政策及资金支持服务，详见《孵化器优惠政策》。",font1);
//        p10.setFirstLineIndent(30f);
//        document.add(p10);
//        Paragraph p11=new Paragraph("5、甲方支持并协助乙方申报国家、市火炬计划、科技攻关计划、嘉定区中小企业创新基金等各类计划和基金，并进行跟踪管理和服务。",font1);
//        p11.setFirstLineIndent(30f);
//        document.add(p11);
//        Paragraph p12=new Paragraph("6、甲方支持并协助乙方申报高新技术企业，认定高新技术产品，成果鉴定、产品鉴定等。",font1);
//        p12.setFirstLineIndent(30f);
//        document.add(p12);
//        Paragraph p13=new Paragraph("7、乙方若在三年内达到毕业条件，可到菊园新区、产业园内购买或租赁办公场地、厂房，甲方将给予协助。",font1);
//        p13.setFirstLineIndent(30f);
//        document.add(p13);
//        Paragraph p14=new Paragraph("8、组织企业间的联谊活动，交流企业发展经验，开展业务合作。",font1);
//        p14.setFirstLineIndent(30f);
//        document.add(p14);
//        Paragraph p15=new Paragraph("第三条 乙方的责任：",font1);
//        p15.setFirstLineIndent(30f);
//        document.add(p15);
//        Paragraph p16=new Paragraph("1、乙方承诺在上海菊园经济发展中心注册成立公司，注册资金真实，具备项目所需要的必要资金，实地型企业保证按照双方约定的科研开发用途使用孵化器办公场所。乙方发生工商注册、经营范围等事项的变更，应提前15日通知甲方，并在变更后3日内到甲方备案。",font1);
//        p16.setFirstLineIndent(30f);
//        document.add(p16);
//        Paragraph p17=new Paragraph("2、乙方属注册型企业，应自签订孵化协议之日起30个工作日内开展科技项目研发工作；实地型企业应自甲方向其交付房屋之日起30个工作日内入驻孵化器并开展科技项目研发工作。",font1);
//        p17.setFirstLineIndent(30f);
//        document.add(p17);
//        Paragraph p18=new Paragraph("3、乙方在生产经营活动中，必须自觉遵守国家的政策、法律和法规，由于经营不当造成的亏损以及由此而带来的债权、债务等经济责任均由乙方独立承担，与甲方无关。",font1);
//        p18.setFirstLineIndent(30f);
//        document.add(p18);
//        Paragraph p19=new Paragraph("4、乙方应甲方的要求需及时向甲方提供以下资料，甲方承诺将予以保密：",font1);
//        p19.setFirstLineIndent(30f);
//        document.add(p19);
//        Paragraph p20=new Paragraph("1) 企业所有权情况；",font1);
//        p20.setFirstLineIndent(30f);
//        document.add(p20);
//        Paragraph p21=new Paragraph("2) 主要管理人员的资料；",font1);
//        p21.setFirstLineIndent(30f);
//        document.add(p21);
//        Paragraph p22=new Paragraph("3) 每季按时如实报送财务报表和税务申报表(复印件)，认真完成政府部门交办的各项统计工作；",font1);
//        p22.setFirstLineIndent(30f);
//        document.add(p22);
//        Paragraph p23=new Paragraph("4）每半年按时报送项目孵化进展报告；",font1);
//        p23.setFirstLineIndent(30f);
//        document.add(p23);
//        Paragraph p24=new Paragraph("5）每年底上交《在孵企业考核表》（见附件5）。",font1);
//        p24.setFirstLineIndent(30f);
//        document.add(p24);
//        Paragraph p25=new Paragraph("5、乙方应服从甲方的统一管理，严格履行协议规定，及时交纳水、电、网络、物业管理等费用。",font1);
//        p25.setFirstLineIndent(30f);
//        document.add(p25);
//        Paragraph p26=new Paragraph("6、乙方应严格遵守甲方所制定的对孵化企业的一切规章制度，维护孵化器的正常工作秩序。",font1);
//        p26.setFirstLineIndent(30f);
//        document.add(p26);
//        Paragraph p27=new Paragraph("7、根据政府职能部门要求，配合甲方做好有关工作，积极参加甲方组织的各项活动。",font1);
//        p27.setFirstLineIndent(30f);
//        document.add(p27);
//        Paragraph p28=new Paragraph("8、乙方毕业后，应以上海菊园经济发展中心作为项目开发及生产营销的所在地。",font1);
//        p28.setFirstLineIndent(30f);
//        document.add(p28);
//        Paragraph p29=new Paragraph("第四条 甲方有权在下列情况出现后，书面通知乙方纠正或整改。经甲方书面通知仍不纠正整改的，甲方有权解除本合同并收回乙方承租的房屋，并有权要求乙方承担违约责任，对于情节严重的，甲方可直接书面通知解除合同：",font1);
//        p29.setFirstLineIndent(30f);
//        document.add(p29);
//        Paragraph p30=new Paragraph("1、乙方属实地型孵化企业的，未按协议规定时间入驻孵化器并开展工作；",font1);
//        p30.setFirstLineIndent(30f);
//        document.add(p30);
//        Paragraph p31=new Paragraph("2、乙方人员及项目进展缓慢，工作不力的；",font1);
//        p31.setFirstLineIndent(30f);
//        document.add(p31);
//        Paragraph p32=new Paragraph("3、乙方未按时提交财务报表、税务申报表，未按时提交孵化项目进展报告和年度总结；",font1);
//        p32.setFirstLineIndent(30f);
//        document.add(p32);
//        Paragraph p33=new Paragraph("4、乙方未经甲方书面同意擅自更改孵化项目；",font1);
//        p33.setFirstLineIndent(30f);
//        document.add(p33);
//        Paragraph p34=new Paragraph("5、孵化项目的进展情况未达到入孵申请书所预期阶段目标的60%；",font1);
//        p34.setFirstLineIndent(30f);
//        document.add(p34);
//        Paragraph p35=new Paragraph("6、弄虚作假，造成恶劣影响的；",font1);
//        p35.setFirstLineIndent(30f);
//        document.add(p35);
//        Paragraph p36=new Paragraph("7、进行违法活动，严重违反规章制度，不服从管理；",font1);
//        p36.setFirstLineIndent(30f);
//        document.add(p36);
//        Paragraph p37=new Paragraph("8、未能按有关规定及时交纳房租费及其它费用；",font1);
//        p37.setFirstLineIndent(30f);
//        document.add(p37);
//        Paragraph p38=new Paragraph("9、其他违反本协议书约定的行为。",font1);
//        p38.setFirstLineIndent(30f);
//        document.add(p38);
//        Paragraph p39=new Paragraph("第五条 乙方提前达到《上海市嘉定区科技企业孵化器毕业标准》，协议可提前终止；本协议书终止、解除的，双方签订的《孵化场地租赁合同》以及优惠政策同时终止或解除。",font1);
//        p39.setFirstLineIndent(30f);
//        document.add(p39);
//        Paragraph p40=new Paragraph("第六条 本协议规定的孵化期限暂定为3年，自",font1); //   年   月   日起至　 年   月   日止，任何一方若终止本协议，应提前30日告知。"
//        p40.setFirstLineIndent(30f);
//        Chunk cc8=new Chunk(StartHatchTime.get(Calendar.YEAR)+"");
//        cc8.setUnderline(0.2f,-2f);
//        p40.add(cc8);
//        Phrase cc9=new Phrase("年",font1);
//        p40.add(cc9);
//        Chunk cc10=new Chunk((StartHatchTime.get(Calendar.MONTH)+1)+"");
//        cc10.setUnderline(0.2f,-2f);
//        p40.add(cc10);
//        Phrase cc11=new Phrase("月",font1);
//        p40.add(cc11);
//        Chunk cc12=new Chunk(StartHatchTime.get(Calendar.DAY_OF_MONTH)+"");
//        cc12.setUnderline(0.2f,-2f);
//        p40.add(cc12);
//        Phrase cc13=new Phrase("日起至",font1);
//        p40.add(cc13);
//        Chunk cc14=new Chunk(EndHatchTime.get(Calendar.YEAR)+"");
//        cc14.setUnderline(0.2f,-2f);
//        p40.add(cc14);
//        Phrase cc15=new Phrase("年",font1);
//        p40.add(cc15);
//        Chunk cc16=new Chunk((EndHatchTime.get(Calendar.MONTH)+1)+"");
//        cc16.setUnderline(0.2f,-2f);
//        p40.add(cc16);
//        Phrase cc17=new Phrase("月",font1);
//        p40.add(cc17);
//        Chunk cc18=new Chunk(EndHatchTime.get(Calendar.DAY_OF_MONTH)+"");
//        cc18.setUnderline(0.2f,-2f);
//        p40.add(cc18);
//        Phrase cc19=new Phrase("日止。，任何一方若终止本协议，应提前30日告知。",font1);
//        p40.add(cc19);
//        document.add(p40);
//        Paragraph p41=new Paragraph("第七条 本协议未尽事宜，双方另行协商解决。",font1);
//        p41.setFirstLineIndent(30f);
//        document.add(p41);
//        Paragraph p42=new Paragraph("第八条 因本协议履行过程中产生的争议、纠纷，双方同意提请市人民法院诉讼。",font1);
//        p42.setFirstLineIndent(30f);
//        document.add(p42);
//        Paragraph p43=new Paragraph("第九条 本协议一式两份，甲乙双方各执一份。自双方签字之日起正式生效。",font1);
//        p43.setFirstLineIndent(30f);
//        document.add(p43);
//        Paragraph p44=new Paragraph("附件：孵化场地租赁合同     ",font1);
//        p44.setFirstLineIndent(30f);
//        document.add(p44);
//        Paragraph p45=new Paragraph("甲方:",font1);
//        Phrase ph8=new Phrase(vmalogo.getPartyACompany(),font1);
//        p45.add(ph8);
//        Phrase ph8x=new Phrase("                         ");
//        p45.add(ph8x);
//        Phrase ph9=new Phrase("乙方:",font1);
//        p45.add(ph9);
//        Phrase ph10=new Phrase(vmalogo.getPartyBCompany(),font1);
//        p45.add(ph10);
//        p45.setFirstLineIndent(30f);
//        document.add(p45);
//        Paragraph p46=new Paragraph("　　　　　　　　（盖章）　                                      （盖章）",font1);
//        p46.setFirstLineIndent(30f);
//        document.add(p46);
//        document.add( Chunk.NEWLINE);//用来换行--》用一个空段落也可以
//        document.add( Chunk.NEWLINE);
//        document.add( Chunk.NEWLINE);
//        document.add( Chunk.NEWLINE);
//        //以下所有的段落中的空格，都是用来排版，比较简单--》可能还有其他的方法，我没去找
//        Paragraph p47=new Paragraph("",font1);
//        p47.setFirstLineIndent(30f);
//        Phrase ph4=new Phrase("代表:");
//        p47.add(ph4);
//        Phrase ph5=new Phrase(vmalogo.getPartyARepresent());
//        p47.add(ph5);
//        Phrase ph5x=new Phrase("                          ");
//        p47.add(ph5x);
//        Phrase ph6=new Phrase("代表:");
//        p47.add(ph6);
//        Phrase ph7=new Phrase(vmalogo.getPartyBRepresent());
//        p47.add(ph7);
//        document.add(p47);
//        Paragraph p48=new Paragraph("年"+"月"+"日                                                "+"年"+"月"+"日",font1);
//
//        p48.setFirstLineIndent(30f);
//        document.add(p48);
//        Paragraph p49=new Paragraph("                     ",font1);
//        p49.setFirstLineIndent(30f);
//        document.add(p49);
//
//        document.close();
//    }
//    /**
//     * 用来创建孵化场地租赁合同.pdf，其中，套内面积没有，场地编号没有,保证金要自己求
//     * 签约日期我是用SigningDate来当做
//     */
//
//    public void  produce2(KjyLeaseContract vmalogo)throws DocumentException, IOException{
//        //Date ==>Calendar
//        Calendar StartDate = Calendar.getInstance();
//        StartDate.setTime(vmalogo.getSigningDate());
//        Calendar ExpirationDate = Calendar.getInstance();
//        ExpirationDate.setTime(vmalogo.getExpirationDate());
//        Calendar SigningDate = Calendar.getInstance();
//        SigningDate.setTime(vmalogo.getSigningDate());
//        //开始创建pdf
//        Document document=new Document();
//        FileOutputStream out=new FileOutputStream("孵化场地租赁合同.pdf");
//        PdfWriter writer = PdfWriter.getInstance(document, out);
//        document.open();
//        String relativelyPath=System.getProperty("user.dir");
//        BaseFont baseFont = BaseFont.createFont(relativelyPath+"\\src\\main\\resources"+"\\SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//        Font font = new Font(baseFont,23);
//        Font fontx=new Font(baseFont,23,Font.BOLD); //23号粗体
//        Font font1 = new Font(baseFont,12); //正常
//        Font font1x = new Font(baseFont,12,Font.BOLD);//12号粗体
//		/*    添加图片
//		 * 文件地址是在resources
//		 */
//        Image img = Image.getInstance(relativelyPath+"\\src\\main\\resources"+"\\2.bmp");
//        img.setAlignment(Image.LEFT | Image.TEXTWRAP);
//        img.setBorder(Image.BOX);         //设置图片边框
//        img.setBorderWidth(10);             //边框的厚度
//        img.setBorderColor(BaseColor.WHITE);//边框的颜色
//        img.scaleToFit(520, 300);//图片的大小
//        img.setRotationDegrees(0);//旋转的角度--》这里是0度
//        document.add(img);
//
//        document.add(new Paragraph("                                                   "));
//        Paragraph p1=new Paragraph("孵化场地租赁合同",fontx);
//        p1.setAlignment(Element.ALIGN_CENTER);
//        document.add(p1);
//        Paragraph px=new Paragraph("编号："+vmalogo.getLeaseContractNo(),font1x);
//        document.add(new Paragraph("\n"));
//        px.setAlignment(Element.ALIGN_RIGHT);
//        document.add(px);
//        Chunk c1 = new Chunk("出租方: ", font1x);
//        document.add(c1);
//        Chunk c2=new Chunk(vmalogo.getPartyACompany(),font1);
//        document.add(c2);
//        Chunk c2x=new Chunk("（以下简称甲方）",font1);
//        document.add(c2x);
//        document.add(Chunk.NEWLINE);
//        Chunk c3=new Chunk("承租方：",font1x);
//        document.add(c3);
//        Chunk c4=new Chunk(vmalogo.getPartyBCompany(),font1);
//        document.add(c4);
//        Chunk c5=new Chunk("（以下简称乙方）",font1);
//        document.add(c5);
//        document.add(Chunk.NEWLINE);
//        document.add(Chunk.NEWLINE);
//        document.add(Chunk.NEWLINE);
//        Paragraph p4=new Paragraph("甲乙双方经协商，甲方同意将已装修的孵化场地中的一部分租赁给乙方作科研办公使用，并自愿达成如下合同条款，供双方共同遵守执行。",font1);
//        p4.setFirstLineIndent(30f);
//        document.add(p4);
//        Paragraph p5=new Paragraph("一、孵化场地（以下简称场地）基本情况",font1x);
//        p5.setFirstLineIndent(30f);
//        document.add(p5);
//        Paragraph pp1=new Paragraph("甲方提供的孵化场地位于江门市篁庄大道的创业大厦内，场地编号为",font1);
//        pp1.setFirstLineIndent(30f);
//        Chunk cc2=new Chunk("11212",font1); //这个是场地编号
//        cc2.setUnderline(0.2f, -2f);
//        pp1.add(cc2);
//       Phrase ccx=new Phrase("，场地套内面积",font1);
//        pp1.add(ccx);
//        Chunk ccx1=new Chunk(""); //套内面积
//        ccx1.setUnderline(0.2f,-2f);
//        pp1.add(ccx1);
//         Phrase cc3=new Phrase("平方米，建筑面积为",font1);
//        pp1.add(cc3);
//        Chunk cc4=new Chunk(vmalogo.getBillingArea()+"",font1);//建筑面积
//        cc4.setUnderline(0.2f,-2f);
//        pp1.add(cc4);
//        Phrase cc5=new Phrase("平方米（场地租金和管理费按建筑面积收费）。",font1);
//        pp1.add(cc5);
//        document.add(pp1);
//        Paragraph p6=new Paragraph("上述场地仅限于乙方科研办公之用，乙方在承租期间不得改变场地用途，乙方员工不得在场地内留宿。",font1);
//        p6.setFirstLineIndent(30f);
//        document.add(p6);
//        Paragraph p8=new Paragraph("二、租赁期限",font1x);
//        p8.setFirstLineIndent(30f);
//        document.add(p8);
//        Paragraph p7=new Paragraph("本合同租赁期限为" ,font1);//  年，自    年   月   日起至     年   月   日止。"
//        p7.setFirstLineIndent(30f);
//        Chunk cc6=new Chunk(" ");//合同租赁期限
//        cc6.setUnderline(0.2f,-2f);
//        p7.add(cc6);
//        Phrase cc7=new Phrase("年，自",font1);
//        p7.add(cc7);
//        Chunk cc8=new Chunk(StartDate.get(Calendar.YEAR)+"");
//        cc8.setUnderline(0.2f,-2f);
//        p7.add(cc8);
//        Phrase cc9=new Phrase("年",font1);
//        p7.add(cc9);
//        Chunk cc10=new Chunk((StartDate.get(Calendar.MONTH)+1)+"");
//        cc10.setUnderline(0.2f,-2f);
//        p7.add(cc10);
//        Phrase cc11=new Phrase("月",font1);
//        p7.add(cc11);
//        Chunk cc12=new Chunk(StartDate.get(Calendar.DAY_OF_MONTH)+"");
//        cc12.setUnderline(0.2f,-2f);
//        p7.add(cc12);
//        Phrase cc13=new Phrase("日起至",font1);
//        p7.add(cc13);
//        Chunk cc14=new Chunk(ExpirationDate.get(Calendar.YEAR)+"");
//        cc14.setUnderline(0.2f,-2f);
//        p7.add(cc14);
//        Phrase cc15=new Phrase("年",font1);
//        p7.add(cc15);
//        Chunk cc16=new Chunk((ExpirationDate.get(Calendar.MONTH)+1)+"");
//        cc16.setUnderline(0.2f,-2f);
//        p7.add(cc16);
//        Phrase cc17=new Phrase("月",font1);
//        p7.add(cc17);
//        Chunk cc18=new Chunk(ExpirationDate.get(Calendar.DAY_OF_MONTH)+"");
//        cc18.setUnderline(0.2f,-2f);
//        p7.add(cc18);
//        Phrase cc19=new Phrase("日止。",font1);
//        p7.add(cc19);
//        document.add(p7);
//        Paragraph p9=new Paragraph("三、租金及支付方式",font1x);
//        p9.setFirstLineIndent(30f);
//        document.add(p9);
//        Paragraph p10=new Paragraph("乙方应按月向甲方交纳租金及管理费，先缴后用。缴纳期限为上月末结束前5日缴纳下月的租金及管理费。首期租金及管理费的缴纳期限为本合同签订生效、乙方进驻场地前10日内由乙方一次性缴纳给甲方。",font1);
//        p10.setFirstLineIndent(30f);
//        document.add(p10);
//        Paragraph p11=new Paragraph("乙方应于本合同期间向甲方缴纳合同保证金",font1);
//        p11.setFirstLineIndent(30f);
//        document.add(p11); //        元(第一年一个月的租金及管理费)，该保证金于本合同签订之日起3日内由乙方一次性缴纳给甲方。"
//        Chunk cc20=new Chunk(vmalogo.getBillingPrice()+vmalogo.getManageFee()+"");//
//        Phrase cc21=new Phrase("元(第一年一个月的租金及管理费)，该保证金于本合同签订之日起3日内由乙方一次性缴纳给甲方。");
//        Paragraph p12=new Paragraph("合同保证金在乙方依约履行完本合同，并经甲方对房屋验收合格，办完退房手续后7日内由甲方一次性退还乙方。本合同期间，乙方所缴的合同保证金不计息。",font1);
//        p12.setFirstLineIndent(30f);
//        document.add(p12);
//        Paragraph p13=new Paragraph("负一层的室内汽车停车场按每月每个车位     元收费，按月先缴费后使用。",font1);//记得填上去
//        p13.setFirstLineIndent(30f);
//        document.add(p13);
//        Paragraph p14=new Paragraph("四、场地交付及期限",font1x);
//        p14.setFirstLineIndent(30f);
//        document.add(p14);
//        Paragraph p15=new Paragraph("甲方应在收到乙方首期租金及管理费后10日内将场地交付乙方。甲方提供的场地应为腾空房屋。甲乙双方应办理书面交接手续。",font1);
//        p15.setFirstLineIndent(30f);
//        document.add(p15);
//        Paragraph p16=new Paragraph("五、场地的再装修",font1x);
//        p16.setFirstLineIndent(30f);
//        document.add(p16);
//        Paragraph p17=new Paragraph("场地内的再装修由乙方自行决定并承担有关费用。乙方再装修时不得改变原场地设施及结构。如乙方因工作需要确需对原有设施及结构进行改造的，乙方应事先将改造方案及图纸报甲方审批，甲方批准后方可施工。",font1);
//        p17.setFirstLineIndent(30f);
//        document.add(p17);
//        Paragraph p18=new Paragraph("乙方搬出时，其所做的装修或改造形成的附着物必须全部拆除，并恢复场地原状。",font1);
//        p18.setFirstLineIndent(30f);
//        document.add(p18);
//        Paragraph p19=new Paragraph("甲方负责出租场地的日常维修工作。乙方租赁期间，其所租场地内相关设施如出现损坏，甲方负责相关维修工作，材料费用由乙方支付。",font1);
//        p19.setFirstLineIndent(30f);
//        document.add(p19);
//        Paragraph p20=new Paragraph("六、场地的使用约定",font1x);
//        p20.setFirstLineIndent(30f);
//        document.add(p20);
//        Paragraph p21=new Paragraph("租赁期间，乙方应协助甲方做好安全保卫、清洁卫生等工作，具体约定如下：",font1);
//        p21.setFirstLineIndent(30f);
//        document.add(p21);
//        Paragraph p22=new Paragraph("1、乙方应遵守甲方制定的与安全有关的各项规章制度，认真落实防火、防盗、防爆、防破坏、安全用电、保密等各项工作，制定本企业的安全生产制度，签订《消防安全责任书》，指派专人负责安全工作，做到安全责任落实到单位职工，避免重大、意外事故的发生。",font1);
//        p22.setFirstLineIndent(30f);
//        document.add(p22);
//        Paragraph p23=new Paragraph("2、甲方应配备合理的消防设施，但乙方在运营过程中有特殊要求时，应主动报消防主管部门审批、报甲方备案并配备相应的消防设施或设备，甲方应予以协助。",font1);
//        p23.setFirstLineIndent(30f);
//        document.add(p23);
//        Paragraph p24=new Paragraph("3、乙方租赁的场地不得存放易燃、易爆及其他危险物品。",font1);
//        p24.setFirstLineIndent(30f);
//        document.add(p24);
//        Paragraph p25=new Paragraph("4、乙方的作息时间应与甲方的作息时间同步。特殊情况下，乙方临时调整作息时间或需加班的，应通知甲方。",font1);
//        p25.setFirstLineIndent(30f);
//        document.add(p25);
//        Paragraph p26=new Paragraph("5、乙方在租赁期间,必须遵守甲方大楼的管理要求。",font1);
//        p26.setFirstLineIndent(30f);
//        document.add(p26);
//        Paragraph p27=new Paragraph("6、走廊、楼梯、开水间及公用厕所等公共区域的卫生由甲方派专人负责。场地内部的卫生由乙方自行负责。双方均有义务维持环境的整洁。",font1);
//        p27.setFirstLineIndent(30f);
//        document.add(p27);
//        Paragraph p28=new Paragraph("7、合同期间，乙方所消耗的电费（专用电表）和水费（专用水表）由乙方自行承担，甲方代为收取。公用水费和电梯电费及维护费，按乙方租赁的建筑面积分摊计算，甲方代为收取。",font1);
//        p28.setFirstLineIndent(30f);
//        document.add(p28);
//        Paragraph p29=new Paragraph("七、合同的终止与变更",font1x);
//        p29.setFirstLineIndent(30f);
//        document.add(p29);
//        Paragraph p30=new Paragraph("本合同终止时，乙方应在7日内腾空交还场地。乙方在搬出时应将场地打扫干净，否则甲方有权在乙方缴纳的合同保证金中扣除有关的清洁费用。",font1);
//        p30.setFirstLineIndent(30f);
//        document.add(p30);
//        Paragraph p31=new Paragraph("本合同终止时，乙方必须按甲方关于退房的有关管理规定及本合同约定，与甲方办理书面的场地交接手续。乙方搬出5日后，场地内如仍有余物视为乙方放弃所有权，甲方有权处理上述余物。",font1);
//        p31.setFirstLineIndent(30f);
//        document.add(p31);
//        Paragraph p32=new Paragraph("本合同期间，乙方因工作要求需调整租赁场地的或变更租金的，甲乙双方协商解决。",font1);
//        p32.setFirstLineIndent(30f);
//        document.add(p32);
//        Paragraph p33=new Paragraph("本合同期满，乙方如要求继续租赁的，则需提前两个月书面向甲方提出，甲乙双方另行签订租赁合同，本合同期限不续延。在同等条件下，乙方享有优先承租权。",font1);
//        p33.setFirstLineIndent(30f);
//        document.add(p33);
//        Paragraph p34=new Paragraph("八、合同的解除",font1x);
//        p34.setFirstLineIndent(30f);
//        document.add(p34);
//        Paragraph p35=new Paragraph("租赁期间，除本合同另有规定外，任何一方提出解除本合同，需提前两个月书面通知对方。双方协商一致，中止合同。协商未达成一致的，本合同继续履行。其中：甲方提出解除本合同的，应双倍返还合同保证金，乙方提出解除本合同的，合同保证金不返还。",font1);
//        p35.setFirstLineIndent(30f);
//        document.add(p35);
//        Paragraph p36=new Paragraph("乙方有下列情形之一的，甲方有权终止合同并收回场地，所缴纳的租金及管理费、合同保证金不再退还乙方，如造成甲方损失的，还应由乙方负责赔偿：",font1);
//        p36.setFirstLineIndent(30f);
//        document.add(p36);
//        Paragraph p37=new Paragraph("1、擅自将承租的场地转租、转让、转借的；",font1);
//        p37.setFirstLineIndent(30f);
//        document.add(p37);
//        Paragraph p38=new Paragraph("2、擅自将承租的场地改变用途或擅自与他人调换使用，经甲方通知拒不纠正的；",font1);
//        p38.setFirstLineIndent(30f);
//        document.add(p38);
//        Paragraph p39=new Paragraph("3、擅自拆改承租房屋结构或不当使用场地，影响他人，经甲方通知拒不纠正的；",font1);
//        p39.setFirstLineIndent(30f);
//        document.add(p39);
//        Paragraph p40=new Paragraph("4、利用承租场地从事违法活动，损害公共利益的；",font1);
//        p40.setFirstLineIndent(30f);
//        document.add(p40);
//        Paragraph p41=new Paragraph("5、拖欠租金及管理费超过30天的或连续两个月不按时足额缴纳水电费的。",font1);
//        p41.setFirstLineIndent(30f);
//        document.add(p41);
//        Paragraph p42=new Paragraph("6、乙方在租赁期间，不能履行本合同第六条“场地的使用约定”，影响中心大楼正常工作秩序。",font1);
//        p42.setFirstLineIndent(30f);
//        document.add(p42);
//        Paragraph p43=new Paragraph("第九条 本协议一式两份，甲乙双方各执一份。自双方签字之日起正式生效。",font1);
//        p43.setFirstLineIndent(30f);
//        document.add(p43);
//        Paragraph p44=new Paragraph("九、违约责任   ",font1x);
//        p44.setFirstLineIndent(30f);
//        document.add(p44);
//        Paragraph p45=new Paragraph("因乙方违反安全、消防制度，发生火灾或其它事故而给甲方或其它企业造成经济损失的，乙方应承担全部的法律责任。",font1);
//        p45.setFirstLineIndent(30f);
//        document.add(p45);
//        Paragraph p46=new Paragraph("乙方逾期支付租金及管理费或其他费用的，甲方有权按逾期数额的万分之五/天向乙方加收滞纳金，并有权限制乙方财产或设备的搬出。",font1);
//        p46.setFirstLineIndent(30f);
//        document.add(p46);
//        document.add(new Paragraph("因甲方原因如场地权属争议等导致乙方无法正常使用场地的，乙方有权解除本合同。      ",font1));
//        document.add(new Paragraph("因甲乙双方中任何一方无故违约的，违约方必须赔偿另一方半年租金及管理费的损失（按甲方当年收取乙方租金及管理费标准）。  ",font1));
//        document.add(new Paragraph("十、因不可抗力原因导致场地损坏和造成损失的，本合同终止，甲乙双方互不承担责任。 ",font1));
//        document.add(new Paragraph("十一、本合同未尽事宜，由甲乙双方另行协商解决并签订书面补充协议，补充协议与本合同具有同等效力。",font1));
//        Paragraph p47=new Paragraph("十二、本合同一式四份，甲乙双方各执两份。本合同自甲乙双方签字盖章之日起生效。 ",font1);
//        p47.setFirstLineIndent(30f);
//        document.add(p47);
//        document.add(Chunk.NEWLINE); //这个是换行
//        document.add(Chunk.NEWLINE);
//        document.add(Chunk.NEWLINE);
//
//        Paragraph p48=new Paragraph("");
//        Chunk c51 = new Chunk("甲方：", font1x);
//        p48.add(c51);
//        Phrase c6=new Phrase(vmalogo.getPartyACompany(),font1);
//        p48.add(c6);
//        Chunk c52 = new Chunk("                                    乙方：", font1x);
//        p48.add(c52);
//        Phrase c62=new Phrase(vmalogo.getPartyBCompany(),font1);
//        p48.add(c62);
//        document.add(p48);
//
//        Paragraph p49=new Paragraph("");
//        Chunk c53 = new Chunk("法定代表人：", font1x);
//        p49.add(c53);
//        Phrase c63=new Phrase(vmalogo.getPartyARepresent(),font1);
//        p49.add(c63);
//        Chunk c54 = new Chunk("                              法定代表人：", font1x);
//        p49.add(c54);
//        Phrase c64=new Phrase(vmalogo.getPartyBRepresent(),font1);
//        p49.add(c64);
//        document.add(p49);
//       Paragraph p50=new Paragraph("");
//        Chunk c55 = new Chunk("经 办 人：", font1x);
//        p50.add(c55);
//        Phrase c65=new Phrase(vmalogo.getPartyAAgent(),font1);
//        p50.add(c65);
//        Chunk c57 = new Chunk("                                经 办 人：", font1x);
//        p50.add(c57);
//        Phrase c67=new Phrase(vmalogo.getPartyBAgent(),font1);
//        p50.add(c67);
//        document.add(p50);
//        Paragraph p51=new Paragraph(SigningDate.get(Calendar.YEAR)+"年"+(SigningDate.get(Calendar.MONTH)+1)+"月"+SigningDate.get(Calendar.DAY_OF_MONTH)+"日                                                "+SigningDate.get(Calendar.YEAR)+"年"+(SigningDate.get(Calendar.MONTH)+1)+"月"+SigningDate.get(Calendar.DAY_OF_MONTH)+"日",font1x);
//        document.add(p51);
//        //document.add(new Paragraph("",font1));
//        //document.add(new Paragraph("",font1));
//        document.close();
//
//    }

}
