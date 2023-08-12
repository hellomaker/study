package com.hellomaker.web.core;

public interface InitializerTrigger {

    void registerInitializer(Initializer initializer);

    void initialize();
}
