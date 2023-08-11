package com.hellomaker.web.security;

import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER}) // 在方法参数上加
@Retention(RetentionPolicy.RUNTIME) // 运行时
public @interface CurrentLoginUser {
}
