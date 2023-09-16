package com.bookclub.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

public class MemBookDao implements BookDao {

	private List<Book> books;
    public MemBookDao() {
        books = new ArrayList<>();
        books.add(new Book("7000568903", "Java", "This is a Java", 50, Arrays.asList("Author-1")));
        books.add(new Book("2998768901", "SpringBoot", "This is a SpringBoot", 250, Arrays.asList("Author-1", "Author-2")));
        books.add(new Book("9871119012", "Maven", "This is a Maven", 15, Arrays.asList("Author-1")));
    }
    @Override
    public List<Book> list() {
        return this.books;
    }

    @Override
    public Book find(String key) {

        for (Book book:this.books){
            if(book.getIsbn().equals(key)){
                return book;
            }
        }
        return new Book();
    }

}
