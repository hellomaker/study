package com.hellomaker.web.view.context;

import com.hellomaker.web.security.TextEncipher;
import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;
import com.hellomaker.web.security.exception.TextEncipherNotFountException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DelegateTextEncipher implements TextEncipher {
    TextEncipher defaultTextEncipher;
    TextEncipher injectTextEncipher;

    public DelegateTextEncipher(TextEncipher defaultTextEncipher, TextEncipher injectTextEncipher) {
        this.defaultTextEncipher = defaultTextEncipher;
        this.injectTextEncipher = injectTextEncipher;
    }

    public DelegateTextEncipher(TextEncipher defaultTextEncipher) {
        this.defaultTextEncipher = defaultTextEncipher;
    }

    public DelegateTextEncipher() {

    }

    public void setInjectTextEncipher(TextEncipher injectTextEncipher) {
        this.injectTextEncipher = injectTextEncipher;
    }

    public void setDefaultTextEncipher(TextEncipher defaultTextEncipher) {
        this.defaultTextEncipher = defaultTextEncipher;
    }

    TextEncipher getDelegateEncipher() throws TextEncipherNotFountException {
        if (!Objects.isNull(injectTextEncipher)) {
            return injectTextEncipher;
        }
        if (Objects.isNull(defaultTextEncipher)) {
            return defaultTextEncipher;
        }
        throw new TextEncipherNotFountException();
    }

    @Override
    public String decrypt(String encryptContent) throws SecurityKeyNotConfigException, TextEncipherNotFountException {
        return getDelegateEncipher().decrypt(encryptContent);
    }

    @Override
    public String encrypt(String sourceContent) throws SecurityKeyNotConfigException, TextEncipherNotFountException {
        return getDelegateEncipher().encrypt(sourceContent);
    }
}
