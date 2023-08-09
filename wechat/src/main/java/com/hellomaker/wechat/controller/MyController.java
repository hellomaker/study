package com.hellomaker.wechat.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.hellomaker.wechat.model.dto.WeChatTemplateMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class MyController {
 

 
    // appId
    private static final String appId = "wxc8xxxxcxxxxxx";
 
    // appIdSecret
    private static final String appIdSecret = "60b429xxxxxxxxxxxxxxx";
 
    //1.先查询code
    @RequestMapping("/getCode")
    public String getCode() {
        // 官方地址
        String urlFir = "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=";
        // 微信申请的域名(提前准备)
        String domain = "http://pay.xxx.cn/wxpay";
        // 自定义跳转方法
        String redirectMethod = "/weixinoauth";
        // 地址进行encode转译 (未转译的地址是：http://pay.xxx.cn/wxpay/weixinoauth)
        // 转译后的地址是: http%3A%2F%2Fpay.xxx.cn%2Fwxpay%2Fweixinoauth
        String encoderUrl = getURLEncoderString(domain + redirectMethod);
        log.info(urlFir +appId + "&redirect_uri=" + encoderUrl +"&response_type=code&scope=snsapi_base" + "&state=STATE" + "#wechat_redirect");
        return urlFir + appId + "&redirect_uri=" + encoderUrl +"&response_type=code&scope=snsapi_base" + "&state=STATE" + "#wechat_redirect";
    }
 
    /**
     * 编码
     * @param str
     * @return
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
 
    //2.根据code获取openId
    @GetMapping("/wxpay/weixinoauth")
    public void weixinOauth(@RequestParam String code, @RequestParam String state) throws Exception {
        log.info("获取code:{}",code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + appId + "&secret=" + appIdSecret + "&code=" + code + "&grant_type=authorization_code";
        Map<String, Object> paramMap = null;
        String res = HttpUtil.get(url, paramMap);
        String openid = JSONObject.parseObject(res).getString("openid");
        log.info("根据code查询得到openId:{}",openid);
 
    }

    @GetMapping("/getToken")
    public void getAccessToken() throws Exception{
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId +"&secret=" + appIdSecret;
        String res = HttpUtil.get(url);
        JSONObject jsonObject = JSONObject.parseObject(res);
        String accessToken = jsonObject.getString("access_token");
        log.info("accessToken：{}", accessToken);
    }

    @GetMapping("/sendMessage")
    public  String sendMessage() {
        // 模板参数
        Map<String, WeChatTemplateMsg> sendMag = new HashMap<String, WeChatTemplateMsg>();

        // openId代表一个唯一微信用户，即微信消息的接收人
        String openId = "oNB9p1BpVJEquxxxxxxxxx";
        // 公众号的模板id(也有相应的接口可以查询到)
        String templateId = "B0YStqTYdjHhY9Da9Sy2NM7xxxxxxxxxxx";
        // 微信的基础accessToken
        String accessToken = "57_LubK-8NKQc6C7jsLMxvdHaI0ju4x3-HPWEFhh7GKkw9fKbWhuxxoZyX4GaVIn6y4yO7RKfSlCyHdedKJlHUMZkd8457nKm0TOoaVkbzK1HCZ4g4gZdrmAGBylGBOZu9yxxxxxxxxxxxxxxxx";
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;

        /**
         *  其他模板可以从模板库中自己添加
         * 模板ID
         * B0YStqTYdjHhY9Da9Sy2NM7HXxxxxxxxxxxxxxx
         * 开发者调用模板消息接口时需提供模板ID
         * 标题
         * 产品兑付成功提醒
         * 行业
         * 金融业 - 证券|基金|理财|信托
         * 详细内容
         * {{first.DATA}}
         * 产品名称：{{keyword1.DATA}}
         * 当期兑付本金：{{keyword2.DATA}}
         * 当期兑付利息：{{keyword3.DATA}}
         * 已兑付期数：{{keyword4.DATA}}
         * 兑付日期：{{keyword5.DATA}}
         * {{remark.DATA}}
         */
        sendMag.put("first", new WeChatTemplateMsg("f123"));
        sendMag.put("keyword1", new WeChatTemplateMsg("111"));
        sendMag.put("keyword2", new WeChatTemplateMsg("222"));
        sendMag.put("keyword3", new WeChatTemplateMsg("333"));
        sendMag.put("keyword4", new WeChatTemplateMsg("444"));
        sendMag.put("remark", new WeChatTemplateMsg("r555"));
        RestTemplate restTemplate = new RestTemplate();
        //拼接base参数
        Map<String, Object> sendBody = new HashMap<>();
        sendBody.put("touser", openId);               // openId
        sendBody.put("url", "www.baidu.com");         // 点击模板信息跳转地址
        sendBody.put("topcolor", "#FF0000");          // 顶色
        sendBody.put("data", sendMag);                   // 模板参数
        sendBody.put("template_id", templateId);      // 模板Id
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url, sendBody, String.class);
        log.info("结果是: {}",forEntity.getBody());
        JSONObject jsonObject = JSONObject.parseObject(forEntity.getBody());
        // 0
        String messageCode = jsonObject.getString("errcode");
        // 2431260672639467520
        String msgId = jsonObject.getString("msgid");
        System.out.println("messageCode : " + messageCode + ", msgId: " +msgId);
        return forEntity.getBody();
    }
}