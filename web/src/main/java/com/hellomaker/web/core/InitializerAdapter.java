package com.hellomaker.web.core;

import org.springframework.context.ApplicationContext;

public abstract class InitializerAdapter implements Initializer{
    @Override
    public void initBySpringApplicationContext(ApplicationContext applicationContext) {

    }

    @Override
    public void initByOther() {

    }
}
