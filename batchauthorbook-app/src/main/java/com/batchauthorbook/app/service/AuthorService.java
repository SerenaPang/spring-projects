package com.batchauthorbook.app.service;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.batchauthorbook.app.model.Author;
import com.batchauthorbook.app.repo.AuthorRepository;

public class AuthorService {
	private AuthorRepository authorRepository;
	private Random random;
	private Clock clock;

	// constructor for the dependencies
	public AuthorService(AuthorRepository authorRepository, Random random, Clock clock) {
		this.authorRepository = authorRepository;
		this.random = random;
		this.clock = clock;
	}

	private List<Author> generate(int count) {
		final String[] names = { "a", "b", "c", "d" };
		final List<Author> authors = new ArrayList<>(count);

		for (int i = 0; i < count; i++) {
			Author author = new Author();
			author.setId(i);
			author.setName(names[random.nextInt(4)]);

			authors.add(author);
		}
		return authors;
	}

	public long createAuthors(int count) {
		List<Author> authors = generate(count);
		long startTime = clock.millis();
		authorRepository.saveAll(authors);
		return clock.millis() - startTime;
	}
}
