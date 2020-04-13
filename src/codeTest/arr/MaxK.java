package codeTest.arr;

import java.util.Arrays;
import java.util.LinkedList;

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

    //两个数组的中位数,可以理解为获取两个数组的第K小的数。K为(len1+len2)/2
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}

