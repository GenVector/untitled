package codeTest.arr;

import java.util.Arrays;

public class MaxK {
    public static void main(String[] args) {
        int[] arr = {10, 5, 7, 4, 6, 0, 8, 17, 1, 2, 3, 30, 31};
        int[] arr2 = {10, 5, 7, 4, 15, 31};
        //System.out.println(getMinK(arr, 3));
        System.out.println(quicksort2(arr2, 0, arr2.length - 1, 31));
//        int k = quicksort2(arr, 0, arr.length - 1, 4);
//        for (int i = 0; i <= k; i++) {
//            System.out.print(arr[i] + " ");
//        }
    }


    public static int part(int[] a, int low, int high) {
        int pivot = a[low];
        while (low < high) {
            while (low < high && a[high] >= pivot)
                high--;
            a[low] = a[high];
            while (low < high && a[low] <= pivot)
                low++;
            a[high] = a[low];
        }
        a[low] = pivot;
        return low;
    }

    //求大
    public static int part2(int[] a, int low, int high) {
        int pivot = a[low];
        while (low < high) {
            while (low < high && a[high] <= pivot)
                high--;
            a[low] = a[high];
            while (low < high && a[low] >= pivot)
                low++;
            a[high] = a[low];
        }
        a[low] = pivot;
        return low;
    }

    static int quicksort(int[] arr, int l, int r, int k) {
        int pos = part2(arr, l, r);
        if (pos == k)
            return arr[pos];
        else if (pos - l + 1 > k)
            return quicksort(arr, l, pos - 1, k);
        else
            return quicksort(arr, pos + 1, r, k);
    }

    //求top k 全部
    static int quicksort2(int[] arr, int l, int r, int k) {
        if (k > arr.length - 1)
            return -1;
        int pos = quick(arr, l, r);
        if (pos == k)
            return arr[pos];
        else if (pos > k)
            return quicksort2(arr, l, pos - 1, k);
        else
            return quicksort2(arr, pos + 1, r, k);
    }

    public static int quick(int[] arr, int low, int high) {
        int tem = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (tem <= arr[j] && i < j) {
                j--;
            }
            arr[i] = arr[j];
            while (tem >= arr[i] && i < j) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = tem;
        return i;
    }


}
