package com.batchauthorbook.app.repo;

import java.util.List;

import com.batchauthorbook.app.model.Book;

public interface BookRepository {
	void saveAll(List<Book> books);
}
