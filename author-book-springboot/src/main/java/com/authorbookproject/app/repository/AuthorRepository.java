package com.authorbookproject.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.authorbookproject.app.model.Author;

@Repository
public class AuthorRepository {
	List<Author> authors = new ArrayList<>();

	public AuthorRepository() {
		System.out.println("AuthorRepository.AuthorRepository()");
	}

	public void saveAuthor(Author author) {
		System.out.println("AuthorRepository.saveAuthor()");
		authors.add(author);
	}

	public List<Author> findAllAuthors() {
		System.out.println("AuthorRepository.findAllAuthors()");
		return authors;
	}
}
