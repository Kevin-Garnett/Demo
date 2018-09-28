package com.hsbc.demo.chapters;

import com.hsbc.demo.chapters.Chapter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ChapterRepository extends ReactiveCrudRepository<Chapter, String> {
}
