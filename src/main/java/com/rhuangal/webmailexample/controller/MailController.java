package com.rhuangal.webmailexample.controller;

import com.rhuangal.webmailexample.model.MailForm;
import com.rhuangal.webmailexample.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {

    @Autowired
    MailService mailService;

    @GetMapping("/")
    protected String showForm(MailForm mailForm) {
        return "form";
    }

    @PostMapping("/")
    @ResponseBody
    protected String process(MailForm mailForm) {
        try {
            sendEmail(mailForm);
            return "Correo enviado!";
        } catch (Exception ex) {
            return "Error enviando correo: " + ex;
        }
    }

    private void sendEmail(MailForm mailForm) {
        mailService.sendEmail(mailForm.getEmail(), mailForm.getMessage());
    }

}
