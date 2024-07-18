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

	public Author saveAuthor(Author author) {
		System.out.println("AuthorRepository.saveAuthor()");
		authors.add(author);
		return author;
	}
	
	public Author findAuthorById(Integer id) {
		Author resultAuthor = null;
		for (int i = 0; i < authors.size(); i++) {
			if (id == authors.get(i).getId()) {
				resultAuthor = authors.get(i);
				break;
			}
		}
		return resultAuthor;
	}

	public List<Author> findAllAuthors() {
		System.out.println("AuthorRepository.findAllAuthors()");
		return authors;
	}

	public Author deleteByAuthorId(Integer idAuthor) {
		System.out.println("AuthorRepository.deleteByAuthorId()");
		Author target = null;
		for (int i = 0; i < authors.size(); i++) {
			if (idAuthor == authors.get(i).getId()) {
				target = authors.get(i);
				authors.remove(i);
				break;
			}
		}
		return target;
	}

	public Author updateAuthor(Author author) {
		Author target = author;
		for (int i = 0; i < authors.size(); i++) {
			if (target.getId() == authors.get(i).getId()) {
				authors.get(i).setName(target.getName());
				authors.get(i).setBooks(author.getBooks());
				break;
			}
		}
		return author;

	}
}
