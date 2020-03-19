package test;

public class MaxSubArr {
    public static void main(String[] args) {

    }

    //最大子数组
    public static void maxSubArrTest(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (max < sum)
                    max = sum;
            }
        }
        System.out.println(max);
    }

    //最大子数组
    public static void maxSubArrTest2(int[] arr) {
        if (arr == null || arr.length <= 1)
            return;
        int MAX = arr[0];
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            //如果小于0，则越加越小
            if (sum < 0)
                sum = arr[i];
            else
                sum += arr[i];
            if (sum > MAX)
                MAX = sum;
        }
        System.out.println(MAX);
    }

    //最大子数组
    public static void maxSubArrTest3(int[] arr) {
        if (arr == null || arr.length <= 1)
            return;
        int Sum = arr[0];   //临时最大值
        int MAX = arr[0];   //比较之后的最大值
        for (int i = 1; i < arr.length; i++) {
            Sum = Math.max(Sum + arr[i], arr[i]);
            if (Sum >= MAX)
                MAX = Sum;
        }
        System.out.println(MAX);
    }

    public static int max(int a, int b, int c) {
        int max = a;
        if (a < b)
            max = b;
        if (b < c)
            max = c;
        return max;
    }


    public static void test(int[] arr) {
        int num = arr.length;
        int end = arr[0];
        int max = arr[0];
        for (int i = 1; i < num; i++) {
            max = max(end, max, end + arr[i]);
            if (end < 0)
                end = arr[i];
            else
                end = end + arr[i];
        }
        System.out.println(max);
    }
}
