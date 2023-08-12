package com.hellomaker.web.core;

import org.springframework.context.ApplicationContext;


public interface SpringInitializerConfig extends InitializerConfig{

    void configWithApplicationContext(ApplicationContext applicationContext);

}
