package com.hellomaker.web.security;

import com.hellomaker.web.model.dto.LoginUser;
import com.hellomaker.web.service.SecurityContextService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

/**
 * @Description
 * @Author xzk
 * @Date 2023/4/19 8:54
 **/
@Component
public class AuthContextMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Resource
    SecurityContextService securityContextService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType().equals(LoginUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterType().equals(LoginUser.class)) {
            return securityContextService.nowLoginUser();
        }
        return null;
    }
}
