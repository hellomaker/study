package com.hellomaker.web.view.impl;


import com.hellomaker.web.view.AbstractEncryptTextView;

public abstract class AbstractBaseEncryptJSONView extends AbstractEncryptTextView {
    BaseJSONView baseJSONView = new BaseJSONView();

    @Override
    public String rawContent() {
        return baseJSONView.content();
    }

    public BaseJSONView getBaseJSONView() {
        return this.baseJSONView;
    }

    public void setBaseJSONView(BaseJSONView baseJSONView) {
        this.baseJSONView = baseJSONView;
    }
}
