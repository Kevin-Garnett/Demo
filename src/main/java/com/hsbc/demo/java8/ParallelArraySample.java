package com.hsbc.demo.java8;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ParallelArraySample {

    public static void main( String[] args ) {

        long[] arrayOfLong = new long [ 20000 ];

        // Set the array randomly
        Arrays.parallelSetAll( arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt( 1000000 ) );

        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );

        System.out.println();

        System.out.print(Arrays.stream( arrayOfLong ).min());

        System.out.println();

        Arrays.parallelSort( arrayOfLong );
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );
        System.out.println();
    }
}

