package com.brightsoft.utils.email;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * 简单邮件（不带附件的邮件）发送器 http://www.bt285.cn BT下载
 */
public class MailSender{

    /**
     * 以文本格式发送邮件
     * 
     * @param mailInfo
     *            待发送的邮件的信息
     */
    public boolean sendTextMail(MailSenderInfo mailInfo){
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if(mailInfo.isValidate()){
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据会话属性和密码验证器构造一个放行邮件的session
        Session sendMailSession = Session.getInstance(pro, authenticator);
        try{
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address fromAddress = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(fromAddress);
            // 创建邮件的接收者的地址，并设置到邮件消息中
            Address toAddress = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, toAddress);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        }
        catch(MessagingException ex){
        	ex.printStackTrace();
        }
        return false;
    }

}