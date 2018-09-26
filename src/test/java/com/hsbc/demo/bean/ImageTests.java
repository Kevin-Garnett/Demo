package com.hsbc.demo.bean;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ImageTests {

    @Test
    public void imagesManagedByLombokShouldWork(){
        Image image = new Image("id", "kevin.jpg");
        assertThat(image.getId()).isEqualTo("id");
        assertThat(image.getName()).isEqualTo("kevin.jpg");
    }
}
