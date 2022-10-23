package com.bookstore.aiti.service;

import java.util.List;

import com.bookstore.aiti.exception.BookNotFoundException;
import com.bookstore.aiti.exception.IdNotFoundException;
import com.bookstore.aiti.pojo.Book;

public interface BookServiceInt {

	boolean addBook(Book book);
	List<Book> getBookCategory(String category) throws BookNotFoundException;
	List<Book> getBookAuthor(String author) throws BookNotFoundException;
	Book getBookById(Integer id) throws IdNotFoundException;
	
}
