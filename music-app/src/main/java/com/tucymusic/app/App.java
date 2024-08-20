package com.tucymusic.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
Main class to start Spring Boot with JDBC support.
*/
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	 System.out.println( "App.main()" );
         SpringApplication.run(App.class, args);
    }
}
