package com.hellomaker.web.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hellomaker.web.common.ViewConst;
import com.hellomaker.web.common.util.AESUtil;
import com.hellomaker.web.exception.instance.NotLoginException;
import com.hellomaker.web.model.dto.LoginUser;
import com.hellomaker.web.model.dto.TokenInfoDTO;
import com.hellomaker.web.service.LoginService;
import com.hellomaker.web.service.openfeign.PlatformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 *
 * @describe: 平台的权限过滤器
 *
 * @author  xianzhikun
 * @date    2023/8/1 15:56
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PlatformAuthenticationTokenFilter  extends OncePerRequestFilter {

    @Value("${platform.host}")
    private String host;
    @Value("${platform.port}")
    private Integer port;

    private final PlatformService platformService;


    @Resource
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Resource
    @Qualifier("loginService")
    @Lazy
    private LoginService loginService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (true) {
                filterChain.doFilter(request, response);
                return;
            }

            URI uri = new URI("http", null, host, port, null, null, null);
            String token = request.getHeader("I-Token");
            if (token == null) {
                //没有携带token中的key的情况。直接提示重新登录
                throw new NotLoginException();
            }
            String result = platformService.check(uri, token);
            //解密工具解密
            result = AESUtil.decrypt(result);
            JSONObject jsonObject = JSON.parseObject(result);
            Integer code = jsonObject.getInteger(ViewConst.CODE);
            String message = jsonObject.getString(ViewConst.MESSAGE);
            if (code.equals(ViewConst.OK_STATUS)) {
                TokenInfoDTO tokenInfo = JSON.toJavaObject(jsonObject.getJSONObject(ViewConst.DATA), TokenInfoDTO.class);

                //用户登录授权
                LoginUser loginUser = loginService.login(tokenInfo);

                //用户认证授权信息 存入SecurityContextHolder
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(loginUser,null, loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                //认证通过，放行
                filterChain.doFilter(request, response);
            } else {
                //用户未登录或key错误
                throw new NotLoginException(message);
            }
        } catch (Exception e) {
            log.error("PlatformAuthenticationTokenFilter 捕获错误 ： " + e.toString());
            //抛出的错误统一到全局处理器处理,返回
            ModelAndView modelAndView = resolver.resolveException(request, response, null, e);
            try {
                //渲染view
                modelAndView.getView().render(modelAndView.getModel(), request, response);
            } catch (Exception exception) {
                log.error("PlatformAuthenticationTokenFilter 捕获错误后，渲染失败 ：" + exception.toString());
            }
        }
    }
}
