package thread;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.*;

public class ParallelStreamPuzzle {
    private static class IntGenerator
            implements Supplier<Integer> {
        private int current = 0;

        public Integer get() {
            return current++;
        }
    }

    public static void main(String[] args) {
        List<Integer> x = Stream.generate(new IntGenerator())
                .limit(10)
                .parallel()  // [1]
                .collect(Collectors.toList());
        System.out.println(x);
    }
}

class ParallelStreamPuzzle2 {
    public static final Deque<String> trace =
            new ConcurrentLinkedDeque<>();

    private static class
    IntGenerator implements Supplier<Integer> {
        private AtomicInteger current =
                new AtomicInteger();

        public IntGenerator(){

        }

        public Integer get() {
            System.out.println("init");
            trace.add(current.get() + ": " + Thread.currentThread().getName());
            return current.getAndIncrement();
        }
    }

    public static void main(String[] args) throws Exception {
        List<Integer> x = Stream.generate(new IntGenerator())
                .limit(10)
                .parallel()
                .collect(Collectors.toList());
        System.out.println(x);
        //Files.write(Paths.get("PSP2.txt"), trace);
    }
}

class ParallelStreamPuzzle3 {
    public static void main(String[] args) {
        List<Integer> x = IntStream.range(0, 30)
                .peek(e -> System.out.println(e + ": " +Thread.currentThread()
                        .getName()))
                .limit(10)
                .parallel()
                .boxed()
                .collect(Collectors.toList());
        System.out.println(x);
    }
}