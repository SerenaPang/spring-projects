package com.mycompany.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * run author book app
 */
@SpringBootApplication
public class App 
{
    public static void main(String[] args )
    {
        System.out.println( "Welcome to author book!" );
        SpringApplication.run(App.class, args);
    }
}
