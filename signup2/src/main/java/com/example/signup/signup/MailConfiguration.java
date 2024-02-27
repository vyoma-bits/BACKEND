package com.example.signup.signup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("your-email@gmail.com");
        mailSender.setPassword("your-email-password");

        // Additional properties (if needed)
        // mailSender.getJavaMailProperties().put("mail.smtp.auth", "true");
        // mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}
