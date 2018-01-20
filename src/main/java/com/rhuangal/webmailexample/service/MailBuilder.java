package com.rhuangal.webmailexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        //Nombre del archivo HTML
        return templateEngine.process("mail", context);
    }
}
