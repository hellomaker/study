package com.hellomaker.web.view.impl;

import com.hellomaker.web.common.ViewConst;

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
        builder.builder = BaseJSONViewBuilder.builder();
        return builder;
    }
    //适配器模式，BaseJSONViewBuilder属性
    BaseJSONViewBuilder builder;

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

    public BaseJSONViewBuilder baseBuilder() {
        return builder;
    }

    public BaseJSONView build() {
        return builder.build();
    }

    public BaseEncryptJSONView buildEncrypt() {
        BaseJSONView baseJSONView = build();
        //加密的jsonView
        BaseEncryptJSONView baseEncryptJSONView = new BaseEncryptJSONView();
        baseEncryptJSONView.setBaseJSONView(baseJSONView);
        return baseEncryptJSONView;
    }
}
