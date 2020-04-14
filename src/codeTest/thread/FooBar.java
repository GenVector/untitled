package codeTest.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FooBar {
    private int n;
    private volatile boolean status = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        try {
            int i = 0;
            while (i < n) {
                while (status) {
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    status = false;
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        try {
            int i = 0;
            while (i < n) {

                while (!status) {
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    status = true;
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        FooBar fooBar = new FooBar(3);
        fooBar.foo(() -> {
            System.out.print("foo");
        });
        fooBar.bar(() -> {
            System.out.print("bar");
        });
    }
}

class FooBar2 {
    private int n;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //模拟生产者消费者模式，用于判断共享变量是否为空
    private boolean status = true;

    public FooBar2(int n) {
        this.n = n;
    }

    //类似生产者offer数据
    public void foo(Runnable printFoo) throws InterruptedException {
        lock.lock();
        for (int i = 0; i < n; i++) {
            if (!status) {
                condition.await();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            status = false;

            condition.signal();

        }
        lock.unlock();

    }

    //类似消费者take数据
    public void bar(Runnable printBar) throws InterruptedException {
        lock.lock();
        for (int i = 0; i < n; i++) {

            if (status) {
                condition.await();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            status = true;

            condition.signal();

        }
        lock.unlock();

    }

    public static void main(String[] args) throws Exception {
        FooBar2 fooBar2 = new FooBar2(5);
        FooBar fooBar = new FooBar(4);
        FooBar3 fooBar3 = new FooBar3(4);
        FooBar4 fooBar4 = new FooBar4(4);
        new Thread(() -> {
            try {
                fooBar4.foo(() -> {
                    System.out.print("foo");
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar4.bar(() -> {
                    System.out.print("bar");
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }).start();
    }
}

class FooBar3 {
    private int n;
    private boolean fooTurn = true;
    private Object lock = new Object();

    public FooBar3(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (lock) {
                if (!fooTurn) lock.wait();
                fooTurn = false;
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                lock.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (lock) {
                if (fooTurn) lock.wait();
                fooTurn = true;
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {

    }
}

class FooBar4 {
    private int n;
    private CountDownLatch a;
    private CyclicBarrier barrier;// 使用CyclicBarrier保证任务按组执行

    public FooBar4(int n) {
        this.n = n;
        a = new CountDownLatch(1);
        barrier = new CyclicBarrier(2);// 保证每组内有两个任务
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        try {
            for (int i = 0; i < n; i++) {
                printFoo.run();
                a.countDown();// printFoo方法完成调用countDown
                barrier.await();// 等待printBar方法执行完成
            }
        } catch (Exception e) {
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        try {
            for (int i = 0; i < n; i++) {
                a.await();// 等待printFoo方法先执行
                printBar.run();
                a = new CountDownLatch(1); // 保证下一次依旧等待printFoo方法先执行
                barrier.await();// 等待printFoo方法执行完成
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {

    }
}