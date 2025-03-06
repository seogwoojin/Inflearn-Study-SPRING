package com.example.autoconfigstart.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class DbConfigTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    TransactionManager transactionManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void checkBean(){
        log.info("data Source = {}", dataSource);
        log.info("tm = {}", transactionManager);
        log.info("jt = {}", jdbcTemplate);
    }

}