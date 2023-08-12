package com.hellomaker.web.core;

import org.springframework.context.ApplicationContext;

public interface Initializer {
    void initBySpringApplicationContext(ApplicationContext applicationContext);

    void initByOther();
}
