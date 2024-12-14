package com.online.travel.planning.online.travel.planning.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Email_Service {
    @Autowired
 private JavaMailSender mailSender; // Use JavaMailSender instead of MailSender
 public void sendWelcomeEmail(String toEmail, String subject, String htmlContent) {


}
