package com.trio.breakFast.util;

import org.apache.log4j.Logger;

import javax.mail.MessagingException;

/**
 * Created by xiaozhi on 2015/8/25.
 */

public class EmailUtil {
    private static final Logger logger = Logger.getLogger(EmailUtil.class);
    private static String senderEmail = "kjy@xiaozhilin.com";//"breakFast@xiaozhilin.com";//发件信箱
    private static String smtpPassWord = "Kjy123456";       //smtp密码
    private static String smtpServer = "smtp.mxhichina.com";//smtp服务器
    public static Boolean sendEmail(String receiverEmail,String subject,String content){
        try {
            SimpleSMTPSender simpleSMTPSender = new SimpleSMTPSender(senderEmail, smtpPassWord, smtpServer);
            simpleSMTPSender.setProperties(receiverEmail);
            simpleSMTPSender.setMessage(subject, content);
            simpleSMTPSender.doSend();

        } catch (MessagingException e) {
            logger.error("MessagingException===", e);
            return false;
        }
        return true;
    }
}
