package com.batchauthorbook.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.batchauthorbook.app.repo.BatchAuthorRepository;
import com.batchauthorbook.app.repo.SimpleAuthorRepository;
import com.batchauthorbook.app.service.AuthorService;

import java.time.Clock;
import java.util.Random;

@Configuration
@PropertySource("classpath:com/batchauthorbook/app/application.properties")
public class AppConfig {

    @Bean
    public AuthorService simpleAuthorService(SimpleAuthorRepository simpleAuthorRepository) {
        return new AuthorService(simpleAuthorRepository, new Random(), Clock.systemUTC());
    }

    @Bean
    public AuthorService batchAuthorBookService(BatchAuthorRepository batchAuthorRepository) {
        return new AuthorService(batchAuthorRepository, new Random(), Clock.systemUTC());
    }
}
