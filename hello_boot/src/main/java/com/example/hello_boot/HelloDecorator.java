package com.example.hello_boot;

import org.springframework.stereotype.Component;

@Component
public class HelloDecorator implements HelloService{
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*"+ helloService.sayHello(name) +"*";
    }
}
