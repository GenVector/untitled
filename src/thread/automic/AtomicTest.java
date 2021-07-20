package thread.automic;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic变量自增运算测试
 *
 * @author zzm
 */
public class AtomicTest {
    public static AtomicInteger race = new AtomicInteger(0);

    public String name;

    public static void increase() {
        race.incrementAndGet();
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS_COUNT];
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1)
            Thread.yield();
        System.out.println(Instant.now().toEpochMilli() - start);
        System.out.println(race);
    }

    int c = 1;
    static int d = 1;

    //基本数据类型不能在线程间共享
//    public void basicNum() {
//        int i = 1;
//        String o1 = new String("1");
//        final AtomicTest atomicTest = new AtomicTest();
//        //o1 = "22";
//        i++;
//        new Thread(() -> {
//            AtomicInteger atomicInteger = new AtomicInteger(i);
//            int b = i + 1;
//        }).start();
//    }


}