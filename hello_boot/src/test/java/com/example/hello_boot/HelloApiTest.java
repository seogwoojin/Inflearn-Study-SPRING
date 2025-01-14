package com.example.hello_boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloApiTest {
    @Test
    public void helloApi(){
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> response = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");
        assertThat(response.getBody()).isEqualTo("hello Spring");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE));

    }
}
