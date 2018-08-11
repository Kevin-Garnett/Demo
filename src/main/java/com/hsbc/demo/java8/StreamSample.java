package com.hsbc.demo.java8;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSample  {
    private enum Status {
        OPEN, CLOSED
    }

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task( final Status status, final Integer points ) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }

    public static void main(String[]args) throws Exception
    {
        final Collection<Task> tasks = Arrays.asList(
            new Task(Status.OPEN, 5),
            new Task(Status.OPEN, 10),
            new Task(Status.CLOSED, 17)
        );

        // Calculate the total points for the tasks which open
        final long totalOpenPoints = tasks
                .stream()   //Use stream to handle collection
                .filter(task -> task.getStatus() == Status.OPEN) //Use filter to filter the status = closed record
                .mapToInt(Task::getPoints) //Change the Task collection to Integer collection
                .sum(); //Sum the result

        // Intermediate operations return a new stream. They are always lazy;
        // executing an intermediate operation such as filter() does not actually perform any filtering,
        // but instead creates a new stream that, when traversed, contains the elements of the initial stream that match the given predicate.
        // Traversal of the pipeline source does not begin until the terminal operation of the pipeline is executed.

        // Terminal operations, such as Stream.forEach or IntStream.sum, may traverse the stream to produce a result or a side-effect.
        // After the terminal operation is performed, the stream pipeline is considered consumed, and can no longer be used;
        // if you need to traverse the same data source again, you must return to the data source to get a new stream.
        // In almost all cases, terminal operations are eager, completing their traversal of the data source and processing of the pipeline before returning.
        // Only the terminal operations iterator() and spliterator() are not;
        // these are provided as an "escape hatch" to enable arbitrary client-controlled pipeline traversals in the event that the existing operations are not sufficient to the task.

        System.out.println("Total open points: " + totalOpenPoints);

        // Calculate total points for all tasks
        final double totalPoints = tasks
                .stream()
                .parallel()
                .map(task -> task.getPoints())
                .reduce(0, Integer::sum);

        System.out.println("Total points: " + totalPoints);

        // Group tasks by their status
        final Map< Status, List< Task >> map = tasks
                .stream()
                .collect( Collectors.groupingBy( Task::getStatus ) );
        System.out.println( map );

        // Calculate the weight of each tasks (as percent of total points)
        final Collection< String > result = tasks
                .stream()                                        // Stream< String >
                .mapToInt( Task::getPoints )                     // IntStream
                .asLongStream()                                  // LongStream
                .mapToDouble( points -> points / totalPoints )   // DoubleStream
                .boxed()                                         // Stream< Double >
                .mapToLong( weigth -> ( long )( weigth * 100 ) ) // LongStream
                .mapToObj( percentage -> percentage + "%" )      // Stream< String>
                .collect( Collectors.toList() );                 // List< String >

        System.out.println( result );


        // Another example for stream handle file
        final Path path = new File( "/Users/kg/test.txt" ).toPath();
        try( Stream< String > lines = Files.lines( path, StandardCharsets.UTF_8 ) ) {
            lines.onClose( () -> System.out.println("Done!") ).forEach( System.out::println );
        }

    }
}

