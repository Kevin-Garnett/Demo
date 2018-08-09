package com.hsbc.demo.java8;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterSample {

    public static void main(String[] args) throws Exception {

        Method method = ParameterSample.class.getMethod( "main", String[].class );
        for( final Parameter parameter: method.getParameters() ) { //This only will happen in COMPILE
            System.out.println( "Parameter: " + parameter.getName() );
        }
    }
}

