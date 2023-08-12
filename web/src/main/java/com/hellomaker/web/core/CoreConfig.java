package com.hellomaker.web.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.Resource;

@Configuration
public class CoreConfig  implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    @Qualifier("springContextInitializerTrigger")
    SpringContextInitializerTrigger springContextInitializerTrigger;

    //@Async
    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null)//root application context 没有parent，他就是老大.
        {
            springContextInitializerTrigger.setApplicationContext(event.getApplicationContext());
            springContextInitializerTrigger.initialize();
//            applicationContext.get
        }
    }
}
