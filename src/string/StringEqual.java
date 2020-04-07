package string;

public class StringEqual {
    public static void main(String[] args) {
        String str1 = "123";//会检查常量池
        //每次都新建一个新对象
        String str2 = new String("123");
        String str3 = new String("123");
        String str4 = new String("123");
        System.out.println(str1 == str2);
        System.out.println(str3 == str4);
        int a = 10 >> 1;
        int b = a++;
        int c = ++a;
        int d = b * a++;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(1<<4);
        System.out.println(4<<1);
    }
}
