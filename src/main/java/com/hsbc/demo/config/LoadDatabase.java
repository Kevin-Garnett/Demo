package com.hsbc.demo.config;

import com.hsbc.demo.bean.Chapter;
import com.hsbc.demo.repository.ChapterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner init(ChapterRepository chapterRepository) {
        return args -> {
            Flux.just(
                    new Chapter("Kevin"),
                    new Chapter("Sherry"),
                    new Chapter("Gunter"))
                    .flatMap(chapterRepository::save)
                    .subscribe(System.out::println);
        };
    }
}
