package com.hellomaker.web.security;

import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;

/**
 * 解密器
 */
public interface TextDecrypt {
    String decrypt(String encryptContent) throws SecurityKeyNotConfigException;
}
