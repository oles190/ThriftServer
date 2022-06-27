package com.example.demo23.dao;

import com.example.demo23.thrift.Book;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class BookRepository {
    private  Map<String, Book> books = initMap();


    public Map<String, Book> getAll() {
        return books;
    }

    private Map<String, Book> initMap() {
        Map<String, Book> map = new HashMap<>();
        map.put(UUID.randomUUID().toString(), new Book("Book1Good", "Igor"));
        map.put(UUID.randomUUID().toString(), new Book("Book2", "Ivan"));
        map.put(UUID.randomUUID().toString(), new Book("Book3", "Taras"));
        return map;
    }


}
