package com.example.hello_boot;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SimpleHelloService implements HelloService {
    @Override
    public String sayHello(String name){
        return "hello " + name;
    }
}
