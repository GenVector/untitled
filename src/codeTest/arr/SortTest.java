package codeTest.arr;

import java.util.Arrays;

import static codeTest.dongtaiguihua.pailiezuhe.mergeMid;


public class SortTest {
    public static void main(String[] args) {
        int[] arr = {0, 5, 19, 2, 15, 44, 3, 17, 4, 6, 10, 8, 17};
        //heapSort(arr);
        mergeSort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }

    //冒泡排序
    public static void popSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 1; j < len - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    int tem = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tem;
                }
            }
        }

    }

    //选择排序
    public static void selectSort(int[] arr) {
        int len = arr.length;
        int max;
        for (int i = 0; i < len; i++) {
            max = 0;
            for (int j = 1; j < len - i; j++) {
                if (arr[j] > arr[max]) {
                    max = j;
                }
            }
            int tem = arr[max];
            arr[max] = arr[len - i - 1];
            arr[len - i - 1] = tem;
        }
    }

    //插入排序
    public static void insertSort(int[] arr) {
        int len = arr.length;
        for (int index = 1; index < len; index++) {//外层向右的index，即作为比较对象的数据的index
            int temp = arr[index];//用作比较的数据
            int leftindex = index - 1;
            while (leftindex >= 0 && arr[leftindex] > temp) {//当比到最左边或者遇到比temp小的数据时，结束循环
                arr[leftindex + 1] = arr[leftindex];
                leftindex--;
            }
            arr[leftindex + 1] = temp;//把temp放到空位上
        }
    }

    /*
        在数组（在0号下标存储根节点）中，容易得到下面的式子（这两个式子很重要）：
               1.下标为i的节点，父节点坐标为(i-1)/2；
               2.下标为i的节点，左子节点坐标为2*i+1，右子节点为2*i+2。
    */
    //堆排序
    private static void heapSort(int[] arr) {
        //创建堆
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            //将堆顶元素与末尾元素进行交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            //重新对堆进行调整
            adjustHeap(arr, 0, i);
        }
    }

    public static int getLeft(int parent) {
        return 2 * parent + 1;
    }

    /**
     * 调整堆
     *
     * @param arr    待排序列
     * @param parent 父节点
     * @param length 待排序列尾元素索引
     */
    private static void adjustHeap(int[] arr, int parent, int length) {
        //将temp作为父节点
        int temp = arr[parent];
        //左孩子公式
        int lChild = getLeft(parent);
        while (lChild < length) {
            //右孩子
            int rChild = lChild + 1;
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (rChild < length && arr[lChild] < arr[rChild]) {
                lChild++;
            }
            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= arr[lChild]) {
                break;
            }
            // 把孩子结点的值赋给父结点
            arr[parent] = arr[lChild];
            //选取孩子结点的左孩子结点,继续向下筛选
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        arr[parent] = temp;
    }


    public static void mergeSort(int[] a, int start, int end) {
        if (start < end) {//当子序列中只有一个元素时结束递归
            int mid = (start + end) / 2;//划分子序列
            mergeSort(a, start, mid);//对左侧子序列进行递归排序
            mergeSort(a, mid + 1, end);//对右侧子序列进行递归排序
            mergeMid(a, start, mid + 1, end);//合并
        }
    }

    public static void mergeMid(int arr[], int start, int mid, int end) {
        for (int i = start; i < mid; i++) {
            if (arr[i] > arr[mid]) {
                int tem = arr[i];
                arr[i] = arr[mid];
                arr[mid] = tem;
                for (int j = mid; j < end; j++) {
                    if (arr[j + 1] < arr[j]) {
                        int temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }


    //快速排序
    public static void quickSort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        if (array == null || array.length < 1)
            return;
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while (i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while (array[j] >= base && i < j) {
                j--;
            }
            // 再从左往右边找，直到找到比base值大的数
            while (array[i] <= base && i < j) {
                i++;
            }
            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;
        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    public static void merge(int[] arr, int left, int right) {
        if (left >= right)
            return;
        int mid = (left + right) / 2;
        merge(arr, left, mid);
        merge(arr, mid + 1, right);
        //merge(arr, left, mid, right);
    }
}
