package com.hellomaker.web.security;

import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;
import com.hellomaker.web.security.exception.TextEncipherNotFountException;

/**
 * 加解密器
 */
public interface TextEncipher {

    String decrypt(String encryptContent) throws SecurityKeyNotConfigException, TextEncipherNotFountException;

    String encrypt(String sourceContent) throws SecurityKeyNotConfigException, TextEncipherNotFountException;

}
