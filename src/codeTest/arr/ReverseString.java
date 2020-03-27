package codeTest.arr;

import java.util.Arrays;
import java.util.StringJoiner;

public class ReverseString {
    public static void main(String[] args) {
        String str = "hello world";
        System.out.println(reverseWord(str));
        int[] arr = {0, 5, 7, 4, 6, 10, 8, 17};
        Arrays.stream(reverse(arr)).forEach(System.out::println);

    }

    public static String reverseWord(String str) {
        if (str == null)
            return null;
        String[] strs = str.split(" ");
        if (strs == null || strs.length < 1)
            return null;
        int len = strs.length;
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int i = 1; i <= len; i++) {
            stringJoiner.add(strs[len - i]);
        }
        return stringJoiner.toString();
    }

    public static String reverse(String str, int len) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < len / 2; i++) {
            char tem = chars[i];
            chars[i] = chars[len - 1 - i];
            chars[len - 1 - i] = tem;
        }
        return new String(chars);
    }

    //奇数在左边,偶数在右边
    public static int[] reverse(int[] a) {
        int len = a.length;
        int i = 0, j = len - 1;
        int tmp = a[0];
        while (i < j) {
            while ((i < j) && a[j] % 2 == 0) {
                j--;
            }
            a[i] = a[j];
            while ((i < j) && a[i] % 2 != 0) {
                i++;
            }
            a[j] = a[i];
        }
        a[i] = tmp;
        return a;
    }
}
