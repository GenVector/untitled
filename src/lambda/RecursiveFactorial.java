package lambda;

import java.util.*;
import java.util.stream.Stream;

interface IntCall {
    int call(int arg);
}

public class RecursiveFactorial {
    IntCall fact;

    public RecursiveFactorial() {
        fact = n -> n == 0 ? 1 : n * fact.call(n - 1);
    }

    public static void main(String[] args) {
        RecursiveFactorial recursiveFactorial = new RecursiveFactorial();
        for (int i = 0; i <= 10; i++)
            System.out.println(recursiveFactorial.fact.call(i));
        new Random().ints(0, 10).distinct().limit(10).sorted().forEach(System.out::println);

        new Random()
                .ints(5, 20)
                .distinct()
                .limit(15)
                .sorted()
                .forEach(System.out::println);
    }
}


class RecursiveFibonacci {
    IntCall fib;

    RecursiveFibonacci() {
        fib = n -> n == 0 ? 0 :
                n == 1 ? 1 :
                        fib.call(n - 1) + fib.call(n - 2);
    }


    int fibonacci(int n) {
        return fib.call(n);
    }

    int fibonacci2(int n) {
        return n;
    }

    public void print(int i) {
        System.out.print(this.fib.call(i) + " ");
    }

    int x = 1;

    Stream<Integer> numbers() {
        return Stream.iterate(0, i -> {
            int result = x + i;
            x = i;
            return result;
        });
    }

    //Fibonacci的各种实现
    public static void main(String[] args) {

        RecursiveFibonacci rf = new RecursiveFibonacci();
        //函数式接口实现1
        for (int i = 0; i <= 10; i++)
            System.out.print(rf.fib.call(i) + " ");
        System.out.println();
        //加入Random().ints流和方法引用实现
        new Random()
                .ints(0, 11)
                .distinct()
                .limit(11)
                .sorted()
                .forEach(rf::print);
        System.out.println();
        //Stream.iterate实现
        rf.numbers().skip(5).limit(10).forEach(System.out::print);
        System.out.println();
        //最贴近原始方法的实现
        new Random()
                .ints(0, 11)
                .distinct()
                .limit(11)
                .sorted()
                .forEach(i -> {
                    System.out.print(rf.f(i) + " ");
                });
    }

    public int f(int i) {
        if (i == 0)
            return 0;
        if (i == 1)
            return 1;
        return f(i - 1) + f(i - 2);
    }
}