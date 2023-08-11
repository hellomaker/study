package com.hellomaker.web.view.impl;


import com.alibaba.fastjson.JSONObject;

/**
 *
 * @describe: json文本view基类构造器
 *
 * @author  xianzhikun
 * @date    2023/8/10 23:28
 */
public class BaseJSONViewBuilder {

    //不允许外部构造
    private BaseJSONViewBuilder () {
    };

    public static BaseJSONViewBuilder builder() {
        BaseJSONViewBuilder builder = new BaseJSONViewBuilder();
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
    public BaseJSONViewBuilder append(String key, Object value) {
        jsonBody.put(key, value);
        return this;
    }

    /**
     *
     * @describe: 构造BaseJSONView 对象
     *
     * @param
     * @return  {{@link BaseJSONView}}
     * @author  xianzhikun
     * @date    2023/8/10 23:02
     */
    public BaseJSONView build() {
        BaseJSONView baseJSONView = new BaseJSONView();
        baseJSONView.setJsonBody(jsonBody);
        return baseJSONView;
    }
}
