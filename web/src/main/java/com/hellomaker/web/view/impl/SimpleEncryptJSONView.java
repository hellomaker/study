package com.hellomaker.web.view.impl;

import com.hellomaker.web.security.TextEncipher;
import com.hellomaker.web.view.AbstractEncryptTextView;

public class SimpleEncryptJSONView extends AbstractBaseEncryptJSONView {
    private TextEncipher textEncrypt;

    public SimpleEncryptJSONView(TextEncipher textEncrypt) {
        this.textEncrypt = textEncrypt;
    }

    @Override
    public TextEncipher getTextEncipher() {
        return textEncrypt;
    }
}