package userless;

public class HJXTset {

    public static void main(String[] args) {
//      System.in******
        int x = 5;
        String str = "12 * 2 + (1 * 3 + x * 2) * 2";
        int sum = 50;
        test2(100, 4);
    }

    public static void test1(long m, long k) {
        long sum = 0l;
        for (long i = 0l; i <= m; i++) {
            sum += contains(i, k);
        }
        System.out.println(sum);
    }

    public static void test2(long m, long k) {
        long sum = 0l;
        for (long i = 0l; i <= m; i++) {
            sum += contains(i, k);
        }
        System.out.println(sum);
    }

    public static long contains(long i, long k) {
        while (i > 10) {
            if (i % 10 == k)
                return 1l;
            i = i / 10;
        }
        if (i % 10 == k)
            return 1l;
        return 0l;
    }
}
