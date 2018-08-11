package com.hsbc.demo.java8;

import java.util.Optional;

public class OptionalSample {

    public static void main(String[]args)
    {
        // Used to handle Null Pointer Exception - Only has Object or Null
        Optional<String> stringOptional = Optional.ofNullable(null);
        System.out.println(stringOptional);
        System.out.println("Full name is set? " + stringOptional.isPresent());
        System.out.println("Full name: " + stringOptional.orElseGet(()->"[None]"));
        System.out.println(stringOptional.map(s->"Hello, " + s + "!").orElse("Hey, Stranger"));

        stringOptional = Optional.ofNullable("Kevin");
        System.out.println("Full name is set? " + stringOptional.isPresent());
        System.out.println("Full name: " + stringOptional.orElseGet(()->"[None]"));
        System.out.println(stringOptional.map(s->"Hello, " + s + "!").orElse("Hey, Stranger"));
    }
}
