package com.hellomaker.web.security.impl;

import com.hellomaker.web.common.util.AESUtil;
import com.hellomaker.web.security.TextEncipher;
import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Order(100)
public class AESTextEncipher implements TextEncipher {

    @Value("${security.secretKey}")
    private String secretKey;

    @Override
    public String decrypt(String encryptContent) throws SecurityKeyNotConfigException {
        if (Objects.isNull(secretKey)) {
            throw new SecurityKeyNotConfigException();
        }
        return AESUtil.decrypt(encryptContent, secretKey);
    }

    @Override
    public String encrypt(String sourceContent) throws SecurityKeyNotConfigException {
        if (Objects.isNull(secretKey)) {
            throw new SecurityKeyNotConfigException();
        }
        return AESUtil.encrypt(sourceContent, secretKey);
    }
}
