package com.example.demo23.exception.handler;

import com.example.demo23.exception.BookAuthorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class BookHandler {

    @ExceptionHandler(BookAuthorException.class)
    ResponseEntity<ErrorBook> responseEntity() {

        ErrorBook errorBook = new ErrorBook(
                "Error Author",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorBook, HttpStatus.NOT_FOUND);

    }
}
