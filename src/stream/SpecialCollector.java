package stream;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpecialCollector {
    public static void main(String[] args) throws Exception {
        ArrayList<String> words =
                FileToWords.stream("Cheese.dat").collect(
                        ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);

        ArrayList<String> words2 =
                FileToWords.stream("Cheese.dat").collect(Collectors.toCollection(ArrayList::new));

        //FileToWords.stream("Cheese.dat").forEach(System.out::println);
        words2.stream()
                .filter(s -> s.equals("cheese"))
                .forEach(System.out::println);
    }
}

class Frobnitz {
    int size;

    Frobnitz(int sz) {
        size = sz;
    }

    @Override
    public String toString() {
        return "Frobnitz(" + size + ")";
    }

    // Generator:
    static Random rand = new Random(47);
    static final int BOUND = 100;

    static Frobnitz supply() {
        return new Frobnitz(rand.nextInt(BOUND));
    }

    public static void main(String[] args) {
        Stream.generate(Frobnitz::supply)
                .limit(10)
                .peek(System.out::println)
                .reduce((fr0, fr1) -> fr0.size < 50 ? fr0 : fr1)
                .ifPresent(System.out::println);
    }
}

interface BinaryOperator<T> {


    public static <T> java.util.function.BinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    public static <T> java.util.function.BinaryOperator<T> minBy2(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    public static <T> java.util.function.BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    public static <T> java.util.function.BinaryOperator<T> maxBy2(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }
}