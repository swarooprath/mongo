package com.swaroopr.mongo.springinterceptor;

import org.springframework.stereotype.Component;

@Component
public class Bean {

    @Interceptor
    public void foo() {
        System.out.println("Executing method 'foo'.");
    }
}
