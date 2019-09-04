import java.util.ArrayList;
import java.util.List;

public class FilterTest {
    public static void filterTest() {
        List<Apple> appleList = new ArrayList<>();
        filter(appleList, new Filter() {
            @Override
            public boolean filter(Apple apple) {
                return false;
            }
        });
        filter(appleList, (Apple apple) -> {
            return apple.color.equals("red");
        });
    }

    public static boolean filter(List<Apple> appleList, Filter filter) {
        if (appleList != null)
            for (Apple apple : appleList) {
                if (!filter.filter(apple))
                    return false;
            }
        return true;
    }

}
@FunctionalInterface
interface Filter {
    boolean filter(Apple apple);
}

class Apple {
    String color;
    int weight;
}