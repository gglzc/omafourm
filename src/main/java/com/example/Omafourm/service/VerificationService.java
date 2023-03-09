package com.example.Omafourm.service;

import org.springframework.stereotype.Service;

@Service
public interface VerificationService {

    /**
     * 產生驗證 URL，並返回該 URL
     *
     * @param email      申請帳戶的電子郵件地址
     * @return 驗證 URL
     */
    String generateVerificationCode(String email);

    /**
     * 驗證 URL 是否有效
     *
     * @param verificationCode 驗證 code
     * @return true：驗證 URL 有效；false：驗證 URL 無效
     */
    boolean verifyCode(String verificationCode);
}
