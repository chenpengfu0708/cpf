package com.hengtong.led.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@RestController
public class TestEmailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/testMail")
    public void test() {
        String msg = "abc";
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setSubject("通知");
//        mailMessage.setText("今晚六点准时跑路");
//        mailMessage.setFrom("1074454337@qq.com");
//
//        //收件人
//        mailMessage.setTo("1009522499@qq.com");
//        mailMessage.setCc("595313551@qq.com");
//
//        mailSender.send(mailMessage);
        try {
        Message message1 = mailSender.createMimeMessage();
        StringBuffer messageText=new StringBuffer();//内容以html格式发送,防止被当成垃圾邮件
        messageText.append("<span>Hi，您好:</span></br>");
        messageText.append("<span>你的验证码是:"+"12"+"</span></br>");
        messageText.append("<span>出于安全原因，该验证码将于10分钟后失效。请勿将验证码透露给他人。</span></br>");
        message1.setContent(messageText.toString(),"text/html;charset=UTF-8");
        Transport.send(message1);


//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
//            messageHelper.setFrom("1074454337@qq.com");
//            messageHelper.setTo("1009522499@qq.com");
//            messageHelper.setCc("595313551@qq.com");
//            messageHelper.setSubject("通知");
//            String html = "<div><h1><a name=\"hello\"></a><span>Hello</span></h1><blockquote><p><span>this is a html email.</span></p></blockquote><p>&nbsp;</p><p><span>"
//                    + msg + "</span></p></div>";
//            messageHelper.setText(html, true);
//            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
