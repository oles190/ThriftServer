package com.example.demo23;


import com.example.demo23.dao.BookRepository;
import com.example.demo23.service.BookServiceImpl;
import com.example.demo23.thrift.Book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = Demo23Application.class)
class Demo23ApplicationTests {

    @Mock
    private BookRepository bookRepository;
    private BookServiceImpl bookService;


    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void getAllSuccessfully() {
        Mockito.when(bookRepository.getAll()).thenReturn(initMap());
        Assertions.assertEquals(new ArrayList<>(bookService.getAll().values()), new ArrayList<>((initMap().values())));
        Mockito.verify(bookRepository, Mockito.times(1)).getAll();

    }


    @Test
    void createSuccessfully() {
        Map<String, Book> book = bookService.create(new Book("one", "one"));
        Book book1 = book.values().stream().findFirst().orElseThrow(() -> new RuntimeException("Value is null"));
        Assertions.assertEquals(book1, new Book("one", "one"));
    }

    @Test
    void createThrow() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> bookService.create(null));
        Assertions.assertEquals("Value can't be null", exception.getMessage());
    }


    @Test
    void removeSuccessfully() {
        Map<String, Book> removed = new LinkedHashMap<>();
        Mockito.when(bookRepository.getAll()).thenReturn(removed);
        String key = UUID.randomUUID().toString();
        removed.put(key, new Book("book1", "book1"));
        bookService.remove(key);
//        Mockito.verify(bookService,Mockito.times(1)).remove(key);

        boolean flag = bookService.getAll().containsKey(key);
        Assertions.assertFalse(flag);
    }

    @Test
    void removeThrow() {
        String key = UUID.randomUUID().toString();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bookService.remove(key));
        Assertions.assertEquals("Incorrect key!", exception.getMessage());
    }


    @Test
    void updateSuccessfully() {
        Map<String, Book> all = new LinkedHashMap<>();
        String key = UUID.randomUUID().toString();
        all.put(key, new Book("book1", "author1"));
        Mockito.when(bookRepository.getAll()).thenReturn(all);
        Book book = new Book("update", "update");
        Book updated = bookService.update(key, book);
        Assertions.assertEquals(book, updated);
    }

    @Test
    void updateThrow() {
        String key = UUID.randomUUID().toString();
        Book book = new Book("update", "update");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bookService.update(key, book));
        Assertions.assertEquals("Incorrect key!", exception.getMessage());

    }

    private Map<String, Book> initMap() {
        Map<String, Book> map = new LinkedHashMap<>();
        map.put(UUID.randomUUID().toString(), new Book("book1", "author1"));
        map.put(UUID.randomUUID().toString(), new Book("book2", "author2"));
        map.put(UUID.randomUUID().toString(), new Book("book3", "author3"));

        return map;
    }

}
