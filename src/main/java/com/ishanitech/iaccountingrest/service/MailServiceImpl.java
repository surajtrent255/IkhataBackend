package com.ishanitech.iaccountingrest.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private final String HOST = "mail.ishanitech.com";
    private final String EMAIL = "test@ishanitech.com";
    private final String PASSWORD = "Password1*#";
    private final String PORT = "587";
    private final String AUTH = "true";
    private final String ENABLE_TLS = "true";
    private final String SUBJECT = "Forgot-Password ";

    @Override
    public void sendEmail(String msg, String to) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", AUTH);
        props.put("mail.smtp.starttls.enable", ENABLE_TLS);
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.ssl.trust", HOST);
        props.put("mail.mime.charset", "UTF-8");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(SUBJECT,"UTF-8");
            message.setText(msg, "UTF-8","html" );

            /* Transport class is used to deliver the message to the recipients */
            Transport.send(message);
        }
        catch(Exception e) {
            log.error("Error: {}", e.getLocalizedMessage());
        }
    }
    
}
