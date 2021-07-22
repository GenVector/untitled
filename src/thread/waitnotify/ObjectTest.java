package thread.waitnotify;
/*
    wait\notify test
 */
public class ObjectTest {
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub

        Object obj = new Object();

        // 母亲做馒头线程
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                // 做馒头
                System.out.println("母亲开始做馒头");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // 做完馒头发通知
                synchronized (obj) {
                    System.out.println("母亲: 馒头做好了,吃吧");
                    //obj.notify();// 通知一个等在obj上的一个线程继续运行
                    obj.notifyAll();// 通知所有等在Obj上的线程继续运行
                }

            }
        });
        t.start();

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                synchronized (obj) {
                    System.out.println("大明:我要吃馒头");
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }// 进入阻塞状态,直到其他线程发通知
                    System.out.println("大明:开始吃馒头");
                }

            }
        });
        t2.start();

        synchronized (obj) {
            System.out.println("小明:我要吃馒头");
            obj.wait();// 进入阻塞状态,直到其他线程发通知
            System.out.println("小明:开始吃馒头");
        }

    }
}
