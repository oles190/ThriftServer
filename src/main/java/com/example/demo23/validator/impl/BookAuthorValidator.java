package com.example.demo23.validator.impl;

import com.example.demo23.exception.BookAuthorException;
import com.example.demo23.thrift.Book;
import com.example.demo23.validator.BookValidator;
import org.springframework.stereotype.Component;

@Component
public class BookAuthorValidator implements BookValidator {
    @Override
    public boolean validate(Book book) {
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new BookAuthorException("Author can't be null or empty!");
        }
        return false;
    }
}
