package leetCode;

public class ReverseInt {
    public static int reverse(int x) {
        if (x == 0)
            return 0;
        int ind = Math.abs(x);
        int num = 0;
        while (ind > 0) {
            int tem = ind % 10;
            if ((long) num * 10 > Integer.MAX_VALUE)
                return 0;
            num = num * 10 + tem;
            ind = ind / 10;
        }
        if (x < 0) {
            return num * -1;
        } else {
            return num;
        }
    }

    public static int reverse2(int x) {
        if (x == 0 || x >= Integer.MAX_VALUE || x <= Integer.MIN_VALUE)
            return 0;
        int ind = Math.abs(x);
        String numStr = String.valueOf(ind);
        char[] chars = numStr.toCharArray();
        char[] charsNew = new char[chars.length];
        int len = chars.length - 1;
        for (int i = 0; i <= len; i++) {
            charsNew[i] = chars[len - i];
        }
        int num;
        long lon = Long.valueOf(new String(charsNew));
        if (lon > Integer.MAX_VALUE)
            return 0;
        else num = (int) lon;
        if (x < 0) {
            return num * -1;
        } else {
            return num;
        }
    }

    public static void main(String[] args) {

        System.out.println(reverse2(214748368));
    }
}
