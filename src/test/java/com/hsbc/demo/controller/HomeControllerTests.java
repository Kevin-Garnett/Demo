package com.hsbc.demo.controller;

import com.google.common.net.HttpHeaders;
import com.hsbc.demo.HomeController;
import com.hsbc.demo.images.Image;
import com.hsbc.demo.images.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = HomeController.class)
@Import({ThymeleafAutoConfiguration.class})
public class HomeControllerTests {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    ImageService imageService;

    @Test
    public void baseRouteShouldListAllImages(){
        //Given
        Image kevinImage = new Image("1", "kevin.jpg");
        Image sherryImage = new Image("2", "sherry.jpg");
        given(imageService.findAllImages()).willReturn(Flux.just(kevinImage, sherryImage));

        //When
        EntityExchangeResult<String> result = webTestClient
                .get().uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).returnResult();

        //Then
        verify(imageService).findAllImages();
        verifyNoMoreInteractions(imageService);
        assertThat(result.getResponseBody())
                .contains("<title>Index</title>")
                .contains("<a href=\"/image/kevin.jpg/raw\">")
                .contains("<a href=\"/image/sherry.jpg/raw\">");
    }

    @Test
    public void fetchingImageShouldWork(){
        given(imageService.findOneImage(any()))
                .willReturn(Mono.just(new ByteArrayResource("data".getBytes())));

        webTestClient
                .get().uri("image/kevin.jpg/raw")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("data");

        verify(imageService).findOneImage("kevin.jpg");
        verifyNoMoreInteractions(imageService);
    }

    @Test
    public void fetchingNullImageShouldFail() throws IOException{
        Resource resource = mock(Resource.class);
        given(resource.getInputStream())
                .willThrow(new IOException("Bad file"));
        given(imageService.findOneImage(any()))
                .willReturn(Mono.just(resource));

        webTestClient
                .get().uri("/image/duck.jpg/raw")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .isEqualTo("Couldn't find duck.jpg ==> Bad file");

        verify(imageService).findOneImage("duck.jpg");
        verifyNoMoreInteractions(imageService);
    }

    @Test
    public void deleteImageShouldWork(){
        Image kevinImage = new Image("1", "Kevin.jpg");
        given(imageService.deleteImage(any())).willReturn(Mono.empty());

        webTestClient
                .delete().uri("/image/Kevin.jpg")
                .exchange()
                .expectStatus().isSeeOther()
                .expectHeader().valueEquals(HttpHeaders.LOCATION, "/");

        verify(imageService).deleteImage("Kevin.jpg");
        verifyNoMoreInteractions(imageService);
    }
}
