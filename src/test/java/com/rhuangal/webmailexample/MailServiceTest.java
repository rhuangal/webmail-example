package com.rhuangal.webmailexample;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.rhuangal.webmailexample.service.MailBuilder;
import com.rhuangal.webmailexample.service.MailService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { MailService.class , JavaMailSenderImpl.class, MailBuilder.class, TemplateEngine.class})
@EnableConfigurationProperties
public class MailServiceTest {

    private GreenMail smtpServer;

    @Autowired
    private MailService mailService;

    @Before
    public void setUp() throws Exception {
        smtpServer = new GreenMail(new ServerSetup(25, null, ServerSetup.PROTOCOL_SMTP));
        smtpServer.setUser("demouser", "demopwd");
        smtpServer.start();
    }

    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }

    @Test
    public void shouldSendMail() throws Exception {
        //given
        String recipient = "abcde@hotmail.com";
        String message = "Test message content";
        //when
        mailService.sendEmail(recipient, message);
        //then
        assertReceivedMessageContains(message);
    }

    private void assertReceivedMessageContains(String expected) throws IOException, MessagingException {
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
        Assert.assertEquals(1, receivedMessages.length);
        smtpServer.stop();
    }

}
