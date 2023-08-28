package com.hellomaker.web.view.impl;


import com.alibaba.fastjson.JSONObject;

/**
 *
 * @describe: json文本view基类构造器
 *
 * @author  xianzhikun
 * @date    2023/8/10 23:28
 */
public class JSONViewBuilder {

    //不允许外部构造
    private JSONViewBuilder() {
    };

    public static JSONViewBuilder builder() {
        JSONViewBuilder builder = new JSONViewBuilder();
        builder.jsonBody = new JSONObject();
        return builder;
    }

    private JSONObject jsonBody;

    /**
     *
     * @describe: 添加值
     *
     * @param key
     * @param value
     * @return  {}
     * @author  xianzhikun
     * @date    2023/8/10 22:36
     */
    public JSONViewBuilder append(String key, Object value) {
        jsonBody.put(key, value);
        return this;
    }

    /**
     *
     * @describe: 构造BaseJSONView 对象
     *
     * @param
     * @return  {{@link JSONView}}
     * @author  xianzhikun
     * @date    2023/8/10 23:02
     */
    public JSONView build() {
        JSONView JSONView = new JSONView();
        JSONView.setJsonBody(jsonBody);
        return JSONView;
    }
}
