package com.mycompany.app;
import org.springframework.boot.SpringApplication;

/**
 * run author book app
 */
public class App 
{
    public static void main(String[] args )
    {
        System.out.println( "Welcome to author book!" );
        SpringApplication.run(App.class, args);
    }
}
