package com.hsbc.demo.java8;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.function.Supplier;

public class FluxTest {

    private Void keyAndCount;

    public static void main(String []args) {

        System.out.println("Flux: ");
        Flux.just("Kevin", "Sherry", "Gunter")
                .subscribe(System.out::println);

        System.out.println("###########");

        System.out.println("Arrays: ");
        Arrays.asList("Kevin", "Sherry", "Gunter").forEach(System.out::println);

        System.out.println("###########");

        Flux.just(
                (Supplier<String>) () -> "Kevin",
                (Supplier<String>) () -> "Sherry",
                (Supplier<String>) () -> "Gunter"
        ).subscribe(stringSupplier -> System.out.println(stringSupplier.get()));


        Flux.just("Kevin", "Sherry", "Gunter")
                .map(String::toUpperCase)
                .flatMap(s -> Flux.fromArray(s.split("")))
                .groupBy(String::toString)
                .sort((o1, o2) -> o1.key().compareTo(o2.key()))
                .flatMap(group -> Mono.just(group.key()).and(group.count()))
                .map(keyAndCount -> keyAndCount.toString() + " ==> " + keyAndCount)

                .subscribe(System.out::println);

        Flux.just("Hello", "World").subscribe(System.out::println);
        Flux.fromArray(new Integer[]{1,2,3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);

    }


}
