package com.batchauthorbook.app.repo;

import java.util.List;

import com.batchauthorbook.app.model.Author;

public interface AuthorRepository {
	void saveAll(List<Author> authors);
}
