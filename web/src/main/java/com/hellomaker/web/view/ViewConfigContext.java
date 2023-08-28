package com.hellomaker.web.view;

import com.hellomaker.web.core.Initializer;
import com.hellomaker.web.core.InitializerTrigger;
import com.hellomaker.web.core.SpringContextInitializerConfigAdapter;
import com.hellomaker.web.security.TextEncipher;
import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;
import com.hellomaker.web.security.exception.TextEncipherNotFountException;
import com.hellomaker.web.view.initializer.ViewInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ViewConfigContext extends SpringContextInitializerConfigAdapter {

    public ViewConfigContext() {
        System.out.println("创建 ViewConfigContext");
    }

    @Resource
    ViewInitializer viewInitializer;

    @Override
    public void initializerTriggerConfig(InitializerTrigger initializerTrigger) {
        initializerTrigger.registerInitializer(viewInitializer);

        System.out.println("注册初始化器");
    }

    TextEncipher textEncipher;

    @Override
    public void configWithApplicationContext(ApplicationContext applicationContext) {
        System.out.println("配置applicaiton ");
    }
}
