package com.authorbookjdbcproject.app.dao;

import java.util.List;

import com.authorbookjdbcproject.app.model.Author;

public interface AuthorDao {
	public Author saveAuthor(Author author);
	public Author findAuthorById(Integer id);
	public List<Author> findAllAuthors();
	public Author deleteAuthor(Integer idAuthor);
	public Author updateAuthor(Author author);
}
