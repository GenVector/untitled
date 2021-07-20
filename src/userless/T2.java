package userless;

import visitcontrol.ProtectClassT1;

public class T2 extends ProtectClassT1 {

    private void test() {
        getStr();
    }

    public static void main(String[] args) {
        ProtectClassT1 t1 = new ProtectClassT1();

        T2 t2 = new T2();
        t2.getStr();
        t2.test();
    }
}