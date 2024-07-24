package com.authorbookjdbcproject.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.authorbookjdbcproject.app.dao.AuthorJdbcDao;
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
		AuthorJdbcDao authorDao = new AuthorJdbcDao(jdbcDataSource);
		List<Book> listOfBooks = new ArrayList<Book>();
		Book b1 = new Book(0,"aaaa","cb6-ce948hjg-78");
		Book b2 = new Book(1,"bbbbb","cb6-yjbv56-78");
		Book b3 = new Book(2,"cccc","cb6-hb3456h-78");
		listOfBooks.add(b1);
		listOfBooks.add(b2);
		listOfBooks.add(b3);
		Author a1 = new Author("Milan",listOfBooks);
		authorDao.saveAuthor(a1);
    }
}
