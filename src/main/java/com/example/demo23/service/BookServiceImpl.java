package com.example.demo23.service;


import com.example.demo23.thrift.Book;
import com.example.demo23.thrift.BookService;
import com.example.demo23.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
public class BookServiceImpl implements BookService.Iface {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Map<String, Book> create(Book book) {
        String key = UUID.randomUUID().toString();
        if (bookRepository.getAll().containsKey(key)) {
            throw new IllegalArgumentException("This key has already exist!");
        }

        if (book == null) {
            throw new NullPointerException("Value can't be null");
        }
        Map<String, Book> map = new HashMap<>();
        map.put(key, book);
        bookRepository.getAll().put(key, book);
        return map;

    }

    @Override
    public Map<String, Book> getAll() {
        return bookRepository.getAll();
    }


    @Override
    public boolean remove(String key) {
        checkKey(key);
        return bookRepository.getAll().remove(key) != null;
    }

    @Override
    public Book update(String key, Book value) {
        checkKey(key);
        return bookRepository.getAll().get(key).setAuthor(value.author).setName(value.name);

    }

    private void checkKey(String key){
        if (getAll().get(key) == null) {
            throw new IllegalArgumentException("Incorrect key!");
        }
    }


}
