package com.hellomaker.web.view.impl;

import com.hellomaker.web.security.TextEncipher;

public class SimpleEncryptJSONView extends AbstractEncryptJSONView {
    private TextEncipher textEncrypt;

    public SimpleEncryptJSONView(TextEncipher textEncrypt) {
        this.textEncrypt = textEncrypt;
    }

    @Override
    public TextEncipher getTextEncipher() {
        return textEncrypt;
    }
}