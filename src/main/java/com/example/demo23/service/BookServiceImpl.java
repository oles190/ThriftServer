package com.example.demo23.service;


import com.example.demo23.dao.BookDao;
import com.example.demo23.thrift.Book;
import com.example.demo23.thrift.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@AllArgsConstructor

public class BookServiceImpl implements BookService.Iface {

    private  BookDao bookDao;


    @Override
    public Book create(Book book) {
        return bookDao.create(book);
    }

    @Override
    public Map<String, Book> getAll() {
        return bookDao.getAll();
    }


    @Override
    public boolean remove(String key) {
        return bookDao.remove(key);
    }


    @Override
    public Book update(String key, Book value) {
        return bookDao.update(key, value);
    }

}
