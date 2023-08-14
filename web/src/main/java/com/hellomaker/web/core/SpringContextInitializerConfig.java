package com.hellomaker.web.core;

import org.springframework.context.ApplicationContext;


public interface SpringContextInitializerConfig extends InitializerConfig{

    void configWithApplicationContext(ApplicationContext applicationContext);

}
