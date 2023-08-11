package com.hellomaker.web.view.impl;


import com.hellomaker.web.security.TextEncrypt;
import com.hellomaker.web.view.AbstractEncryptTextView;

public class BaseEncryptJSONView extends AbstractEncryptTextView {
    BaseJSONView baseJSONView = new BaseJSONView();

    @Override
    public String rawContent() {
        return baseJSONView.content();
    }

    @Override
    public TextEncrypt getTextEncrypt() {
        return null;
    }

    public BaseJSONView getBaseJSONView() {
        return this.baseJSONView;
    }

    public void setBaseJSONView(BaseJSONView baseJSONView) {
        this.baseJSONView = baseJSONView;
    }
}
