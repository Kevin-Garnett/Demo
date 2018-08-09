package com.hsbc.demo.java8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

public class AnnotationSample {

    @Retention( RetentionPolicy.RUNTIME )
    @Target( { ElementType.TYPE_USE, ElementType.TYPE_PARAMETER } )
    public @interface NonEmpty {
    }

    public static class Holder< @NonEmpty T > extends @NonEmpty Object { //Annotation can use in super class / Generic type
        public void method() throws @NonEmpty Exception { //Annotation can use in Exception
        }
    }

    @SuppressWarnings( "unused" )
    public static void main(String[] args) {
        final Holder< String > holder = new @NonEmpty Holder< String >(); //Annotation used in Object
        @NonEmpty Collection< @NonEmpty String > strings = new ArrayList<>(); //Annotation used in Object
        System.out.println(strings);
    }
}

