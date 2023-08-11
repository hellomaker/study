package com.hellomaker.web.view.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hellomaker.web.view.AbstractTextView;

/**
 *
 * @describe: json文本view基类
 *
 * @author  xianzhikun
 * @date    2023/8/10 23:28
 */
public class BaseJSONView extends AbstractTextView {
    JSONObject jsonBody;

    @Override
    public String content() {
        return jsonBody == null ? null : JSON.toJSONString(jsonBody);
    }

    public void setJsonBody (JSONObject jsonBody) {
        this.jsonBody = jsonBody;
    }
}
