package volatileTest;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * volatile变量自增运算测试
 *
 * @author zzm
 */
public class VolatileTest {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    //System.out.println("sub thread Group:" + Thread.currentThread().getThreadGroup());
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        System.out.println("main thread Group:" + Thread.currentThread().getThreadGroup());

//        ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
//        ThreadInfo[] threadInfos = tmxb.dumpAllThreads(false, false);
        // 遍历线程信息，打印出ID和名称
//        for (ThreadInfo info : threadInfos) {
//            System.out.println("[" + info.getThreadId() + "] " + info.getThreadName());
//        }
        //等待所有累加线程都结束
        while (Thread.activeCount() > 1) {
            ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
            ThreadInfo[] threadInfos = tmxb.dumpAllThreads(false, false);
            // 遍历线程信息，打印出ID和名称
            for (ThreadInfo info : threadInfos) {
                System.out.println("[" + info.getThreadId() + "] " + info.getThreadName());
            }
            System.out.println(Thread.activeCount());
            Thread.currentThread().getThreadGroup().activeGroupCount();
            Thread.currentThread().getThreadGroup().list();
            Thread.yield();
        }
        System.out.println(race);
    }
}