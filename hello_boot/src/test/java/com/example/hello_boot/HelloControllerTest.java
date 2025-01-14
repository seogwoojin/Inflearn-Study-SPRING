package com.example.hello_boot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    void controller() throws InterruptedException {
        HelloController helloController = new HelloController(name -> name);
        String hello = helloController.Hello("spring");
        Assertions.assertThat(hello).isEqualTo("spring");
    }
}
