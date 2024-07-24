package com.authorbookjdbcproject.app;

import java.io.IOException;
import java.sql.SQLException;

import com.authorbookjdbcproject.app.dao.AuthorJdbcDao;
import com.authorbookjdbcproject.app.dao.JdbcDataSource;

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
		
    }
}
