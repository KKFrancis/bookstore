package com.bookstore.aiti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.aiti.dao.BookDao;
import com.bookstore.aiti.pojo.Book;
import com.bookstore.aiti.service.BookServiceImp;

@RestController
public class BookController {
	
	@Autowired
	private BookServiceImp service;
	
	@Autowired
	private BookDao dao;

	@PostMapping("/create_book")
	public ResponseEntity<?> addBook(@RequestBody Book book){
		
		boolean serviceBook = service.addBook(book);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "this is to add book");
		if(!serviceBook) {
			dao.save(book);
			return ResponseEntity.ok("Book saved");
		}
		
			return new ResponseEntity<String>("Book Already exist",HttpStatus.OK);
	}
	
	@GetMapping("/author/{author}")
	public ResponseEntity<?> getAuthor(@PathVariable("author") String author){
		
		List<Book> auth = service.getBookAuthor(author);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "get the author");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(auth);
		
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<?> getCategory(@PathVariable("category") String category){
		
		List<Book> cate = service.getBookCategory(category);		
		return ResponseEntity.ok(cate);
		
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<?> findBookById(@PathVariable Integer id){
	
		Book book = service.getBookById(id);
		return ResponseEntity.ok(book);
		
	}
}
