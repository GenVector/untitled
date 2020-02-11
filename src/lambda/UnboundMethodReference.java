package lambda;

import java.util.function.*;

class X {
    String f() {
        return "X::f()";
    }

    static String g() {
        return "X::f()";
    }
}

interface MakeString {
    String make();
}

interface TransformX {
    String transform(X x);
}

public class UnboundMethodReference {
    public static void main(String[] args) {
        MakeString ms = X::g; // [1]
        X x = new X();
        ms = x::f;
        TransformX sp = X::f;

        sp = new TransformX() {
            @Override
            public String transform(X x) {
                return x.f();
            }
        };
        sp = X::f;
        System.out.println(sp.transform(x)); // [2]
        System.out.println(x.f()); // 同等效果
    }
}

class This {
    void two(int i, double d) {
        System.out.println(i + "|" + d);
    }

    void three(int i, double d, String s) {
        System.out.println(i + "|" + d + "|" + s);

    }

    void four(int i, double d, String s, char c) {
        System.out.println(i + "|" + d + "" + s + "|" + c);

    }
}

interface TwoArgs {
    void call2(This athis, int i, double d);
}

interface ThreeArgs {
    void call3(This athis, int i, double d, String s);
}

interface FourArgs {
    void call4(
            This athis, int i, double d, String s, char c);
}

class MultiUnbound {
    public static void main(String[] args) {
        TwoArgs twoargs = This::two;
        ThreeArgs threeargs = This::three;
        FourArgs fourargs = This::four;
        This athis = new This();
        twoargs.call2(athis, 11, 3.14);
        threeargs.call3(athis, 11, 3.14, "Three");
        fourargs.call4(athis, 11, 3.14, "Four", 'Z');
    }
}


class Dog {
    String name;
    int age = -1; // For "unknown"

    Dog() {
        name = "stray";
    }

    Dog(String nm) {
        name = nm;
    }

    Dog(String nm, int yrs) {
        name = nm;
        age = yrs;
    }
}

interface MakeNoArgs {
    Dog make();
}

interface Make1Arg {
    Dog make(String nm);
}

interface Make2Args {
    Dog make(String nm, int age);
}

class CtorReference {
    public static void main(String[] args) {
        MakeNoArgs mna = Dog::new; // [1]
        Make1Arg m1a = Dog::new;   // [2]
        Make2Args m2a = Dog::new;  // [3]

        Dog dn = mna.make();
        Dog d1 = m1a.make("Comet");
        Dog d2 = m2a.make("Ralph", 4);
    }
}

@FunctionalInterface
interface Functional {
    String goodbye(String arg);

    boolean equals(Object obj);

    String toString();
}

interface FunctionalNoAnn {
    String goodbye(String arg);
}


class FunctionalAnnotation {
    public String goodbye(String arg) {
        return "Goodbye, " + arg;
    }

    public static void main(String[] args) {
        FunctionalAnnotation fa =
                new FunctionalAnnotation();
        Functional f = fa::goodbye;
        FunctionalNoAnn fna = fa::goodbye;
        Functional fl = a -> "Goodbye, " + a;
        FunctionalNoAnn fnal = a -> "Goodbye, " + a;
        System.out.println(fl.goodbye("shiny"));
    }
}

@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

class TriFunctionTest {
    static int f(int i, long l, double d) {
        return 99;
    }

    public static void main(String[] args) {
        TriFunction<Integer, Long, Double, Integer> tf =
                TriFunctionTest::f;
        tf = (i, l, d) -> i;
        System.out.println(tf.apply(1, 1l, 1.0));
    }
}