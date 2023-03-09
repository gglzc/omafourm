package com.example.Omafourm.service.impl;

import com.example.Omafourm.service.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;


import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationServiceImpl implements VerificationService {
    private static final Logger logger = LoggerFactory.getLogger(VerificationServiceImpl.class);
    // 驗證 URL 的有效期限（單位：小時）
    private static final int EXPIRATION_TIME = 2;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String generateVerificationCode(String email) {
        // 生成一個 UUID
        String uuid = UUID.randomUUID().toString();
        // 將驗證碼存入 Redis，有效期為 2 小時
        redisTemplate.opsForValue().set( uuid, email, EXPIRATION_TIME, TimeUnit.HOURS);
        logger.info("Generated verification code for email {}: {}", email, uuid);
        return uuid;
    }

    @Override
    public boolean verifyCode(String verificationCode) {
        // 從 Redis 中取得驗證碼對應的 email
        String email = redisTemplate.opsForValue().get(verificationCode);
        if (email == null) {
            logger.error("Verification code {} is invalid or has expired", verificationCode);
            return false;
        }
        // 從 Redis 中刪除驗證碼，避免被重複使用
        redisTemplate.delete( verificationCode);
        logger.info("Verified email {} with verification code {}", email, verificationCode);
        return true;
    }
}
