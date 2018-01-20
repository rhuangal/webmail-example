package com.rhuangal.webmailexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender mailSender;
    private MailBuilder mailBuilder;

    @Autowired
    public MailService(JavaMailSender mailSender, MailBuilder mailBuilder) {
        this.mailSender = mailSender;
        this.mailBuilder = mailBuilder;
    }

    public void sendEmail(String recipient, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("abcde@gmail.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Sample mail subject");
            String content = mailBuilder.build(message);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception;
        }
    }

}
