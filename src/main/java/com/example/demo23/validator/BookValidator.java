package com.example.demo23.validator;

import com.example.demo23.thrift.Book;

public interface BookValidator {

    boolean validate(Book book);
}
