package codeTest.find;

public class CompareString {
    public static void main(String[] args) {
        String str1 = "abc346dfgdkasjdkfjkk3467";
        String str2 = "346f";
        System.out.println(contains(str1, str2));
        int[] arr = {1, 3, 5, 6, 8, 11, 13, 15, 25, 36};
        System.out.println(find(arr, 0, arr.length - 1, 32));
    }

    public static boolean contains(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        boolean bool = false;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[0]) {
                for (int j = 0; j < chars2.length; j++) {
                    if (j + i >= chars1.length)
                        return bool;
                    bool = chars1[j + i] == chars2[j];
                }
                if (bool == true)
                    return bool;
            }
        }
        return bool;
    }

    //二分查找
    public static int find(int[] arr, int low, int high, int key) {
        if (low >= high)
            return -1;
        int mid = (low + high) / 2;
        if (key == arr[mid])
            return mid;
        if (key == arr[low])
            return low;
        if (key == arr[high])
            return high;
        if (mid == high || mid == low)
            return -1;
        if (key > arr[mid])
            return find(arr, mid, high, key);
        else
            return find(arr, low, mid, key);
    }
}
