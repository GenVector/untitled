package codeTest.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
实现手法
1、synchronized
2、atomic CAS
3、ReentrantLock + condition
4、volatile CAS
5、CountDownLatch CyclicBarrier
 */

public class PrintArr {

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
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static volatile boolean status = true;

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < arr1.length; i++) {
//                    if(i==arr1.length){
//                        condition.signal();
//                    }
                    while (!status)
                        condition.await();
                    System.out.println(arr1[i]);
                    status = false;
                    condition.signal();
                }
                lock.unlock();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < arr2.length; i++) {
                    while (status)
                        condition.await();
                    System.out.println(arr2[i]);
                    status = true;
                    condition.signal();
                }
                lock.unlock();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        thread1.start();
        thread2.start();

    }
}

class PrintArr5 {
    private static Boolean i = true;
    private static Object lock = new Object();

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        Thread thread1 = new Thread(() -> {

            try {
                int j = 0;
                while (j < arr1.length) {
                    synchronized (lock) {
                        if (!i) {
                            lock.wait();
                        }
                        if (i) {
                            System.out.println(arr1[j]);
                            i = false;
                            j++;
                            lock.notify();
                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.print(e);
            }


        });
        Thread thread2 = new Thread(() -> {
            try {
                int j = 0;
                while (j < arr2.length) {
                    synchronized (lock) {
                        if (i) {
                            lock.wait();
                        }
                        if (!i) {
                            System.out.println(arr2[j]);
                            i = true;
                            j++;
                            lock.notify();
                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.print(e);

            }
        });
        thread1.start();
        thread2.start();
    }
}

class PrintArr6 {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < arr1.length; i++) {
                    System.out.print(arr1[i]);
                    countDownLatch.countDown();
                    cyclicBarrier.await();
                }
            } catch (Exception e) {
                System.out.print(e);
            }


        });
        Thread thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < arr2.length; i++) {
                    countDownLatch.await();
                    System.out.print(arr2[i]);
                    countDownLatch = new CountDownLatch(1);
                    cyclicBarrier.await();
                }
            } catch (Exception e) {
                System.out.print(e);

            }
        });
        thread1.start();
        thread2.start();
    }
}






