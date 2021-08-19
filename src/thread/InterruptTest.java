package thread;

public class InterruptTest {
    static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //System.out.println("sub thread Group:" + Thread.currentThread().getThreadGroup());
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " | " + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(" error" + e);
                    return;
                }

            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        System.out.println(thread1.isInterrupted());
        System.out.println(thread1.isInterrupted());
        Thread.sleep(1000L);
        thread1.interrupt();
        System.out.println(thread1.isInterrupted());
        System.out.println(thread1.isInterrupted());

    }
}
