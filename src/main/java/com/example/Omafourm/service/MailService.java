package com.example.Omafourm.service;

import com.example.Omafourm.entity.User;
import org.springframework.stereotype.Service;

/**
 * @param: MailService
 * @package: com.example.Omafourm.service
 * @className: MailService
 * @description: SendEmail
 * @return:
 */
@Service
public interface MailService {
    void sendMail(String to, String subject, String content);
}
