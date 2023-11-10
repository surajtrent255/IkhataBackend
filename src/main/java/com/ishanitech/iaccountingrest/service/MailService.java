package com.ishanitech.iaccountingrest.service;

import org.springframework.mail.MailSender;

public interface MailService {
    void sendEmail(String message, String email);

}
