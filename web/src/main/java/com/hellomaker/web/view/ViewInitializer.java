package com.hellomaker.web.view;

import org.springframework.context.ApplicationContext;

/**
 * view 的初始化器
 */
public interface ViewInitializer {
    void initBySpringApplicationContext(ApplicationContext applicationContext);

}
