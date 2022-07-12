package com.example.demo23.dao;


import com.example.demo23.exception.BookAuthorException;
import com.example.demo23.exception.BookException;
import com.example.demo23.repository.BookRepository;

import com.example.demo23.thrift.Book;
import com.example.demo23.validator.BookValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class BookDaoTests {


    private BookDao bookDao;
    @Mock
    private BookRepository bookRepository;
    @Autowired
    List<BookValidator> validators;


    @BeforeEach
    void setUp() {
        bookDao = new BookDao(bookRepository, validators);
    }

    @Test
    void getAllSuccessfully() {
        Mockito.when(bookDao.getAll()).thenReturn(initMap());
        Assertions.assertEquals(new ArrayList<>(bookDao.getAll().values()), new ArrayList<>((initMap().values())));
        Mockito.verify(bookRepository, Mockito.times(1)).getAll();

    }


    @Test
    void createSuccessfully() {
        Book newBook = new Book("one", "one", 1970);
        Book created = bookDao.create(newBook);
        Assertions.assertEquals(newBook, created);
    }

    @Test
    void createThrow() {
        BookException exception = assertThrows(BookException.class, () -> bookDao.create(null));
        Assertions.assertEquals("Value can't be null!", exception.getMessage());
    }


    @Test
    void removeSuccessfully() {
        Map<String, Book> removed = new LinkedHashMap<>();
        Mockito.when(bookDao.getAll()).thenReturn(removed);
        String key = UUID.randomUUID().toString();
        removed.put(key, new Book("book1", "book1", 1980));
        bookDao.remove(key);

        boolean flag = bookDao.getAll().containsKey(key);
        Assertions.assertFalse(flag);
    }

    @Test
    void removeThrow() {
        String key = UUID.randomUUID().toString();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bookDao.remove(key));
        Assertions.assertEquals("Incorrect key!", exception.getMessage());
    }


    @Test
    void updateSuccessfully() {
        Map<String, Book> all = new LinkedHashMap<>();
        String key = UUID.randomUUID().toString();
        all.put(key, new Book("book1", "author1", 1888));
        Mockito.when(bookDao.getAll()).thenReturn(all);
        Book book = new Book("update", "update", 1888);
        Book updated = bookDao.update(key, book);
        Assertions.assertEquals(book, updated);
    }

    @Test
    void updateThrow() {
        String key = UUID.randomUUID().toString();
        Book book = new Book("update", "update", 2003);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bookDao.update(key, book));
        Assertions.assertEquals("Incorrect key!", exception.getMessage());

    }


    private Map<String, Book> initMap() {
        Map<String, Book> map = new LinkedHashMap<>();
        map.put(UUID.randomUUID().toString(), new Book("book1", "author1", 1987));
        map.put(UUID.randomUUID().toString(), new Book("book2", "author2", 1998));
        map.put(UUID.randomUUID().toString(), new Book("book3", "author3", 2001));

        return map;
    }

}
