package com.hellomaker.web.view.initializer;

import com.hellomaker.web.core.Initializer;
import com.hellomaker.web.security.TextEncipher;
import com.hellomaker.web.security.exception.SecurityKeyNotConfigException;
import com.hellomaker.web.security.exception.TextEncipherNotFountException;
import com.hellomaker.web.view.impl.TextEncryptManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Map;

@Configuration
public class ViewInitializer implements Initializer {

    @Override
    public void initBySpringApplicationContext(ApplicationContext applicationContext) {
//        TextEncipher textEncipher = applicationContext.getBean(TextEncipher.class);
        Map<String, TextEncipher> beansOfType = applicationContext.getBeansOfType(TextEncipher.class);
        System.out.println(beansOfType);
        TextEncipher target = null;
        int targetOrder = Integer.MAX_VALUE;
        for (TextEncipher textEncipher : beansOfType.values()) {
            if (target == null) {
                target = textEncipher;
            }
            if (textEncipher.getClass().isAnnotationPresent(Order.class)) {
                Order order = textEncipher.getClass().getDeclaredAnnotation(Order.class);
                if (order.value() < targetOrder) {
                    target = textEncipher;
                    targetOrder = order.value();
                }
            }
        }
        if (target != null) {
            TextEncryptManager.registerTextEncipher(target);
        }
    }

    @Override
    public void initByOther() {
        System.out.println("hello this is ");
    }

    @Bean
    @Order(1000)
    TextEncipher textEncipherBean() {
        return new TextEncipher() {
            @Override
            public String decrypt(String encryptContent) throws SecurityKeyNotConfigException, TextEncipherNotFountException {
                return "decrpt" + encryptContent;
            }

            @Override
            public String encrypt(String sourceContent) throws SecurityKeyNotConfigException, TextEncipherNotFountException {
                return "encrypt" + sourceContent;
            }
        };
    }
}
