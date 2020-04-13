package codeTest.arr;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class PrintArr {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < arr1.length; i++) {
                try {
                    synchronized (PrintArr.class) {
                        System.out.println(arr1[i]);
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {

                }
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(10);

            } catch (Exception e) {

            }
            for (int i = 0; i < arr2.length; i++) {
                try {
                    System.out.println(arr2[i]);
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        });
        thread1.start();

        thread2.start();
    }
}


class PrintArr2 {
    private static volatile boolean status = true;

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        Thread thread1 = new Thread(() -> {
            int i = 0;
            while (true) {
                if (status && i < arr1.length) {
                    System.out.println(arr1[i]);
                    status = false;
                    i++;
                }
                if (i == arr1.length)
                    break;
            }


        });
        Thread thread2 = new Thread(() -> {
            int i = 0;
            while (true) {
                if (!status && i < arr1.length) {
                    System.out.println(arr2[i]);
                    status = true;
                    i++;
                }
                if (i == arr2.length)
                    break;
            }
        });
        thread1.start();
        thread2.start();
    }
}

class PrintArr3 {
    private static volatile AtomicInteger cur = new AtomicInteger(1);

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        Thread thread1 = new Thread(() -> {
            int i = 0;
            while (true) {
                if (cur.get() % 2 != 0 && i < arr1.length) {
                    System.out.println(arr1[i]);
                    cur.incrementAndGet();
                    i++;
                }
                if (i == arr1.length)
                    break;
            }


        });
        Thread thread2 = new Thread(() -> {
            int i = 0;
            while (true) {
                if (cur.get() % 2 == 0 && i < arr1.length) {
                    System.out.println(arr2[i]);
                    cur.incrementAndGet();
                    i++;
                }
                if (i == arr2.length)
                    break;
            }
        });
        thread1.start();
        thread2.start();
    }
}

class PrintArr4 {
    private static Boolean i = true;

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        Thread thread1 = new Thread(() -> {
            synchronized (i) {
                try {
                    int j = 0;
                    while (true) {
                        if (!i) {
                            i.wait();
                        }
                        if (i) {
                            System.out.println(arr1[j]);
                            i = false;
                            j++;
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.print(e);
                }
            }

        });
        Thread thread2 = new Thread(() -> {
            synchronized (i) {
                try {
                    int j = 0;
                    while (true) {

                        if (i) {
                            i.wait();
                        }
                        if (!i) {
                            System.out.println(arr2[j]);
                            i = true;
                            j++;
                            i.notify();
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.print(e);
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}



