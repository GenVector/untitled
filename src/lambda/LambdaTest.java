package lambda;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
class Jerry {
    public Jerry(String color, int year) {
        this.color = color;
        this.year = year;

    }

    public int getYear() {
        return year;
    }

    public String color;
    public int year;
}

public class LambdaTest {
    public static void main(String[] args) {
        lambdaLListTest();
    }

    public static void lambdaLListTest() {
        new Thread(() -> System.out.println("Hello world !")).start();
        Random random = new Random();
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<Jerry> jerries = Arrays.asList(new Jerry("green", 12), new Jerry("blue", 2), new Jerry("gray", 8), new Jerry("yellow", 10));
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        List limitList = jerries.stream().limit(10).collect(Collectors.toList());
        Map map = jerries.stream().filter(jerry -> jerry.year > 5).collect(Collectors.groupingBy(jerry -> jerry.color, Collectors.summarizingInt(t -> 1)));
        Map<String, List<Jerry>> collectOne = jerries.stream().collect(Collectors.groupingBy((jerry) -> jerry.color));

        IntSummaryStatistics stats = jerries.stream().mapToInt(Jerry::getYear).summaryStatistics();
        System.out.println(stats.getSum());
        System.out.println(stats.getMax());
        System.out.println(stats.getMin());
        System.out.println(stats.getCount());
        System.out.println(stats.getAverage());
        System.out.println(jerries.stream().mapToInt(Jerry::getYear).sum());
        System.out.println(jerries.stream().mapToInt(Jerry::getYear).max());
        System.out.println(jerries.stream().mapToInt(Jerry::getYear).min());
        System.out.println(jerries.stream().mapToInt(Jerry::getYear).count());
        System.out.println(jerries.stream().mapToInt(Jerry::getYear).average());


        jerries.sort(Comparator.comparing(Jerry::getYear));
        jerries.stream().sorted(Comparator.comparing(Jerry::getYear).reversed()).collect(Collectors.toList());
        jerries.sort(Comparator.comparing((Jerry jerry) -> jerry.getYear()));
        jerries.sort((a, b) -> {
            return (int) a.getYear() - b.getYear();
        });

    }


    public static void sortTet() {
        //装载因子
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>(10);
        map1.put("a", "a");
        map1.put("score", 2.6565f);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("b", "b");
        map2.put("score", 0.0f);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("c", "c");
        map3.put("score", 1.6565f);
        list.add(map1);
        list.add(map2);
        list.add(map3);

        float f = 0.5f;
        int i = (int) f;

        list.sort((a, b) -> {
            return (int) ((float) a.get("score") - (float) b.get("score"));
        });

        list.sort(Comparator.comparing((Map a) -> (float) a.get("score")));

        list.stream().sorted(Comparator.comparing((Map a) -> (float) a.get("score"))).collect(Collectors.toList());
    }
}
