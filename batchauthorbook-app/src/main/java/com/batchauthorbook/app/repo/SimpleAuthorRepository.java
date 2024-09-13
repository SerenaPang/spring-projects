package com.batchauthorbook.app.repo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.batchauthorbook.app.model.Author;

@Repository
public class SimpleAuthorRepository implements AuthorRepository {

	private final JdbcTemplate jdbcTemplate;

	public SimpleAuthorRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public void saveAll(List<Author> authors) {
		for (Author author : authors) {
			jdbcTemplate.update("INSERT INTO Author (name) " + "VALUES (?)", author.getName());
		}
	}
}
