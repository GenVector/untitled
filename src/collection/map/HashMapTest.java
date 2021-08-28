package collection.map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>(8, 0.5f);
        map.put("test1", "test");
        map.put("test2", "test2");
        map.put("test3", "test3");
        map.put("test4", "test4");
        map.put("test5", "test5");
        map.put("test6", "test6");
        map.get("test6");
        map.remove("test6");
        String str = map.put("test1", "test1");
        System.out.println(str);

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("", "");
    }
}
