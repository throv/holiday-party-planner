package com.ada.holiday_party_planning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public String sendMail(String to, String subject, String eventLink) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(eventLink);
            javaMailSender.send(message);
            return "Email sent successfully";

        } catch (Exception e) {
            return "Email not sent - " + e.getLocalizedMessage();
        }
    }
}
