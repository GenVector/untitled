package thread.parallel;

import java.time.Instant;
import java.util.stream.*;

import static java.util.stream.LongStream.*;

import java.io.*;

import static java.util.stream.LongStream.rangeClosed;

public class ParallelPrime {
    static final int COUNT = 100_000;

    public static boolean isPrime(long n) {
        return rangeClosed(2, (long) Math.sqrt(n)).noneMatch(i -> n % i == 0);
    }

    public static void iterateWithParallel() {
        Instant start = Instant.now();
        long num =
                iterate(1, i -> i + 1).limit(COUNT)
                        .parallel()
                        .filter(ParallelPrime::isPrime)
                        .sum();
        Instant end = Instant.now();
        System.out.println("iterate Parallel time:" + (end.toEpochMilli() - start.toEpochMilli()) + "-------num-----" + num);
        //primes.forEach(System.out::print);
    }

    public static void iterateWithoutParallel() {
        Instant start = Instant.now();
        long num =
                iterate(1, i -> i + 1).limit(COUNT)
                        .filter(ParallelPrime::isPrime)
                        .sum();
        Instant end = Instant.now();
        System.out.println("iterate WithoutParallel time:" + (end.toEpochMilli() - start.toEpochMilli()) + "-------num-----" + num);
        //primes.forEach(System.out::print);
    }

    public static void testrangeClosed() {
        Instant start = Instant.now();
        long num =
                LongStream.rangeClosed(1, COUNT)
                        .filter(ParallelPrime::isPrime).reduce(0L, Long::sum);
        Instant end = Instant.now();
        System.out.println("rangeClosed WithoutParallel time:" + (end.toEpochMilli() - start.toEpochMilli()) + "-------num-----" + num);
        //primes.forEach(System.out::print);
    }

    public static void testrangeClosedParallel() {
        Instant start = Instant.now();
        long num =
                LongStream.rangeClosed(1, COUNT).parallel()
                        .filter(ParallelPrime::isPrime).reduce(0L, Long::sum);
        Instant end = Instant.now();
        System.out.println("rangeClosed Parallel time:" + (end.toEpochMilli() - start.toEpochMilli()) + "-------num-----" + num);
        //primes.forEach(System.out::print);
    }

    public static void testFor() {
        Instant start = Instant.now();
        long num = 0;
        for (long i = 1; i <= COUNT; i++) {
            if (ParallelPrime.isPrime(i)) {
                num = num + i;
            }
        }

        Instant end = Instant.now();
        System.out.println("for time:" + (end.toEpochMilli() - start.toEpochMilli()) + "-------num-----" + num);
        //primes.forEach(System.out::print);
    }

    public static void main(String[] args)
            throws IOException {
        iterateWithoutParallel();
        iterateWithParallel();
        testrangeClosed();
        testrangeClosedParallel();
        testFor();

//        iterate(2, i -> i + 1)
//                .limit(10).forEach(System.out::println);
//        LongStream.rangeClosed(1, 10).forEach(System.out::println);
        //Files.write(Paths.get("primes.txt"), primes, StandardOpenOption.CREATE);

    }
}