package test;

import gc.GCTest;
import gsy.com.*;

public class ImportTest {

    public static void main(String[] args) {
        System.out.println(Imp.str);
        Imp.str = "str";
        System.out.println(Imp.str);
        GCTest gcTest=new GCTest();
        gcTest.test(0);
    }
}
