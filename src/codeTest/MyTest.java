package codeTest;

import codeTest.dongtaiguihua.MaxSubArr;

import static codeTest.dongtaiguihua.MaxSubArr.maxSubArrTest;
import static codeTest.dongtaiguihua.MaxSubArr.maxSubArrTest2;
import static codeTest.dongtaiguihua.MaxSubArr.maxSubArrTest3;

public class MyTest {

    private final String param;
    private final int size;
    private static final String config = "ss";

    public MyTest() {
        param = "ss";
        size = 2;
    }

    public static void main(String[] args) {
        int[] arr = {1, -1, 15, -36, -9, 13, 45, 9, 15, -1, 2, 4, -5, -2};

        String a = "23221312asdafa";
        String b = "23221312Aasdafa";
        String items[][] = {
                {"财经1", "财经2", "财经3", "财经4", "财经5"},
                {"时政1", "时政2", "时政3", "时政4", "时政5"},
                {"体育I", "体育II", "体育III", "体育IV", "体育V", "体育VI", "体育VII"}
        };
        int weight[] = {2, 1, 3};
        //最大子数组和
        maxSubArrTest(arr);
        //最大字数组和2
        maxSubArrTest2(arr);
        //最大字数组和3
        maxSubArrTest3(arr);
        //时政、财经、体育输出
        //productTest(items, weight);
        MaxSubArr.maxSubArrTest4(arr);
    }

    //时政、财经、体育输出
    public static void productTest(String[][] items, int[] weight) {

        int max = 0;
        for (int i = 0; i < weight.length; i++) {
            int len;
            if (items[i].length % weight[i] == 0) {
                len = items[i].length / weight[i];

            } else
                len = items[i].length / weight[i] + 1;
            max = Math.max(max, len);
        }
        System.out.println(items[0].length);
        System.out.println(items[1].length);
        System.out.println(items[2].length);
        System.out.println(max);
        for (int k = 1; k <= max; k++) {
            int i = 0;
            for (; i < weight.length; i++) {
                int j = 0;
                for (; j < weight[i]; j++)
                    if (items[i].length > weight[i] * k - weight[i] + j)
                        System.out.print(items[i][weight[i] * k - weight[i] + j] + " ");
            }
            System.out.println();
        }
    }


}
