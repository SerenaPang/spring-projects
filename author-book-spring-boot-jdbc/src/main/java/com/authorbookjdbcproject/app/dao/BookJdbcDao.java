package com.authorbookjdbcproject.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.authorbookjdbcproject.app.model.Author;
import com.authorbookjdbcproject.app.model.Book;

public class BookJdbcDao implements BookDao {
	@Autowired
	private JdbcDataSource dataSource;
	@Autowired
	AuthorJdbcDao authorDao;

	public BookJdbcDao() {
	}

	@Override
	public Book saveBook(Integer authorId, Book book) {
		System.out.println("jdbc save book");
		Author authorExist = authorDao.findAuthorById(authorId);
		if (authorExist != null) {

			if (authorExist.getBooks() == null || authorExist.getBooks().isEmpty()
					|| !authorExist.getBooks().contains(book)) {
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
			PreparedStatement ps = connection.prepareStatement("SELECT id, name, isbn FROM BOOK WHERE id =?");
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
		System.out.println("jdbc find all books");
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, isbn from book");
			List<Book> books = new ArrayList<>();
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setIsbn(rs.getString("isbn"));
				books.add(book);
			}
			System.out.println(books);
			return books;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Book deleteBookById(Integer idBook) {
		System.out.println("jdbc delete Book");

		Book target = findBookById(idBook);
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM book WHERE id= ?");
				ps.setInt(1, idBook);
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc delete Book " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Book updateBook(Integer authorId, Book book) {
		System.out.println("jdbc update book");
		Author authorExist = authorDao.findAuthorById(authorId);
		if (authorExist != null) {
			if (authorExist.getBooks() != null && authorExist.getBooks().contains(book)) {
				try (Connection connection = dataSource.getConnection()) {
					PreparedStatement ps = connection.prepareStatement("UPDATE book SET name=? isbn=? WHERE id=?");
					ps.setString(1, book.getName());
					ps.setString(2, book.getIsbn());
					ps.setInt(3, book.getId());
					int i = ps.executeUpdate();
					if (i == 1) {
						System.out.println("jdbc update book " + book.toString());
						return book;
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return null;
	}

	public Book updateBook(Book book) {
		System.out.println("jdbc update book");
		Book bookExist = findBookById(book.getId());
		if (bookExist != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("UPDATE book SET name=? isbn=? WHERE id=?");
				ps.setString(1, book.getName());
				ps.setString(2, book.getIsbn());
				ps.setInt(3, book.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update book " + book.toString());
					return book;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
