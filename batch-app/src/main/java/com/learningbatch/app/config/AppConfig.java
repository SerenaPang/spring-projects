package com.learningbatch.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.learningbatch.app.repo.BatchProductRepository;
import com.learningbatch.app.repo.SimpleProductRepository;
import com.learningbatch.app.service.ProductService;

import java.time.Clock;
import java.util.Random;

@Configuration
@PropertySource("classpath:com/learningbatch/app/application.properties")
public class AppConfig {

    @Bean
    public ProductService simpleProductService(SimpleProductRepository simpleProductRepository) {
        return new ProductService(simpleProductRepository, new Random(), Clock.systemUTC());
    }

    @Bean
    public ProductService batchProductService(BatchProductRepository batchProductRepository) {
        return new ProductService(batchProductRepository, new Random(), Clock.systemUTC());
    }
}
