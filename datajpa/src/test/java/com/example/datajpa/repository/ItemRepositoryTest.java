package com.example.datajpa.repository;

import com.example.datajpa.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired ItemRepository itemRepository;
    @Test
    public void item(){
        Item item = new Item("123");
        itemRepository.save(item);
    }
}