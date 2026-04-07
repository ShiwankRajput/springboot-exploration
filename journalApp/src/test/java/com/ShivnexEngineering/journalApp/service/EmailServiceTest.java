package com.ShivnexEngineering.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail(){
        emailService.sendEmail(
                    "//add_email_toSend_messages",
                    "Testing Java Mail Sender",
                    "Hey There!, Testing Java Mail Sender."
                );
    }

}
