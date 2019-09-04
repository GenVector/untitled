import java.util.*;
import java.util.stream.Collectors;

public class CollectionsTest {

    public static void collectionsTest(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"shiny","daisy","raining");
        Collections.sort(list);
        Collections.reverse(list);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //指定比较规则，按照首字母降序来排列
                return o2.charAt(0) - o1.charAt(0);
            }
        });
        Collections.sort(list,(o1,o2)->{ return o2.charAt(o2.length()-1)-o1.charAt(o1.length()-1);});
        Arrays.asList("");
    }

    public void collectionTest() {
        final MyTest myTest = new MyTest();

        List<String> arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.add(null);
        arrayList.add(null);
        List<String> linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.add(null);
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(null, null);
        Map<String, String> treeMap = new TreeMap<>();
        //TreeMap can not put key=null
        //treeMap.put(null, null);
        //treeMap can not get key null
        //treeMap.get(null);
        //treeSet can not add null
        Set<String> treeSet = new TreeSet<>();
        //treeSet.add(null);

        Set<Object> hashSet = new HashSet<>();
        hashSet.add(null);
        hashSet.add(null);
        hashSet.add("3");
        hashSet.add("4");
        hashSet.add(3);
        hashSet.add(4);

        hashSet.stream().forEach(o -> System.out.println(o));
        treeMap.put("33", null);
        System.out.println(arrayList.size());
        System.out.println(linkedList.size());
        System.out.println(hashMap.get(null));
        System.out.println(hashSet.size());

        List<Time> list = new ArrayList<>(Arrays.asList(new Time("3", "3"), new Time("1", "1")));
        List<Time> list1 = Arrays.asList(new Time("3", "3"), new Time("1", "1"));
        Arrays.asList(new Time("3", "3"), new Time("1", "1")).stream().collect(Collectors.toList());

    }
}
