package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBoilerPlateApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBoilerPlateApplication.class, args);
    }
}