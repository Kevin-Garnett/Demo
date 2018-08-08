package com.hsbc.demo.config;

import com.hsbc.demo.bean.Chapter;
import com.hsbc.demo.repository.ChapterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class LoadDatabase {

    CommandLineRunner init(ChapterRepository chapterRepository)
    {
        return args -> {
            Flux.just(
                    new Chapter("Quick start with Java"),
                    new Chapter("Reactive web with spring boot"),
                    new Chapter("...and more!"))
                    .flatMap(chapterRepository::save)
                    .subscribe(System.out::println);
        };
    }
}
