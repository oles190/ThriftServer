package com.example.demo23.exception;

import org.springframework.stereotype.Component;

@Component
public class BookAuthorException extends  RuntimeException{

    public BookAuthorException() {
    }

    public BookAuthorException(String message) {
        super(message);
    }
}
