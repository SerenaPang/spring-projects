package com.batchsong.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.batchsong.app.repo.BatchSongRepository;
import com.batchsong.app.repo.SimpleSongRepository;
import com.batchsong.app.service.SongService;


import java.time.Clock;
import java.util.Random;

@Configuration
@PropertySource("classpath:com/learningbatch/app/application.properties")
public class AppConfig {

    @Bean
    public SongService simpleSongService(SimpleSongRepository simpleSongRepository) {
        return new SongService(simpleSongRepository, new Random(), Clock.systemUTC());
    }

    @Bean
    public SongService batchSongService(BatchSongRepository batchSongRepository) {
        return new SongService(batchSongRepository, new Random(), Clock.systemUTC());
    }
}
