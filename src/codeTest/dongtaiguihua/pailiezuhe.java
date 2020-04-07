package codeTest.dongtaiguihua;

import java.util.Stack;

import static codeTest.dongtaiguihua.pailiezuhe2.mergeMid;
import static codeTest.dongtaiguihua.pailiezuhe2.zuhe;

public class pailiezuhe {
    public static void main(String[] args) {
        int[] arr1 = {1, 5, 6, 7, 9, 2, 4, 8, 10, 13, 14};
        //mergeMid(arr1, 5);
        char[] c = {'a', 'b', 'c', 'd'};
        //for (int i = 1; i <= c.length; i++)
        //zuhe(c, 0, i, new Stack<>());

        int[] arr = {1, 2, 3, 4};
        arrNew(arr, 0, 3);

        int shu[] = {1, 2, 3, 4};

        //f(shu, 3, 0, 0); // 从这个数组4个数中选择三个
    }
    //合并有序数组:两个递增序列{1, 5, 6, 7, 9, 2, 4, 8, 10, 13, 14}
    public static void mergeMid(int arr[], int mid, int start, int end) {
        for (int i = start; i < mid; i++) {
            if (arr[i] > arr[mid]) {
                int tem = arr[i];
                arr[i] = arr[mid];
                arr[mid] = tem;
                for (int j = mid; j < end - 1; j++) {
                    if (arr[j + 1] < arr[j]) {
                        int temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void arrNew(int[] arr, int cur, int len) {
        if (cur == len) {
            System.out.println(stack);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (stack.contains(arr[i]))
                continue;
            stack.push(arr[i]);
            arrNew(arr, cur + 1, len);
            stack.pop();
        }
    }


    public static Stack<Integer> stack = new Stack<>();

    //可重复、全部排列组合 n的n次方

    /**
     * @param arr  待选择的数组
     * @param targ 要选择多少个次
     * @param cur  当前选择的是第几次
     */
    private static void allCanRepeat(int[] arr, int targ, int cur) {
        // TODO Auto-generated method stub
        if (cur == targ) {
            System.out.println(stack);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
            allCanRepeat(arr, targ, cur + 1);
            stack.pop();

        }
    }

    //不可重复
    private static void allCannotRepeat(int[] arr, int targ, int cur) {
        // TODO Auto-generated method stub
        if (cur == targ) {
            System.out.println(stack);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!stack.contains(arr[i])) {
                stack.push(arr[i]);
                allCannotRepeat(arr, targ, cur + 1);
                stack.pop();
            }
        }
    }

    //1不在第一位,2不在第二位
    public static void arr2(int[] arr, int len, int cur) {
        if (cur == len) {
            System.out.println(stack);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!stack.contains(arr[i]) && !(cur == 0 && arr[i] == 1) && !(cur == 1 && arr[i] == 2)) {
                stack.push(arr[i]);
                arr2(arr, len, cur + 1);
                stack.pop();
            }
        }
    }

    /**
     * @param shu  元素
     * @param targ 要选多少个元素
     * @param has  当前有多少个元素
     * @param cur  当前选到的下标
     *             <p>
     *             1    2   3     //开始下标到2
     *             1    2   4     //然后从3开始
     */
    //只组合不排列
    private static void f(int[] shu, int targ, int has, int cur) {
        if (has == targ) {
            System.out.println(stack);
            return;
        }

        for (int i = cur; i < shu.length; i++) {
            if (!stack.contains(shu[i])) {
                stack.add(shu[i]);
                f(shu, targ, has + 1, i);
                stack.pop();
            }
        }

    }

}

class pailiezuhe2 {
    public static void main(String[] args) {
        int[] arr = {1, 5, 6, 7, 9, 2, 4, 8, 10, 13, 14};
        pailiezuhe.mergeMid(arr, 5,0,arr.length);
    }

    public static void mergeMid(int arr[], int mid) {
        for (int i = 0; i < mid; i++) {
            if (arr[i] > arr[mid]) {
                int tem = arr[i];
                arr[i] = arr[mid];
                arr[mid] = tem;
                for (int j = mid; j < arr.length - 1; j++) {
                    if (arr[j + 1] < arr[j]) {
                        int temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    //字符串的组合！！没有排列 求"abc"的额所有组合方式
    public static void zuhe(char c[], int begin, int len, Stack<Character> str) {
        if (len == 0) {
            System.out.println(str);
            return;
        }
        if (begin == c.length)
            return;
        str.push(c[begin]);
        zuhe(c, begin + 1, len - 1, str);
        str.pop();
        zuhe(c, begin + 1, len, str);
    }

}

class dongtaiguihua2 {
    private static Stack<Integer> stack = new Stack<>();

    //有n级台阶，一个人每次上一级或者两级，问有多少种走完n级台阶的方法。
    public static int step(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        return step(n - 1) + step(n - 2);
    }

    public static void main(String[] args) {
        //System.out.println(step(4));
        int[] arr = {1, 2, 3, 4};
        //arr(arr, 2, 0);
        //arr(arr, 3, 0, 0);
    }

}
