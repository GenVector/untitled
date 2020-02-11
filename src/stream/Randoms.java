package stream;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class Randoms {
    public static void random() {
        new Random(47)
                .ints(5, 20)
                .distinct()
                .limit(7)
                .sorted()
                .forEach(System.out::println);
        Random random = new Random();

    }


    public static void main() {
        Random rand = new Random(47);
        SortedSet<Integer> rints = new TreeSet<>();
        while (rints.size() < 7) {
            int r = rand.nextInt(20);
            if (r < 5) continue;
            rints.add(r);
        }
        System.out.println(rints);
    }

    public static <T> void show(Stream<T> stream) {
        stream
                .limit(4)
                .forEach(System.out::println);
        System.out.println("++++++++");
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        show(rand.ints().boxed());
        show(rand.longs().boxed());
        show(rand.doubles().boxed());
        // 控制上限和下限：
        show(rand.ints(10, 20).boxed());
        show(rand.longs(50, 100).boxed());
        show(rand.doubles(20, 30).boxed());
        // 控制流大小：
        show(rand.ints(2).boxed());
        show(rand.longs(2).boxed());
        show(rand.doubles(2).boxed());
        // 控制流的大小和界限
        show(rand.ints(3, 3, 9).boxed());
        show(rand.longs(3, 12, 22).boxed());
        show(rand.doubles(3, 11.5, 12.3).boxed());
    }
}

class ArrayStreams {
    public static void main(String[] args) {
        Arrays.stream(new double[]{3.14159, 2.718, 1.618})
                .forEach(n -> System.out.format("%f ", n));
        System.out.println();

        Arrays.stream(new int[]{1, 3, 5})
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        Arrays.stream(new long[]{11, 22, 44, 66})
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        // 选择一个子域:
        Arrays.stream(new int[]{1, 3, 5, 7, 15, 28, 37}, 3, 6)
                .forEach(n -> System.out.format("%d ", n));
    }
}

class Ranges {
    public static void main(String[] args) {

        //System.out.println(result);
        // for-in 循环:

        for (int i : IntStream.range(10, 20).toArray()) ;

        //System.out.println(result);
        // 使用流:
        //System.out.println(IntStream.range(10, 20).sum());

        //10~19
        //IntStream.range(10, 20).forEach(System.out::println);
        //10~19
        //IntStream.iterate(10, i -> i + 1).limit(10).forEach(System.out::println);
        //IntStream.rangeClosed(10, 19).forEach(System.out::println);
        new Random()
                .ints(10, 20)
                .distinct().limit(10).peek(e -> System.out.println(e + ": " + Thread.currentThread()
                .getName())).count();
        IntStream.range(0, 30).parallel()
                .peek(e -> System.out.println(e + ": " + Thread.currentThread()
                        .getName())).limit(10).count();
    }

    public static void repeat(int n, Runnable action) {
        range(0, n).forEach(i -> action.run());
    }
}