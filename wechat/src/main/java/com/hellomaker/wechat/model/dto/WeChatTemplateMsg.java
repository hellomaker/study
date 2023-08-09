package com.hellomaker.wechat.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WeChatTemplateMsg {
    /**
     * 消息
     */
    private String value;
    /**
     * 消息颜色
     */
    private String color;
 
 
    public WeChatTemplateMsg(String value) {
        this.value = value;
        this.color = "#173177";
    }
 
    public WeChatTemplateMsg(String value, String color) {
        this.value = value;
        this.color = color;
    }
}