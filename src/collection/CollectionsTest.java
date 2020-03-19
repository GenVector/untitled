package collection;

import codeTest.MyTest;
import timeAndExtends.Time;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionsTest {

    public static void main(String[] args) {
        //collectionsTest();
        //listTest();
        //setTest();
        mapTest();
    }

    public static void collectionsTest() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "shiny", "daisy", "raining");
        System.out.println(list.toString());
        //反转元素
        Collections.reverse(list);
        System.out.println(list.toString());
        Collections.sort(list);
        System.out.println(list.toString());

        //Comparator实现compare重写
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //指定比较规则，按照首字母降序来排列
                return o2.charAt(0) - o1.charAt(0);
            }
        });
        Collections.sort(list, (o1, o2) -> {
            return o2.charAt(o2.length() - 1) - o1.charAt(o1.length() - 1);
        });
        Arrays.asList("");
    }

    public static void listTest() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.add(null);
        arrayList.add(null);
        System.out.println(arrayList.toString());
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.add(null);
        System.out.println(arrayList.size());
        System.out.println(linkedList.size());
        List<Time> list = new ArrayList<>(Arrays.asList(new Time("3", "3"), new Time("1", "1")));
        List<Time> list1 = Arrays.asList(new Time("3", "3"), new Time("1", "1"));
        Arrays.asList(new Time("3", "3"), new Time("1", "1")).stream().collect(Collectors.toList());
    }

    public static void setTest() {
        //treeSet can not add null,but hashSet can
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.stream().forEach(str -> {
        });
        for (String string : treeSet) {

        }
        treeSet.iterator();
        //treeSet.add(null);
        Set<Object> hashSet = new HashSet<>();
        hashSet.add(null);
        hashSet.add(null);
        hashSet.add("3");
        hashSet.add("4");
        hashSet.add(3);
        hashSet.add(4);
        hashSet.stream().forEach(o -> System.out.println(o));
        System.out.println(hashSet.size());
    }

    public static void mapTest() {
        final MyTest myTest = new MyTest();
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(null, null);
        Map<String, String> treeMap = new TreeMap<>();
        //TreeMap can not put key=null
        //treeMap.put(null, null);
        //treeMap can not get key=null
        //treeMap.get(null);
        //treeSet can  put value=null
        treeMap.put("33", null);
        System.out.println(treeMap.get("33"));
        System.out.println(hashMap.get(null));
    }
}
