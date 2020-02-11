package gc;

public class GCTest {

    public static void main(String[] args) {
//        MyTime myTime = new MyTime("name", "alive");
//        System.out.println(myTime.getSuperZt());
//        Rudolph rudolph = new Rudolph();
        int i=0;
        test(i);

    }

    public static void test(int i) {

        try {
            System.out.println(i);
            test(++i);
        }catch (Throwable e){
            System.out.println("stack_length:"+i);
        }

    }

    public GCTest() {

    }

}

class Soup1 {
    private Soup1() {
    }

    public static Soup1 makeSoup() { // [1]
        return new Soup1();
    }
}

class Soup2 {
    private Soup2() {
    }

    private static Soup2 ps1 = new Soup2(); // [2]

    public static Soup2 access() {
        return ps1;
    }

    public void f() {
    }
}


