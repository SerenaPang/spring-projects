package com.batchauthorbook.app.service;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.batchauthorbook.app.model.Book;
import com.batchauthorbook.app.repo.BookRepository;

public class BookService {
	
	private BookRepository bookRepository;
	private Random random;
	private Clock clock;

	// constructor for the dependencies
	public BookService(BookRepository bookRepository, Random random, Clock clock) {
		this.bookRepository = bookRepository;
		this.random = random;
		this.clock = clock;
	}

	private List<Book> generate(int count) {
		final String[] names = { "a", "b", "c", "d" };
		final List<Book> books = new ArrayList<>(count);

		for (int i = 0; i < count; i++) {
			Book book = new Book();
			book.setId(i);
			book.setName(names[random.nextInt(4)]);
			book.setIsbn("");
			books.add(book);
		}
		return books;
	}
	public long createBooks(int count) {
		List<Book> books = generate(count);
		long startTime = clock.millis();
		bookRepository.saveAll(books);
		return clock.millis() - startTime;
	}

}
