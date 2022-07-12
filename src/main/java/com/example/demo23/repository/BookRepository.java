package com.example.demo23.repository;

import com.example.demo23.thrift.Book;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class BookRepository {
    private Map<String, Book> books = initMap();


    public Map<String, Book> getAll() {
        return books;
    }

    private Map<String, Book> initMap() {
        Map<String, Book> map = new HashMap<>();
        map.put(UUID.randomUUID().toString(), new Book("Book1Good", "Igor", 2014));
        map.put(UUID.randomUUID().toString(), new Book("Book2", "Ivan", 2005));
        map.put(UUID.randomUUID().toString(), new Book("Book3", "Taras", 2014));
        return map;
    }


}
