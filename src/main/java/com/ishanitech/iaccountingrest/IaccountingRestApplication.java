package com.ishanitech.iaccountingrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

@SpringBootApplication
public class IaccountingRestApplication  {

	public static void main(String[] args) {
		SpringApplication.run(IaccountingRestApplication.class, args);
	}

	@Bean
    public FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean() {
        FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean  = new FreeMarkerConfigurationFactoryBean();
        freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("/templates/");
        return freeMarkerConfigurationFactoryBean;
    }
	@Bean
	public JavaMailSender blMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("mail.ishanitech.com");
		sender.setUsername("test@ishanitech.com");
		sender.setPassword("Password1*#");
		sender.setPort(587);
		sender.setDefaultEncoding("UTF-8");
		sender.setProtocol("smtp");
		Properties javaMailProps = new Properties();
		javaMailProps.setProperty("mail.smtp.starttls.enable", "true");
		javaMailProps.setProperty("mail.smtp.auth", "true");
		javaMailProps.setProperty("mail.smtp.timeout", "25000");
		sender.setJavaMailProperties(javaMailProps);
		return sender;
	}
}
