package com.example.demo23.validator.impl;

import com.example.demo23.exception.BookYearException;
import com.example.demo23.thrift.Book;
import com.example.demo23.validator.BookValidator;
import org.springframework.stereotype.Component;

@Component
public class BookYearValidator implements BookValidator {
    @Override
    public boolean validate(Book book) {
        if (book.getYear() <= 0) {
            throw new BookYearException("Year can't be 0 or less than 0!");
        }
        return true;
    }
}
