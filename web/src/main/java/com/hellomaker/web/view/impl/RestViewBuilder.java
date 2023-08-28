package com.hellomaker.web.view.impl;

import com.hellomaker.web.common.ViewConst;
import com.hellomaker.web.security.TextEncipher;

import java.util.Objects;

/**
 *
 * @describe: Rest风格json view 构造器
 * 适配器模式的组合方式
 *
 * @author  xianzhikun
 * @date    2023/8/10 23:29
 */
public class RestViewBuilder{
    protected RestViewBuilder(){};

    public static RestViewBuilder builder() {
        RestViewBuilder builder = new RestViewBuilder();
        builder.builder = JSONViewBuilder.builder();
        return builder;
    }
    //适配器模式，BaseJSONViewBuilder属性
    JSONViewBuilder builder;
    //
    EncryptJSONViewBuilder encryptBuilder;

    public RestViewBuilder code(int code) {
        builder.append(ViewConst.CODE, code);
        return this;
    }

    public RestViewBuilder message(String message) {
        builder.append(ViewConst.MESSAGE, message);
        return this;
    }

    public RestViewBuilder data(Object data) {
        builder.append(ViewConst.DATA, data);
        return this;
    }

    public RestViewBuilder append(String key, Object value) {
        builder.append(key, value);
        return this;
    }

    public JSONViewBuilder baseBuilder() {
        return builder;
    }

    public JSONView build() {
        JSONView JSONView = null;
        if (!Objects.isNull(builder)) {
            JSONView = builder.build();
        }
        if (!Objects.isNull(encryptBuilder)) {
            AbstractEncryptJSONView encryptJSONView = encryptBuilder.buildGlobalDelegate();
            encryptJSONView.setBaseJSONView(JSONView);
            return encryptJSONView;
        }
        return JSONView;
    }

    public RestViewBuilder encrypt() {
        this.encryptBuilder = EncryptJSONViewBuilder.builder();
        return this;
    }

    public AbstractEncryptJSONView buildEncrypt(TextEncipher textEncipher) {
        JSONView JSONView = build();
        //加密的jsonView
        AbstractEncryptJSONView abstractBaseEncryptJSONView = new SimpleEncryptJSONView(textEncipher);
        abstractBaseEncryptJSONView.setBaseJSONView(JSONView);
        return abstractBaseEncryptJSONView;
    }
}
