package thread.phaserAndCyclicBarrier;

import java.util.concurrent.*;

public class PhaserAndBarrier {

    public static void main(String[] args) {
        phaserTest();
        //cyclicBarrierTest2();
    }

    public static void phaserTest() {
        final Phaser phaser = new Phaser(5) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                //当所有线程都完成了一个任务的时候，会回调。
                System.out.println("完成了第" + phase + "个屏障");
                //true:后面的屏障无效。false:保持屏障的有效性
                return false;
            }
        };
        //线程数量
        int threadNum = 10;
        for (int i = 0; i < threadNum; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("开始" + finalI);
                        Thread.sleep(1000);
                        //与CyclicBarrier相同，都是等待所有线程到达屏障后，再统一释放
                        phaser.arriveAndAwaitAdvance();
                        System.out.println("结束" + finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    public static void cyclicBarrierTest() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                //执行所有线程全部完成后的逻辑
                try {
                    System.out.println("执行所有线程全部完成后的逻辑  开始");
                    Thread.sleep(3000);
                    System.out.println("执行所有线程全部完成后的逻辑   结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //子线程数量必须等于parties。否则将会一直进入等待
        int threadNum = 5;
        for (int i = 0; i < threadNum; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("开始" + finalI);
                        cyclicBarrier.await();
                        System.out.println("结束" + finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void cyclicBarrierTest2() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                //执行所有线程全部完成后的逻辑
                try {
                    System.out.println("执行所有线程全部完成后的逻辑  开始");
                    Thread.sleep(3000);
                    System.out.println("执行所有线程全部完成后的逻辑   结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //子线程数量必须等于parties。否则将会一直进入等待
        int threadNum = 5;
        for (int i = 0; i < threadNum; i++) {
            final int finalI = i;
            executorService.execute(()->{
                try {
                    System.out.println("开始" + finalI);
                    cyclicBarrier.await();
                    System.out.println("结束" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}

