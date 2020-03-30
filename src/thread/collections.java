package thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class collections {
    public static void main(String[] args) {
        String word = "key";
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap();
        Integer oldValue;
        Integer newValue;
        do {
            oldValue = map.get(word);
            newValue = oldValue == null ? 1 : oldValue + 1;
        } while (!map.replace(word, oldValue, newValue));

        map.compute(word, (k, v) -> v == null ? 1 : v + 1);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("cyclicBarrier");
        });
    }

}
