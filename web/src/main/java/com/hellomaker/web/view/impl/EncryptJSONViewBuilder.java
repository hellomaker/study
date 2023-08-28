package com.hellomaker.web.view.impl;

import com.hellomaker.web.security.TextEncipher;

public class EncryptJSONViewBuilder{

    public static EncryptJSONViewBuilder builder() {
        return new EncryptJSONViewBuilder();
    }

    public AbstractEncryptJSONView buildSimple(TextEncipher textEncipher) {
        return new SimpleEncryptJSONView(textEncipher);
    }

    public AbstractEncryptJSONView buildGlobalDelegate() {
        AbstractEncryptJSONView abstractBaseEncryptJSONView = new DelegateEncryptJSONView();
        return abstractBaseEncryptJSONView;
    }

    private class DelegateEncryptJSONView extends AbstractEncryptJSONView {
        @Override
        public TextEncipher getTextEncipher() {
            return TextEncryptManager.getDelegateTextEncrypt();
        }
    }

}
