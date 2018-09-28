package com.hsbc.demo.config;

import com.hsbc.demo.chapters.Chapter;
import com.hsbc.demo.chapters.ChapterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class LoadDatabase {

    // Remember should not have 2 same function name = init() here, otherwise spring boot will only run 1 of them
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
