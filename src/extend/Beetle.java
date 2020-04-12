// reuse/Beetle.java
// The full process of initialization
package extend;

class Insect {
    private int i = 9;
    protected int j;

    Insect() {
        System.out.print("Insect constructor ");
        System.out.println("i = " + i + ", j = " + j);
        j = 39;
    }

    private static int x1 = printInit("static Insect.x1 initialized");

    private int x4 = printInit("Insect.x4 initialized");
    private int x2 = printInit("Insect.x2 initialized");
    private int x3 = printInit("Insect.x3 initialized");

    public static void method(){
        System.out.println("Insect static method");
    }

    static int printInit(String s) {
        System.out.println(s);
        return 47;
    }
}

public class Beetle extends Insect {
    private int k = printInit("Beetle.k.initialized");

    final  int i=7;

    private static int x2 = printInit("static Beetle.x2 initialized");

    public Beetle() {
        //i=8;
        System.out.print("Beetle constructor ");
        System.out.print("k = " + k);
        System.out.println(" j = " + j);
    }

    public static void method(){
        System.out.println("Beetle static method");
    }


    public static void main(String[] args) {
        //初始化class静态变量、进入主方法main(也是静态方法)。静态方法并不会被执行
        System.out.println("init Beetle");
        //初始化对象,先初始化基类实例,后初始化子类实例。新初始化类变量,后构造方法。初始化变量的顺序和变量定义的顺序一致
        Beetle b = new Beetle();
    }
}