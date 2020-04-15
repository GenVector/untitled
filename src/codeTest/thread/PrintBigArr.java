package codeTest.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintBigArr {
    static volatile AtomicInteger i = new AtomicInteger(1);

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        new Thread(() -> {
            while (i.get() <= arr.length)
                if (i.get() % 3 == 1) {
                    System.out.println(arr[i.get() - 1] + " ");
                    i.getAndIncrement();
                }
        }).start();
        new Thread(() -> {
            while (i.get() <= arr.length)
                if (i.get() % 3 == 2) {
                    System.out.println(arr[i.get() - 1] + " ");
                    i.getAndIncrement();
                }
        }).start();
        new Thread(() -> {
            while (i.get() <= arr.length)
                if (i.get() % 3 == 0) {
                    System.out.println(arr[i.get() - 1] + " ");
                    i.getAndIncrement();
                }
        }).start();
    }
}

