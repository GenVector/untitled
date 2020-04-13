package codeTest.arr;

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
            for (int i = 0; i < arr2.length; i++) {
                try {
                    System.out.println(arr2[i]);
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        });
        thread1.start();
        try {
            Thread.sleep(10);

        } catch (Exception e) {

        }
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



