package com.hengtong.led.email;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class TestEmail {
    public void r() {
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.qq.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipients(Message.RecipientType.TO, "1009522499@qq.com");//设置收信人
            mimeMessage.addRecipients(Message.RecipientType.CC, "595313551@qq.com");//抄送
            mimeMessage.setFrom("1074454337@qq.com");//邮件发送人
            mimeMessage.setSubject("测试邮件主题");//邮件主题
            mimeMessage.setContent("申请人XXX通过最高院“人民法院网上保全系统”提交了新的诉讼申请材料，业务号为：XXX，请您尽快审核！", "text/html;charset=utf-8");//正文

            Transport transport = session.getTransport();
            transport.connect("smtp.qq.com", "1074454337@qq.com", "xgercocxrersjgeg");
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());//发送邮件，第二个参数为收件人
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
