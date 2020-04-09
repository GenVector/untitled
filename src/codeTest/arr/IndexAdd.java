package codeTest.arr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IndexAdd {
    public static void main(String[] args) {
        int[] arr = {0, 5, 14, 4, 6, 12, 20, 3, 19, 2, 15, 44, 3, 17, 4, 6, 10, 8, 17};
        printIndex2(arr, 20);
        System.out.println();
        printIndex(arr, 20);
    }

    //输出和等于N的数组下标
    //元素是否可以重复是个关键问题,如果不重复,可以借助MAP
    //找index和找数也不太一样
    public static void printIndex(int[] arr, int n) {
        Arrays.sort(arr);
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            while (arr[i] + arr[j] <= n) {
                if (arr[i] + arr[j] == n)
                    System.out.println(arr[i] + ":" + arr[j]);
                i++;
            }
            while (arr[i] + arr[j] >= n) {
                if (arr[i] + arr[j] == n)
                    System.out.println(arr[i] + ":" + arr[j]);
                j--;
            }
        }
    }

    public static void printIndex2(int[] arr, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(n - arr[i])) {
                System.out.println(arr[i] + ":" + arr[map.get(n - arr[i])]);
                map.remove(n - arr[i]);
                map.remove(arr[i]);
            }
        }
    }
}
