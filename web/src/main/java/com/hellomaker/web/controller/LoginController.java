package com.hellomaker.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hellomaker.web.common.ViewConst;
import com.hellomaker.web.common.util.AESUtil;
import com.hellomaker.web.model.vo.UserVO;
import com.hellomaker.web.service.LoginService;
import com.hellomaker.web.service.openfeign.PlatformService;
import com.hellomaker.web.view.TextView;
import com.hellomaker.web.view.impl.RestViewBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @describe: 子系统登录登出控制器
 *
 * @author  xianzhikun
 * @date    2023/8/11 17:28
 */
@Controller
public class LoginController {

    @Value("${platform.host}")
    private String host;
    @Value("${platform.port}")
    private Integer port;

    @Resource
    PlatformService platformService;

    @Resource
    @Qualifier("loginService")
    @Lazy
    private LoginService loginService;

    /**
     *
     * @describe: 子系统自己的登录接口，
     * 使用平台时，不再提供登录
     *
     * @param userVO
     * @return  {{@link TextView}}
     * @author  xianzhikun
     * @date    2023/8/11 17:23
     */
    @PostMapping("login")
    TextView UserLogin(UserVO userVO) {
        if (loginService.login(userVO) != null) {
            //子系统登录成功
            return RestViewBuilder.builder().code(ViewConst.NO_RESULT_STATUS).message("登录失败").build();
        } else {
            return RestViewBuilder.builder().code(ViewConst.OK_STATUS).message("登录成功").build();
        }
    }

    /**
     *
     * @describe: 退出登录 注：http 体不需要发数据，
     *  退出登录成功后，源token也失效。访问请求时，将源token从http头部移除。
     * @param request
     * @return  {{@link TextView}}
     * @author  xianzhikun
     * @date    2023/8/11 16:20
     */
    @PostMapping("exit")
    TextView userLogOut(HttpServletRequest request) throws URISyntaxException {
        //移除登录信息
        URI uri = new URI("http", null, host, port, null, null, null);
        String token = request.getHeader("I-Token");
        String result = platformService.userLogout(uri, token);
        result = AESUtil.decrypt(result);
        JSONObject jsonObject = JSON.parseObject(result);
        Integer code = jsonObject.getInteger("code");
        String message = jsonObject.getString("message");
        if (code.equals(ViewConst.OK_STATUS)) {
            return RestViewBuilder.builder().code(ViewConst.OK_STATUS).message(message).build();
        } else {
            return RestViewBuilder.builder().code(ViewConst.NO_RESULT_STATUS).message(message).build();
        }
    }

}
