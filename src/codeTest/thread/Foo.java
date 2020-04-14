package codeTest.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo {
    private volatile int mark = 1;

    public Foo() {

    }

    public void one() {
        System.out.print("one");
    }

    public void two() {
        System.out.print("two");
    }

    public void three() {
        System.out.print("three");
    }

    public void first(Runnable printFirst) {
        try {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            mark = 2;
            synchronized (this) {
                notifyAll();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void second(Runnable printSecond) {
        try {
            // printSecond.run() outputs "second". Do not change or remove this line.
            while (mark != 2) {
                synchronized (this) {
                    wait();
                }
            }
            mark = 3;
            printSecond.run();
            synchronized (this) {
                notifyAll();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void third(Runnable printThird) {

        try {
            // printThird.run() outputs "third". Do not change or remove this line.
            while (mark != 3) {
                synchronized (this) {
                    wait();
                }
            }
            printThird.run();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Foo foo = new Foo();
        new Thread(()->{
            foo.second(foo::two);
        }).start();
        new Thread(()->{
            foo.first(foo::one);
        }).start();
        new Thread(()->{
            foo.third(foo::three);
        }).start();



    }

}

class Foo2 {

    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger secondJobDone = new AtomicInteger(0);

    public Foo2() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first".
        printFirst.run();
        // mark the first job as done, by increasing its count.
        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstJobDone.get() != 1) {
            // waiting for the first job to be done.
        }
        // printSecond.run() outputs "second".
        printSecond.run();
        // mark the second as done, by increasing its count.
        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondJobDone.get() != 1) {
            // waiting for the second job to be done.
        }
        // printThird.run() outputs "third".
        printThird.run();
    }

    public static void main(String[] args) throws Exception {
        Foo2 foo2 = new Foo2();
        Foo foo = new Foo();
        foo2.first(foo::one);
        foo2.third(foo::two);
        foo2.second(foo::three);

    }
}
