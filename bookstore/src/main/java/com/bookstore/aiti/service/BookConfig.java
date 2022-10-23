package com.bookstore.aiti.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bookstore.aiti.dao.BookDao;
import com.bookstore.aiti.pojo.Book;

@Configuration
public class BookConfig {

	@Bean
	public CommandLineRunner command(BookDao book) {
		return args->{
			book.save(new Book(1, "Kofi samam","horror","mensah"));
			book.save(new Book(2, "Little red ridden hood","novel","charlse"));
			book.save(new Book(3, "Java for programmer","academics","francis"));
			book.save(new Book(4, "Obeede","adventure","mensah"));
			book.save(new Book(5, "Gold Coast","adventure","francis"));
		};
	}
}
