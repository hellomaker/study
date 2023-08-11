package com.hellomaker.web.view;


import com.hellomaker.web.common.util.AESUtil;
import com.hellomaker.web.security.TextEncrypt;
import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 *
 * @describe: 抽象加密文本View父类
 *
 * @author  xianzhikun
 * @date    2023/8/11 0:32
 */
public abstract class AbstractEncryptTextView extends AbstractTextView {

    @Override
    public String content() {
        //加密
        try {
            return getTextEncrypt().encrypt(rawContent());
        } catch (SecurityKeyNotConfigException e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract String rawContent();

    public abstract TextEncrypt getTextEncrypt();
}
