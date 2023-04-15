package com.example.Omafourm.service.impl;

import com.example.Omafourm.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @param: MailServiceImpl
 * @package: com.example.Omafourm.service.impl
 * @className: MailServiceImpl
 * @description: Mail sender Imp
 */
@Service
public class MailServiceImpl implements MailService {
    private static final Logger logger= LoggerFactory.getLogger(MailServiceImpl.class);
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private static String from;
    @Override
    public void sendMail(String to, String subject, String content) {
        try{
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);

            mailSender.send(simpleMailMessage);
            logger.info("Send Email success");
        }catch(Exception e){
            logger.error("Send fail:",e);
        }
    }
}
