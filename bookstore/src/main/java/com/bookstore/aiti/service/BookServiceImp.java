package com.bookstore.aiti.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.bookstore.aiti.dao.BookDao;
import com.bookstore.aiti.exception.BookNotFoundException;
import com.bookstore.aiti.exception.IdNotFoundException;
import com.bookstore.aiti.pojo.Book;

@Service
public class BookServiceImp implements BookServiceInt {

	@Autowired
	private BookDao dao;
	
	@Override
	public boolean addBook(Book book) {
		
		Book add  = new Book();
		add.setTitle(book.getTitle());
		
		Example<Book> bookExamp = Example.of(add);
		Optional<Book> option = dao.findOne(bookExamp);
		if(option.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public List<Book> getBookCategory(String category) {
		
		return dao.findAll()
				.stream()
				.filter(book -> book.getCategory().equals(category))
				.collect(Collectors.toList());
	}

	@Override
	public List<Book> getBookAuthor(String author) {
		
		List<Book> auth= dao.findAll()
						.stream()
						.filter(book -> book.getAuthor().equals(author))
						.collect(Collectors.toList());
		if(auth.isEmpty()) {
			throw new BookNotFoundException("Book not found");
		}
		return auth;
	}

	@Override
	public Book getBookById(Integer id) {
		return dao.findById(id)
				.stream()
				.filter(book -> book.getBookId() == id)
				.findAny()
				.orElseThrow(()-> new IdNotFoundException("Pls the id not found"));
	}

}
