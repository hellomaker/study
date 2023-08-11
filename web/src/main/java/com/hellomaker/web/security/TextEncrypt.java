package com.hellomaker.web.security;

import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;

/**
 *
 * @describe: 加密内容
 *
 * @author  xianzhikun
 * @date    2023/8/10 23:45
 */
public interface TextEncrypt {

    String encrypt(String sourceContent) throws SecurityKeyNotConfigException;

}
