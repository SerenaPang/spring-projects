package com.authorbookjdbcproject.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.authorbookjdbcproject.app.dao.AuthorJdbcDao;
import com.authorbookjdbcproject.app.dao.BookJdbcDao;
import com.authorbookjdbcproject.app.dao.JdbcDataSource;
import com.authorbookjdbcproject.app.model.Author;
import com.authorbookjdbcproject.app.model.Book;

/**
 * author book app
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Test JDBC Connection" );
        JdbcDataSource jdbcDataSource = null;
		try {
			jdbcDataSource = new JdbcDataSource("mysqldb.properties");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//AuthorJdbcDao authorDao = new AuthorJdbcDao(jdbcDataSource);
//		List<Book> listOfBooks = new ArrayList<Book>();
//		List<Book> listOfBooks2 = new ArrayList<Book>();
//		List<Book> listOfBooks3 = new ArrayList<Book>();
//		Book b1 = new Book(0,"aaaa","cb6-ce948hjg-78");
//		Book b2 = new Book(1,"bbbbb","cb6-yjbv56-78");
//		Book b3 = new Book(2,"cccc","cb6-hb3456h-78");
//		listOfBooks.add(b1);
//		listOfBooks.add(b2);
//		listOfBooks.add(b3);
//		Author a1 = new Author("Milan",listOfBooks);
//		Author a2 = new Author("Murakami",listOfBooks2);
//		Author a3 = new Author("May",listOfBooks3);
//		
//		authorDao.saveAuthor(a1);
//		authorDao.saveAuthor(a2);
//		authorDao.saveAuthor(a3);
//		authorDao.findAuthorById(1);
//		authorDao.findAllAuthors();
//		authorDao.deleteAuthor(2);
//		authorDao.findAllAuthors();
//		Author a4 = new Author("MayMayMay",listOfBooks3);
//		authorDao.updateAuthor(a4);
	//	authorDao.findAllAuthors();
		BookJdbcDao bookDao = new BookJdbcDao(jdbcDataSource);
//		Book b1 = new Book(8,"Love Actually", "fgh-567gv-567");
//		bookDao.saveBook(3, b1);
//		Book b2 = new Book(6,"Gone with the wind", "f34-cfr5-v5y6");
//		bookDao.saveBook(4, b2);
		bookDao.findBookById(1);
		bookDao.findBookById(2);
		bookDao.findBookById(4);
		bookDao.findBookById(5);
    }
}
