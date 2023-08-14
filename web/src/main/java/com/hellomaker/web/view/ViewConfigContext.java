package com.hellomaker.web.view;

import com.hellomaker.web.core.Initializer;
import com.hellomaker.web.core.InitializerTrigger;
import com.hellomaker.web.core.SpringContextInitializerConfigAdapter;
import com.hellomaker.web.security.TextEncipher;
import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;
import com.hellomaker.web.security.exception.TextEncipherNotFountException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewConfigContext extends SpringContextInitializerConfigAdapter {

    public ViewConfigContext() {
        System.out.println("创建 ViewConfigContext");
    }

    @Bean
    TextEncipher textEncipher() {
        System.out.println("创建 text encipher");
        return new TextEncipher() {
            @Override
            public String decrypt(String encryptContent) throws SecurityKeyNotConfigException, TextEncipherNotFountException {
                return null;
            }

            @Override
            public String encrypt(String sourceContent) throws SecurityKeyNotConfigException, TextEncipherNotFountException {
                return null;
            }
        };
    }

    @Override
    public void initializerTriggerConfig(InitializerTrigger initializerTrigger) {
        initializerTrigger.registerInitializer(new Initializer() {
            @Override
            public void initBySpringApplicationContext(ApplicationContext applicationContext) {

            }

            @Override
            public void initByOther() {

            }
        });
    }

    TextEncipher textEncipher;

    @Override
    public void configWithApplicationContext(ApplicationContext applicationContext) {
        super.configWithApplicationContext(applicationContext);
    }
}
