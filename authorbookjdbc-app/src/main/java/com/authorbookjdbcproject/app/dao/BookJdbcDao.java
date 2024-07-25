package com.authorbookjdbcproject.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.authorbookjdbcproject.app.model.Author;
import com.authorbookjdbcproject.app.model.Book;

public class BookJdbcDao implements BookDao {
	private JdbcDataSource dataSource;
	AuthorJdbcDao authorDao = null;

	public BookJdbcDao(JdbcDataSource dataSource) {
		this.dataSource = dataSource;
		authorDao = new AuthorJdbcDao(dataSource);
	}

	@Override
	public Book saveBook(Integer authorId, Book book) {
		System.out.println("jdbc save book");
		Author authorExist = authorDao.findAuthorById(authorId);
		if (authorExist != null) {

			if (authorExist.getBooks() == null || authorExist.getBooks().isEmpty()) {
				try (Connection connection = dataSource.getConnection()) {
					PreparedStatement ps = connection
							.prepareStatement("INSERT INTO BOOK(name, isbn, id_author) " + "VALUES(?,?,?)");
					ps.setString(1, book.getName());
					ps.setString(2, book.getIsbn());
					ps.setInt(3, authorId);
					int i = ps.executeUpdate();

					if (i == 1) {
						System.out.println("jdbc saved book info to database");
						System.out.println(book.toString());
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
				List<Book> bookList = authorExist.getBooks();
				for (int i = 0; i < bookList.size(); i++) {
					if (book.getId() == bookList.get(i).getId() && book.getName() != bookList.get(i).getName()) {
						System.out.println("Book already exist");
						return null;
					}
				}
			}
		}
		return null;
	}

	@Override
	public Book findBookById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book deleteBookById(Integer idBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book updateBook(Integer authorId, Book book) {
		// TODO Auto-generated method stub
		return null;
	}

}
