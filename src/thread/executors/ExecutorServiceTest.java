package thread.executors;

import java.util.concurrent.*;

public class ExecutorServiceTest {


    static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //System.out.println("sub thread Group:" + Thread.currentThread().getThreadGroup());
            for (int i = 0; i < 10000; i++) {
                System.out.println("i");
            }
        }
    };
    static Callable<Future> callable = new Callable<Future>() {
        @Override
        public Future call() throws Exception {
            return new FutureTask(runnable, 0);

        }
    };

    public static void main(String[] args) {
        //自定义线程池
        //参数说明
        //corePoolSize:核心线程池数量int
        //maximumPoolSize:最大线程池大小
        //keepAliveTime:线程最大空闲时间
        //TimeUnit:时间单位
        //ThreadFactory:线程工厂
        //workQueue:线程等待列队
        //handle:拒绝策略
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 50, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(20));

        //带缓存的线程池
        ExecutorService cachePool = Executors.newCachedThreadPool();
        //它俩是一样的(核心线程为0)
        ExecutorService cachePool1 = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        //单线程池
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());


        //创建一个支持定时、延时任务及周期性的任务执行的线程池
        //既然这样,是不是也可以代替任务调度
        // 在这之前的实现需要依靠Timer和TimerTask或者其它第三方工具来完成。但Timer有不少的缺陷
        //Runnable对象或者Callable对象。会把传入的任务封装成一个RunnableScheduledFuture对象，其实也就是ScheduledFutureTask对象
        ExecutorService scheduledPool = Executors.newScheduledThreadPool(5);
        ExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        ((ScheduledExecutorService) scheduledPool).scheduleAtFixedRate(runnable, 100, 10, TimeUnit.HOURS);
        ((ScheduledExecutorService) scheduledPool).scheduleWithFixedDelay(runnable, 100, 10, TimeUnit.HOURS);
        //FutureTask;
        //Callable;


        //固定大小的线程池
        //核心线程池大小和最大线程池大小均为5
        //目前只有它在不shut down的情况下有内存泄漏情况
        //没有持续存活时间
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        fixedThreadPool.shutdown();

        //任务窃取线程池
        //注意,它和上面的几个都不太一样
        //ForkJoinPool 主要用于实现“分而治之”的算法，特别是分治之后递归调用的函数
        //ForkJoinPool 最适合的是计算密集型的任务，如果存在 I/O，线程间同步，sleep() 等会造成线程长时间阻塞的情况时，最好配合使用 ManagedBlocker。
        ExecutorService workStealingPool = Executors.newWorkStealingPool(5);
        //参数 parallel并行数
        //workerThreadFactory
        //是否启用异步asyncMode
        new ForkJoinPool
                (5, ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                        null, true);
        CountDownLatch countDownLatch = new CountDownLatch(7);
        CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + " world").thenAccept(System.out::println);
    }
}
