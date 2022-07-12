package com.example.demo23.exception;

public class BookNameException extends RuntimeException {
    public BookNameException() {
    }

    public BookNameException(String message) {
        super(message);
    }
}
