package com.example.demo23.exception;

import org.springframework.stereotype.Component;

@Component
public class BookException extends RuntimeException {
    public BookException() {
    }

    public BookException(String message) {
        super(message);
    }
}
