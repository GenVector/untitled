package codeTest.number;

public class Number {
    public static void main() {
        for (int m = 2; m < 100; m++) {
            int k;  // m 的平方根

            k = (int) Math.floor(Math.sqrt((double) m));
            // 求平方根，注意sqrt()的参数为 double 类型，这里要强制转换m的类型
            int i;
            for (i = 2; i <= k; i++)
                if (m % i == 0)
                    break;
            // 如果完成所有循环，那么m为素数
            // 注意最后一次循环，会执行i++，此时 i=k+1，所以有i>k
            if (i > k)
                System.out.println("是素数" + m);
            else
                System.out.println("不是素数" + m);
        }
    }
}
