package com.example.demo23.dao;

import com.example.demo23.exception.BookException;
import com.example.demo23.repository.BookRepository;
import com.example.demo23.thrift.Book;
import com.example.demo23.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookDao {

    private final BookRepository bookRepository;
    private final List<BookValidator> validators;

    @Autowired
    public BookDao(BookRepository bookRepository, List<BookValidator> validators) {
        this.bookRepository = bookRepository;
        this.validators = validators;
    }

    public Map<String, Book> getAll() {
        return bookRepository.getAll();
    }

    public Book create(Book book) {

        if (book == null) {
            throw new BookException("Value can't be null!");
        }

        for (BookValidator bookValidator : validators) {
            bookValidator.validate(book);
        }

        String key = UUID.randomUUID().toString();
        if (bookRepository.getAll().containsKey(key)) {
            throw new BookException("This key has already exist!");
        }


        bookRepository.getAll().put(key, book);
        return  book;
    }


    public boolean remove(String key) {
        checkKey(key);
        return bookRepository.getAll().remove(key) != null;
    }


    public Book update(String key, Book value) {
        checkKey(key);
        return bookRepository.getAll().get(key).setAuthor(value.author).setName(value.name).setYear(value.year);

    }

    private void checkKey(String key) {
        if (getAll().get(key) == null) {
            throw new IllegalArgumentException("Incorrect key!");
        }
    }
}