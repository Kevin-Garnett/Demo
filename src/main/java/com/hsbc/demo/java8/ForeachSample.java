package com.hsbc.demo.java8;

import java.util.Arrays;

public class ForeachSample {

    public static void main(String []args)
    {
        String separator = ",";
        Arrays.asList("a", "b", "c", "d").forEach(e -> {
            System.out.print(e + separator);
        });

        System.out.println();

        Integer[] a = new Integer[]{3,4,2,1,32,6,6,7,8,9};
        Arrays.asList(a).sort((e1, e2) -> e1.compareTo(e2));
        Arrays.asList(a).forEach(e->System.out.print(e + separator));

        System.out.println();

    }
}
