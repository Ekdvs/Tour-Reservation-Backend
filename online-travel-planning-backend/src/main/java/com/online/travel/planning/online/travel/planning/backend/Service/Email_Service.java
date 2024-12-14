package com.online.travel.planning.online.travel.planning.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class Email_Service {
    @Autowired
 private JavaMailSender mailSender; // Use JavaMailSender instead of MailSender
 public void sendWelcomeEmail(String toEmail, String subject, String htmlContent) {
try {
            // Create a MimeMessage
            MimeMessage message = mailSender.createMimeMessage();
 
            // Use MimeMessageHelper to configure the email
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // Pass 'true' for HTML content
            helper.setFrom("ceylontravelplanning@gmail.com");

            // Send the email
            mailSender.send(message);
        }
         catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email");}}
            public void sendotpcode(String toEmail, String subject, String htmlContent) {
                try {
                    // Create a MimeMessage
                    MimeMessage message = mailSender.createMimeMessage();
                
                 // Use MimeMessageHelper to configure the email
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // Pass 'true' for HTML content
            helper.setFrom("ceylontravelplanning@gmail.com");

                }
            }
 }


