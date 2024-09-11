package com.batchauthorbook.app.repo;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.batchauthorbook.app.model.Author;

public class BatchAuthorRepository implements AuthorRepository {
	 private JdbcTemplate jdbcTemplate;
	 
	@Override
	@Transactional
	public void saveAll(List<Author> authors) {
		  jdbcTemplate.batchUpdate("INSERT INTO AUTHOR (id, name) " +
			        "VALUES (?, ?)",
			        authors,
			        100,
			        (PreparedStatement ps, Author author) -> {
			         ps.setInt(1,  author.getId());
			          ps.setString(2, author.getName());
			        });
	}

}
