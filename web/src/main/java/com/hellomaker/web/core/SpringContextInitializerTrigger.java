package com.hellomaker.web.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("springContextInitializerTrigger")
@Slf4j
public class SpringContextInitializerTrigger implements InitializerTrigger, ApplicationListener<ContextRefreshedEvent> {

    private List<Initializer> springContextInitializer = new ArrayList<>();
    private ApplicationContext applicationContext;

    //@Async
    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        applicationContext = event.getApplicationContext();
        System.out.println("app : " + applicationContext.getDisplayName() + ";" + applicationContext.getClass() + applicationContext.getApplicationName());
//        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForAnnotation(Configuration.class);
        if(event.getApplicationContext().getParent() == null)//root application context 没有parent，他就是老大.
        {
//            applicationContext = event.getApplicationContext();
            Map<String, SpringContextInitializerConfig> springInitializerConfigMap = applicationContext.getBeansOfType(SpringContextInitializerConfig.class);
            for (Map.Entry<String, SpringContextInitializerConfig> initializerConfigEntry : springInitializerConfigMap.entrySet()) {
                //调用配置器中的方法
                initializerConfigEntry.getValue().initializerTriggerConfig(this);
                initializerConfigEntry.getValue().configWithApplicationContext(applicationContext);
                log.info("spring 触发器 初始化配置 ； " + initializerConfigEntry.toString());
            }
            log.info("spring 触发器初始化正在触发所有的初始器");
            initialize();
        }
    }

    @Override
    public void registerInitializer(Initializer initializer) {
        springContextInitializer.add(initializer);
    }

    @Override
    public void initialize() {
        if (applicationContext != null) {
            for (Initializer initializer : springContextInitializer) {
                initializer.initBySpringApplicationContext(applicationContext);
            }
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
