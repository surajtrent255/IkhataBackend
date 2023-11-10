package com.ishanitech.iaccountingrest.service;


import com.ishanitech.iaccountingrest.dto.EmailDTO;
import freemarker.core.ParseException;
import freemarker.template.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;

    private final Configuration freeMarkerConfiguration;
    
    public EmailService(JavaMailSender javaMailSender, Configuration freeMarkerConfiguration){
        this.javaMailSender = javaMailSender;
        this.freeMarkerConfiguration = freeMarkerConfiguration;
    }
    public void sendAccountActivationEmail(EmailDTO email, String reset) throws MessagingException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        try {
            Template template;
            if (reset.equalsIgnoreCase("normalUserRegistrationMsg")){
                template = freeMarkerConfiguration.getTemplate("normalUserRegestrationEmail.ftl");
            } else if(reset.equalsIgnoreCase("pw")) {
                template = freeMarkerConfiguration.getTemplate("password-reset-email.ftl");
            }else {
                template = freeMarkerConfiguration.getTemplate("normalUserRegestrationEmail.ftl");
            }

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, email.getModel());
            messageHelper.setTo(InternetAddress.parse(email.getTo()));
            messageHelper.setFrom(email.getFrom());
            messageHelper.setText(html, true);
            messageHelper.setSubject(email.getSubject());
//            messageHelper.addInline("logo.png", new ClassPathResource("logo.png"));
            log.info("EMAIL IS BEING SENDING. PLEASE WAIT ...");
            javaMailSender.send(mimeMessage);
            log.info("EMAIL HAS BEEN SENT. CHECK666");

        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}