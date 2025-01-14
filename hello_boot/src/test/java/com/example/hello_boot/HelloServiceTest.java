package com.example.hello_boot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
    @Test
    void helloService(){
        HelloService helloService = new SimpleHelloService();

        String spring = helloService.sayHello("spring");

        Assertions.assertThat(spring).isEqualTo("hello spring");
    }
}
