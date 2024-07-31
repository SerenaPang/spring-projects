package com.petcoco.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to start Spring Boot with JDBC support
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Welcome to Pet Adoption Center!" );
		SpringApplication.run(App.class, args);
    }
}
