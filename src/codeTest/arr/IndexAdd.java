package codeTest.arr;

import java.util.Arrays;

public class IndexAdd {
    public static void main(String[] args) {
        int[] arr = {0, 5, 14, 4, 6, 12, 20, 3, 19, 2, 15, 44, 3, 17, 4, 6, 10, 8, 17};
        printIndex(arr, 20);
    }

    //输出和等于N的数组下标
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
            while (arr[i] + arr[j] >= 20) {
                if (arr[i] + arr[j] == n)
                    System.out.println(arr[i] + ":" + arr[j]);
                j--;
            }
        }
    }
}
