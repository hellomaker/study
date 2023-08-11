package com.hellomaker.web.exception;

import com.hellomaker.web.common.ViewConst;
import com.hellomaker.web.exception.instance.NotLoginException;
import com.hellomaker.web.view.TextView;
import com.hellomaker.web.view.impl.RestViewBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @describe: 抛出的异常全部需要处理
 * 注：系统所有的异常均在此处处理，
 * 包含 PlatformAuthenticationTokenFilter后的所有filter
 * Aspect, Controller, Spring security 的鉴权
 * @author  xianzhikun
 * @date    2023/8/11 14:08
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AccessDeniedException.class)
    TextView accessDeniedExceptionHandler(AccessDeniedException accessDeniedException) {
        return RestViewBuilder
                .builder()
                .code(ViewConst.FORBIDDEN_STATUS)
                .message("没有权限")
                .build();
    }

    @ExceptionHandler(value = AuthenticationException.class)
    TextView authenticationExceptionHandler(AuthenticationException authenticationException) {
        return RestViewBuilder
                .builder()
                .code(ViewConst.UNAUTHORIZED_STATUS)
                .message("用户未认证")
                .build();
    }

    @ExceptionHandler(value = NotLoginException.class)
    TextView notLoginExceptionHandler(NotLoginException notLoginException) {
        return RestViewBuilder
                .builder()
                .code(ViewConst.UNAUTHORIZED_STATUS)
                .message("用户未登录")
                .build();
    }


    /**
     *
     * @describe: 处理所有其他没有设置处理器的exception
     *
     * @param e
     * @return  {{@link TextView}}
     * @author  xianzhikun
     * @date    2023/8/11 14:09
     */
    @ExceptionHandler(value = Exception.class)
    TextView exceptionHandler(Exception e) {
        return RestViewBuilder
                .builder()
                .code(ViewConst.INNER_ERROR_STATUS)
                .message("出现错误" + e.getMessage())
                .build();
    }


}
