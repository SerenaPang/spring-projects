package com.batchauthorbook.app.repo;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.batchauthorbook.app.model.Author;

@Repository
public class BatchAuthorRepository implements AuthorRepository {
	 
	@Autowired
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
