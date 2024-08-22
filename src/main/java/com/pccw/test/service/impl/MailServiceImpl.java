package com.pccw.test.service.impl;

import com.pccw.test.service.MailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
public class MailServiceImpl implements MailService {

    public static String subject = "Welcome Mail";
    public static String content = "Welcome, ";
    Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    public void sendMail(String email, String nickName) throws Exception {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // for qq mail
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.port", "587");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // need real userName and password
                return new PasswordAuthentication("", "");
            }
        };

        Session session = Session.getInstance(props, auth);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("3166195626@qq.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(subject);
        message.setText(content + nickName);

        Transport.send(message);

    }


}
