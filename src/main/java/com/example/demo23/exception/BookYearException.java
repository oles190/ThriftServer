package com.example.demo23.exception;

public class BookYearException extends  RuntimeException {

    public BookYearException() {
    }

    public BookYearException(String message) {
        super(message);
    }
}
