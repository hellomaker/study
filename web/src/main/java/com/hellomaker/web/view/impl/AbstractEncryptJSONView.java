package com.hellomaker.web.view.impl;


import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;
import com.hellomaker.web.security.exception.TextEncipherNotFountException;
import com.hellomaker.web.view.TextEncrypt;

public abstract class AbstractEncryptJSONView extends JSONView implements TextEncrypt {
    JSONView JSONView = new JSONView();

    @Override
    public String rawContent() {
        return JSONView.content();
    }

    public JSONView getBaseJSONView() {
        return this.JSONView;
    }

    public void setBaseJSONView(JSONView JSONView) {
        this.JSONView = JSONView;
    }

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
