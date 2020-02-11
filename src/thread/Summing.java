package thread;

import java.time.Instant;
import java.util.Arrays;
import java.util.stream.*;
import java.util.function.*;


public class Summing {
    static void timeTest(String id, long checkValue, LongSupplier operation) {
        System.out.print(id + ": ");
        Instant start = Instant.now();
        long result = operation.getAsLong();
        if (result == checkValue) {
            Instant end = Instant.now();
            System.out.println("time:" + (end.toEpochMilli() - start.toEpochMilli()));
        } else
            System.out.format("result: %d%ncheckValue: %d%n", result, checkValue);
    }

    public static final int SZ = Integer.MAX_VALUE - 1;// This even works://
    public static final long CHECK = (long) SZ * ((long) SZ + 1) / 2; // Gauss's formula

    public static void main(String[] args) {
        System.out.println(CHECK);
        timeTest("Sum Stream", CHECK, () -> LongStream.rangeClosed(0, SZ).sum());
        timeTest("Sum Stream Parallel", CHECK, () -> LongStream.rangeClosed(0, SZ).parallel().sum());
        timeTest("Sum Iterated", CHECK, () -> LongStream.iterate(0, i -> i + 1).limit(SZ + 1).sum());
        // Slower & runs out of memory above 1_000_000:
        // timeTest("Sum Iterated Parallel", CHECK, () ->
        //   LongStream.iterate(0, i -> i + 1)
        //     .parallel()
        //     .limit(SZ+1).sum());
    }
}

class Summing2 {
    static long basicSum(long[] ia) {
        long sum = 0;
        int size = ia.length;
        for (int i = 0; i < size; i++)
            sum += ia[i];
        return sum;
    }

    // Approximate largest value of SZ before
    // running out of memory on mymachine:
    public static final int SZ = 10_000_000;
    public static final long CHECK = (long) SZ * ((long) SZ + 1) / 2;

    public static void main(String[] args) {
        System.out.println(CHECK);
        long[] la = new long[SZ + 1];
        Arrays.parallelSetAll(la, i -> i);
        Summing.timeTest("Array Stream Sum", CHECK, () ->
                Arrays.stream(la).sum());
        Summing.timeTest("Parallel", CHECK, () ->
                Arrays.stream(la).parallel().sum());
        Summing.timeTest("Basic Sum", CHECK, () ->
                basicSum(la));// Destructive summation:
        Summing.timeTest("parallelPrefix", CHECK, () -> {
            Arrays.parallelPrefix(la, Long::sum);
            return la[la.length - 1];
        });
    }
}

class Summing3 {
    static long basicSum(Long[] ia) {
        long sum = 0;
        int size = ia.length;
        for (int i = 0; i < size; i++)
            sum += ia[i];
        return sum;
    }

    // Approximate largest value of SZ before
// running out of memory on my machine:
    public static final int SZ = 10_000_000;
    public static final long CHECK = (long) SZ * ((long) SZ + 1) / 2;

    public static void main(String[] args) {
        System.out.println(10_000_000==10000000);
        System.out.println(CHECK);
        Long[] aL = new Long[SZ + 1];
        Arrays.parallelSetAll(aL, i -> (long) i);
        Summing.timeTest("Long Array Stream Reduce", CHECK, () ->
                Arrays.stream(aL).reduce(0L, Long::sum));
        Summing.timeTest("Long Basic Sum", CHECK, () -> basicSum(aL));
        // Destructive summation:
        Summing.timeTest("Long parallelPrefix", CHECK, () -> {
            Arrays.parallelPrefix(aL, Long::sum);
            return aL[aL.length - 1];
        });
    }
}