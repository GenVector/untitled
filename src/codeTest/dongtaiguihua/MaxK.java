package codeTest.dongtaiguihua;

public class MaxK {
    public static void main(String[] args) {
        int[] arr = {0, 5, 7, 4, 6, 10, 8, 17};
        //System.out.println(getMinK(arr, 3));
        System.out.println(quicksort(arr, 0, arr.length - 1, 4));
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

    static int quicksort(int[] arr, int l, int r, int k) {
        int pos = part(arr, l, r);
        if (pos - l + 1 == k)
            return arr[pos];
        else if (pos - l + 1 > k)
            return quicksort(arr, l, pos - 1, k);
        else
            return quicksort(arr, pos + 1, r, k - (pos - l + 1));
    }
}
