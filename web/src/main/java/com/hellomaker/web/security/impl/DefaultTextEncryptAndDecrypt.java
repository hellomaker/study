package com.hellomaker.web.security.impl;

import com.hellomaker.web.common.util.AESUtil;
import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DefaultTextEncryptAndDecrypt implements TextEncryptAndDecrypt{

    @Value("hellomaker.security.secretKey")
    String secretKey;

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
