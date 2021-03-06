package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java 程序通过强制循环等待来创建死锁。 * *
 */
public class DeadLockDemo {
    /*
     * 此方法请求两个锁,第一个字符串,然后整数
     */
    public void method1() {
        synchronized (String.class) {
            System.out.println("Aquired lock on String.class object");
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
            synchronized (Integer.class) {
                System.out.println("Aquired lock on Integer.class object");
            }
        }
        System.out.println("1 finish lock ");

    }
    /*          * 此方法也请求相同的两个锁,但完全
     * 相反的顺序,即首先整数,然后字符串。
     * 如果一个线程持有字符串锁,则这会产生潜在的死锁
     * 和其他持有整数锁,他们等待对方,永远。     */

    public void method2() {
        synchronized (Integer.class) {
            System.out.println("2 Aquired lock on Integer.class object");
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }

            synchronized (String.class) {
                System.out.println("2 Aquired lock on String.class object");
            }
        }
        System.out.println("2 finish lock ");

    }

    static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        executorService.execute(() -> deadLockDemo.method1());
        executorService.execute(() -> deadLockDemo.method2());
        executorService.shutdown();
    }
}

