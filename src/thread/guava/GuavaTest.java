package thread.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaTest {
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {

            @Override
            public Integer call() {
                // TODO Auto-generated method stub
                // 做馒头
                System.out.println("母亲开始做馒头");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1;
            }
        };
        ExecutorService jPool = Executors.newFixedThreadPool(10);
        ListeningExecutorService gPool = MoreExecutors.listeningDecorator(jPool);
        ListenableFuture<Integer> future = gPool.submit(callable);
//        Futures.addCallback(future, );
//        future.addListener();
    }

}
