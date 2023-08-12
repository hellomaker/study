package com.hellomaker.web.security;

import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;

public abstract class TextEncipherAdapter implements TextEncipher{

    @Override
    public String decrypt(String encryptContent) throws SecurityKeyNotConfigException {
        return null;
    }

    @Override
    public String encrypt(String sourceContent) throws SecurityKeyNotConfigException {
        return null;
    }
}
