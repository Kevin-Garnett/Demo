package com.hsbc.demo.chapters;

import com.hsbc.demo.chapters.Chapter;
import com.hsbc.demo.chapters.ChapterRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChapterService {

    private final ChapterRepository chapterRepository;

    public ChapterService (ChapterRepository chapterRepository)
    {
        this.chapterRepository = chapterRepository;
    }

    public Flux<Chapter> listing()
    {
        return chapterRepository.findAll();
    }
}
