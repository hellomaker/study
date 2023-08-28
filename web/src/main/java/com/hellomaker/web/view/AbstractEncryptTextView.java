package com.hellomaker.web.view;


import com.hellomaker.web.security.TextEncipher;
import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;
import com.hellomaker.web.security.exception.TextEncipherNotFountException;

/**
 *
 * @describe: 抽象加密文本View父类
 *
 * @author  xianzhikun
 * @date    2023/8/11 0:32
 */
public abstract class AbstractEncryptTextView extends AbstractTextView implements TextEncrypt{

    @Override
    public String content() {
        //加密
        try {
            return getTextEncipher().encrypt(rawContent());
        } catch (SecurityKeyNotConfigException | TextEncipherNotFountException e) {
            e.printStackTrace();
            return null;
        }
    }
}
