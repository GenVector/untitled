package executors;

public class ThreadWaitInterruptDemo {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread waitThread = new Thread(() -> {
            ThreadWait threadWait = new ThreadWait(lock);
            threadWait.testWait();
        });
        waitThread.start();
        waitThread.interrupt();
    }
}
