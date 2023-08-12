package com.hellomaker.web.view.impl;

import com.hellomaker.web.view.ViewInitializer;
import org.springframework.context.ApplicationContext;

/**
 * 适配器
 */
public abstract class ViewInitializerAdapter implements ViewInitializer {

    @Override
    public void initBySpringApplicationContext(ApplicationContext applicationContext) {

    }

}
