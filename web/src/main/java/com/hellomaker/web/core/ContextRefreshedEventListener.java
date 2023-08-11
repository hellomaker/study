package com.hellomaker.web.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.net.URISyntaxException;

@Component
@Slf4j
public class ContextRefreshedEventListener{ //implements ApplicationListener<ContextRefreshedEvent> {

    //@Async
    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) throws URISyntaxException {
        if(event.getApplicationContext().getParent() == null)//root application context 没有parent，他就是老大.
        {
            ApplicationContext applicationContext = event.getApplicationContext();

//            applicationContext.get
        }
    }

}
