package jconsole;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OOMObject {

    static class OOMObjectT {
        public byte[] placeholder = new byte[64 * 1024];

        public static void fillHeap(int num) throws InterruptedException {
            List<OOMObjectT> list = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                //稍作延时,令监视曲线的变化更加明显
                Thread.sleep(50);
                System.out.println(i);
                list.add(new OOMObjectT());
            }

        }

        public static void main(String[] args) throws Exception {
            System.out.println("start");
            fillHeap(1000);
            System.gc();
        }

    }

    static class LockThreadObjectT {
        public static void createBusyThread() {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) ;
                }
            }, "testBusyThread");
            thread.start();
        }

        /**
         * 线程锁等待演示
         */
        public static void createLockThread(final Object lock) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "testLockThread");
            thread.start();
        }

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            br.readLine();
            createBusyThread();
            br.readLine();
            Object obj = new Object();
            createLockThread(obj);
        }

    }

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();

        for (long i = 0L; i < 2000L; i++) {
            list.add(i);
        }
        Runnable runnable1 = () -> {
            for (long i = 2000L; i < 4000L; i++) {
                list.add(i);
            }
        };
        Runnable runnable2 = () -> {
            for (long i = 0L; i < 2000L; i++) {
                list.remove(i);
            }
        };
        Runnable runnable3 = () -> {
            List<Long> list1 = new ArrayList<>(list);
            System.out.println(list1.size() + " " + list.size());
            List<Long> list2 = new ArrayList<>(list);
            System.out.println(list2.size() + " " + list.size());
            List<Long> list3 = new ArrayList<>(list);
            System.out.println(list3.size() + " " + list.size());
            List<Long> list4 = new ArrayList<>(list);
            System.out.println(list4.size() + " " + list.size());
            List<Long> list5 = new ArrayList<>(list);
            System.out.println(list5.size() + " " + list.size());
            List<Long> list6 = new ArrayList<>(list);
            System.out.println(list6.size() + " " + list.size());
        };
        Runnable runnable4 = () -> {
            for (long i = 2000L; i < 4000L; i++) {
                list.remove(i);
            }
        };
        Runnable runnable5 = () -> {
            for (long i = 4000L; i < 6000L; i++) {
                list.add(i);
            }
        };
        Runnable runnable6 = () -> {
            for (long i = 6000L; i < 8000L; i++) {
                list.add(i);
            }
        };
        Runnable runnable7 = () -> {
            for (Long l : list) {
                System.out.print(l);
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);
        Thread thread4 = new Thread(runnable4);
        Thread thread5 = new Thread(runnable5);
        Thread thread6 = new Thread(runnable6);
        Thread thread7 = new Thread(runnable7);

        thread1.start();
        thread2.start();
        thread3.start();
        thread7.start();
        thread4.start();
        thread5.start();
        thread6.start();

    }


}