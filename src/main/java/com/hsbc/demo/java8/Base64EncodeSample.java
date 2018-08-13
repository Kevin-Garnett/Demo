package com.hsbc.demo.java8;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64EncodeSample {

    public static void main(String[] args) {

        final String text = "Base64 finally in Java 8!";

        System.out.println( "Original text: " + text );

        final String encoded = Base64
                .getEncoder()
                .encodeToString( text.getBytes( StandardCharsets.UTF_8 ) );
        System.out.println( "Encoded text: " + encoded );

        final String decoded = new String(
                Base64.getDecoder().decode( encoded ),
                StandardCharsets.UTF_8 );
        System.out.println( "Decoded text: " + decoded );
    }
}

