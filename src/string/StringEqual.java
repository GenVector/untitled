package string;

public class StringEqual {
    public static void main(String[] args) throws Exception {
        testInt();
    }

    public static void main() {
        int a = 10 >> 1;
        int b = a++;
        int c = ++a;
        int d = b * a++;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(1 << 4);
        System.out.println(4 << 1);
    }

    public static void stringEqual() throws Exception {
        //System.out.println(Runtime.getRuntime().freeMemory());

        String str11 = String.valueOf(1234567891234L);
        String str21 = String.valueOf(1234567891234L);
        System.out.println(str11 == str21);
        String str2 = new String("123");

        String str1 = "123";//会检查常量池
        //每次都新建一个新对象
        String str3 = new String("123");
        String str4 = "123";
        String str5 = "12" + "3";
        System.out.println("123 == new String(123)  " + (str1 == str2));
        System.out.println("new String(123) == new String(123)  " + (str2 == str3));
        System.out.println("123 == 123  " + (str1 == str4));
        System.out.println("123 == 12+3  " + (str1 == str5));
        System.out.println("new String(123) == 12+3  " + (str2 == str5));

        System.out.println("new String(123).intern == 123  " + (str2.intern() == str1));
        char[] chars = {'a', 'v'};
        String s1 = new String(chars);
        String s2 = s1;
        System.out.println("------------ " + (s2 == s1));
        System.out.println("------------ " + ("av" == s1));
        System.out.println("------------ " + (s1.intern() == s1));
        System.out.println("------------ " + (s1 == s2));

    }

    public static void testInt() {
        int a = 1233;
        int num = 1233;
        Integer num2 = 1233;
        //拆包
        System.out.println(num == num2);
    }
}
