package com.sky.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;


/**
 * 发送邮件
 * Created by sky on 2017/12/3.
 */
public class EmailUtils {
    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);
    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    public static String myEmailAccount = "technesky@163.com";
    public static String myEmailPassword = "sunzhg009";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.163.com";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "280085328@qq.com,597309258@qq.com";

    public static boolean sendHtmlMail(String subject,String content){

        boolean result = true;

        try{
            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

            final String smtpPort = "465";
            props.setProperty("mail.smtp.port", smtpPort);
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", smtpPort);

            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getInstance(props);
            session.setDebug(true);           // 设置为debug模式, 可以查看详细的发送 log
            String [] receiveMail=receiveMailAccount.split(",");
            MimeMessage message=null;
            for (int i=0;i<receiveMail.length;i++){
                // 1. 创建一封邮件
                 message = new MimeMessage(session);

                // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
                message.setFrom(new InternetAddress(myEmailAccount, "蜗牛画室", "UTF-8"));

                // 3. To: 收件人（可以增加多个收件人、抄送、密送）
                message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail[i], "XX用户", "UTF-8"));

                // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
                message.setSubject(subject, "UTF-8");

                // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
                message.setContent(content, "text/html;charset=UTF-8");

                // 6. 设置发件时间
                message.setSentDate(new Date());

                // 7. 保存设置
                message.saveChanges();
                // 4. 根据 Session 获取邮件传输对象
                Transport transport = session.getTransport();


                transport.connect(myEmailAccount, myEmailPassword);

                // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
                transport.sendMessage(message, message.getAllRecipients());
                // 7. 关闭连接
                transport.close();
            }


        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            result = false;
            logger.info("EmailUtils.method [sendHtmlMail]: email send result-" + result +",error message-" + e);
        } catch (UnsupportedEncodingException e) {
            result = false;

            e.printStackTrace();
        }

        return result;
    }

    /*public static void main(String[] args) throws Exception{
        sendHtmlMail("预约提醒","姓名：黎明，年龄：10 家长电话：12314512121");

    }*/



}
