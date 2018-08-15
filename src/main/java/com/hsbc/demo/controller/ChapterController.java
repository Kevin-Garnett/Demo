package com.hsbc.demo.controller;

import com.hsbc.demo.bean.Chapter;
import com.hsbc.demo.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value="/demo")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/chapters")
    public Flux<Chapter> listing()
    {
        return chapterService.listing();
    }
}
