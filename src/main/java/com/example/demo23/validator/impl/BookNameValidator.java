package com.example.demo23.validator.impl;

import com.example.demo23.exception.BookNameException;
import com.example.demo23.thrift.Book;
import com.example.demo23.validator.BookValidator;
import org.springframework.stereotype.Component;

@Component
public class BookNameValidator implements BookValidator {
    @Override
    public boolean validate(Book book) {

        if (book.getName() == null || book.getName().trim().equals("")) {
            throw new BookNameException("Name can't be null or empty!");
        }
        return true;
    }
}
