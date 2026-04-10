package com.ShivnexEngineering.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testSendEmail(){
//        emailService.sendEmail(
//                    "//add_email_toSend_messages",
//                    "Testing Java Mail Sender",
//                    "Hey There!, Testing Java Mail Sender."
//                );

        redisTemplate.opsForValue().set("email", "shiwank@gmail.com");
        Object salary = redisTemplate.opsForValue().get("salary");
        int a = 1;

    }

//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Test
//    void testRedis(){
//        redisTemplate.opsForValue().set("email", "shiwank@gmail.com");
//        Object email = redisTemplate.opsForValue().get("email");
//        int a = 1;
//    }

}
