package codeTest.find;

public class BinFind {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 8, 11, 13, 15, 25, 36};
        int[] arr2 = {11, 13, 15, 25, 36, 1, 3, 5, 6, 8};
        System.out.println(binFind(arr2, 0, arr.length - 1, 1));
        System.out.println(binFind2(arr2, 0, arr.length - 1, 3));
    }

    //二分查找
    public static int binFind(int[] arr, int low, int high, int key) {
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
            return binFind(arr, mid + 1, high, key);
        else
            return binFind(arr, low, mid - 1, key);
    }

    //二分查找2:翻转后的二分查找
    public static int binFind2(int[] arr, int low, int high, int key) {
        if (low >= high)
            return -1;
        int mid1, mid2;
        int tem = (low + high) % 2;
        if (tem == 0) {
            mid2 = (low + high) / 2;
            mid1 = (low + high) / 2 - 1;
        } else {
            mid2 = (low + high) / 2 + 1;
            mid1 = (low + high) / 2;
        }
        if (key == arr[mid1])
            return mid1;
        if (key == arr[mid2])
            return mid2;
        if (key == arr[low])
            return low;
        if (key == arr[high])
            return high;
        if (mid1 == high || mid1 == low)
            return -1;
        if (mid2 == high || mid2 == low)
            return -1;
        if (key > arr[mid2])
            return binFind(arr, mid2 + 1, high, key);
        else
            return binFind(arr, low, mid1 - 1, key);
    }
}
