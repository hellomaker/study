package com.hellomaker.web.core;

import org.springframework.context.ApplicationContext;

public interface SpringConfigurationContextInitializerConfig extends InitializerConfig{
    void configWithConfigurationApplicationContext(ApplicationContext applicationContext);
}
