package com.example.demo23.validator.impl;

import com.example.demo23.exception.BookAuthorException;
import com.example.demo23.thrift.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookAuthorValidatorTest {

    BookAuthorValidator authorValidator;

    @Autowired
    public BookAuthorValidatorTest(BookAuthorValidator authorValidator) {
        this.authorValidator = authorValidator;
    }

    @Test
    void validatorSuccessfully() {
        Book book = new Book("one", "   ", 2133);
        BookAuthorException exception = assertThrows(BookAuthorException.class, () -> authorValidator.validate(book));
        assertEquals("Author can't be null or empty!", exception.getMessage());
    }

}