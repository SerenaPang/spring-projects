package com.authorbookjdbcproject.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

			if (authorExist.getBooks() == null || authorExist.getBooks().isEmpty() || !authorExist.getBooks().contains(book)) {
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
				if (authorExist.getBooks().contains(book)) {
					System.out.println("Book already exist");
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public Book findBookById(Integer idBook) {
		System.out.println("jdbc find book by id");

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("SELECT id, name, isbn FROM BOOK WHERE id =?");
			ps.setInt(1, idBook);
			Book book = new Book();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					book.setId(rs.getInt("id"));
					book.setName(rs.getString("name"));
					book.setIsbn(rs.getString("isbn"));
				}
			}
			System.out.println("id: " + book.getId() + " name: " + book.getName() + "isbn: " + book.getIsbn());
			return book;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> findAllBooks() {
//		System.out.println("jdbc find all books");
//		try (Connection connection = dataSource.getConnection()) {
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT id, name from author");
//			List<Author> authors = new ArrayList<>();
//			while (rs.next()) {
//				Author author = new Author();
//				author.setId(rs.getInt("id"));
//				author.setName(rs.getString("name"));
//				authors.add(author);
//			}
//			System.out.println(authors);
//			return authors;
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
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
