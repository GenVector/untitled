package codetest;

public class NumberTest {
    public static void main(String[] args) {
        int a = 5;
        int b = 1;
        a = -b;
        //System.out.println(a);
        literals();
    }

    public static void literals() {
        int i1 = 0x2f; // 16进制 (小写)
        System.out.println("i1: " + Integer.toBinaryString(i1) + " " + i1);
        int i2 = 0X2F; // 16进制 (大写)
        System.out.println("i2: " + Integer.toBinaryString(i2) + " " + i2);
        int i3 = 0177; // 8进制 (前导0)
        System.out.println("i3: " + Integer.toBinaryString(i3) + " " + i3);
        char c = 0xffff; // 最大 char 型16进制值
        System.out.println("c: " + Integer.toBinaryString(c) + ": " + (int) c);
        byte b = 0x7f; // 最大 byte 型16进制值  10101111;
        System.out.println("b: " + Integer.toBinaryString(b) + " " + b);
        short s = 0x7fff; // 最大 short 型16进制值
        System.out.println("s: " + Integer.toBinaryString(s) + " " + s);
        long n1 = 200L; // long 型后缀
        long n2 = 200l; // long 型后缀 (容易与数值1混淆)
        long n3 = 200;

        // Java 7 二进制字面值常量:
        byte blb = (byte) 0b00110101;
        System.out.println("blb: " + Integer.toBinaryString(blb));
        short bls = (short) 0B0010111110101111;
        System.out.println("bls: " + Integer.toBinaryString(bls) + " " + bls);
        int bli = 0b00101111101011111010111110101111;
        System.out.println("bli: " + Integer.toBinaryString(bli) + " " + bli);
        long bll = 0b00101111101011111010111110101111;
        System.out.println("bll: " + Long.toBinaryString(bll) + " " + bll);
        float f1 = 1;
        float f2 = 1F; // float 型后缀
        float f3 = 1f; // float 型后缀
        double d1 = 1d; // double 型后缀
        double d2 = 1D; // double 型后缀
        // (long 型的字面值同样适用于十六进制和8进制 )
    }
}

// operators/Underscores.java
class Underscores {
    public static void main(String[] args) {
        double d = 341_435_936.445_667;
        System.out.println(d);
        int bin = 0b0010_1111_1010_1111_1010_1111_1010_1111;
        System.out.println(Integer.toBinaryString(bin));
        System.out.printf("%x%n", bin); // [1]
        long hex = 0x7f_e9_b7_aa;
        System.out.printf("%x%n", hex);
    }
}

class LongAndInt {
    public static void main(String[] args) {
        int i = Integer.MAX_VALUE;
        long l = Integer.MAX_VALUE;
        long l1 = l + i;
        System.out.println(i + ": " + l);
        System.out.println(l1 + ": " + (l + i));
        l += 1;
        i += 1;
        System.out.println(i + ": " + l);
        l += 50000;
        System.out.println(i + ": " + l);
    }
}
