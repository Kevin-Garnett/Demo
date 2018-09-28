package com.hsbc.demo.repository;

import com.hsbc.demo.images.Image;
import com.hsbc.demo.images.ImageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
//If we need to switch off Flapdoodle, only we need to do is exclude EmbeddedMongoAutoConfiguration
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class EmbeddedImageRepositoryTests {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    MongoOperations mongoOperations;

    @Before
    public void setUp(){
        mongoOperations.dropCollection(Image.class);
        mongoOperations.insert(new Image("1", "kevin.jpg"));
        mongoOperations.insert(new Image("2", "sherry.jpg"));
        mongoOperations.insert(new Image("3", "gunter.jpg"));

        mongoOperations.findAll(Image.class).forEach(
                image->System.out.println(image.toString())
        );
    }

    @Test
    public void findAllShouldWork(){
        Flux<Image> images = imageRepository.findAll();
        StepVerifier.create(images)
                .recordWith(ArrayList::new)
                .expectNextCount(3)
                .consumeRecordedWith(results -> {
                    assertThat(results).hasSize(3);
                    assertThat(results)
                            .extracting(Image::getName)
                            .contains(
                                    "kevin.jpg",
                                    "sherry.jpg",
                                    "gunter.jpg"
                            );
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void findByNameShouldWork(){
        Mono<Image> image = imageRepository.findByName("kevin.jpg");
        StepVerifier.create(image)
                .expectNextMatches(result -> {
                    assertThat(result.getName()).isEqualTo("kevin.jpg");
                    assertThat(result.getId()).isEqualTo("1");
                    return true;
                });
    }

}
