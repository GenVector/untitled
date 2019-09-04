import java.util.*;
import java.util.stream.Collectors;



class Jerry {
    public Jerry(String color, int year) {
        this.color = color;
        this.year = year;

    }

    public String color;
    public int year;
}

public class LambdaTest {
    public static void main(String[] args) {


    }

    public static void lambdaLListTest() {
        new Thread(() -> System.out.println("Hello world !")).start();
        Random random = new Random();
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<Jerry> jerries = Arrays.asList(new Jerry("green", 12), new Jerry("blue", 2), new Jerry("gray", 8), new Jerry("yellow", 10));
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        List filterList = strings.stream().filter(string -> string.equals("")).collect(Collectors.toList());
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        List filtered = strings.stream().filter(string -> string.equals("bc")).collect(Collectors.toList());
        List limitList = jerries.stream().limit(10).collect(Collectors.toList());
        Map map = jerries.stream().filter(jerry -> jerry.year > 5).collect(Collectors.groupingBy(jerry -> jerry.color, Collectors.summarizingInt(t -> 1)));
        Map<String, List<Jerry>> collectOne = jerries.stream().collect(Collectors.groupingBy((jerry) -> jerry.color));
        IntSummaryStatistics stats = jerries.stream().mapToInt((x) -> x.year).summaryStatistics();
        stats.getMax();
    }
}
