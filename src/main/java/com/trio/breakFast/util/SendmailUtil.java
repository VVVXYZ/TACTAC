package com.trio.breakFast.util;

/**
 * Created by ienovo on 2016/10/27.
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @ClassName: Sendmail
 * @Description: 发送Email
 * @author: 孤傲苍狼
 * @date: 2015-1-12 下午9:42:56
 *
 */

public class SendmailUtil {

    /**
     * 随机生成6位随机验证码
     * 方法说明
     */
    public static String createRandomVcode(){
        //验证码
        String vcode ="" ;
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }


    //发送邮件  getterEmail=接受者邮箱；sendText=发送内容
    public static String sendEmail(String getterEmail)  {
        String sendText=createRandomVcode();
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", "smtp.163.com");
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            //使用JavaMail发送邮件的5个步骤
            //1、创建session
            Session session = Session.getInstance(prop);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            ts.connect("smtp.163.com", "VVVXYZJW", "521521v");
            // ts.connect();
            //4、创建邮件
            Message message = createSimpleMail(session, getterEmail, sendText);
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  sendText;

    }

    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     * @Anthor:孤傲苍狼
     *
     * @param session
     * @return
     * @throws Exception
     */
    public static MimeMessage createSimpleMail(Session session,String getterEmail,String sendText)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("VVVXYZJW@163.com"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(getterEmail));
        //邮件的标题
        message.setSubject("兼职");
        //邮件的文本内容
        message.setContent("验证码:"+sendText, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }

}
