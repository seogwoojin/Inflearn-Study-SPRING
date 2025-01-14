package com.example.hello_boot;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping()
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        System.out.println(helloService.getClass().getName());
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String Hello( String name) throws InterruptedException {
        System.out.println(helloService.getClass().getName());
        Thread.sleep(1000);
        return helloService.sayHello(Objects.requireNonNull(name));
    }
    public static class Json{
        private int num;
        private String name;
        public Json(){
            num=5;
            name="woojin";
        }

    }
}
