package thread.executors;

public class JoinDemo extends Thread {
    int i;
    Thread previousThread; //上一个线程

    public JoinDemo(Thread previousThread, int i) {
        this.previousThread = previousThread;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            //调用上一个线程的join方法，大家可以自己演示的时候可以把这行代码注释掉
            previousThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("num:" + i);
    }

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();

        for (int i = 0; i < 10; i++) {
            JoinDemo joinDemo = new JoinDemo(previousThread, i);
            joinDemo.start();
            previousThread = joinDemo;
        }
    }
}

class ThreadNotify {

    private Object lock;

    public ThreadNotify(Object lock) {
        this.lock = lock;
    }

    public void testNotify() {
        try {
            synchronized (lock) {
                System.out.println("start notify........");
                lock.notify();
                System.out.println("end notify........");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ThreadWait {

    private Object lock;

    public ThreadWait(Object lock) {
        this.lock = lock;
    }

    public void testWait() {
        try {
            synchronized (lock) {
                System.out.println("start wait........");
                lock.wait();
                System.out.println("end wait........");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ThreadWaitNotifyDemo {
    public static void main(String[] args) throws Exception {
        Object lock = new Object();
        Thread waitThread = new Thread(() -> {
            ThreadWait threadWait = new ThreadWait(lock);
            threadWait.testWait();
        });
        Thread notifyThread = new Thread(() -> {
            ThreadNotify threadNotify = new ThreadNotify(lock);
            threadNotify.testNotify();
        });
        waitThread.start();
        /**
         * 保证waitThread一定会先开始启动
         */
        Thread.sleep(1000);
        notifyThread.start();
    }
}

