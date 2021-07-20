package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 0;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask(callable);
        futureTask.run();
        Integer result = futureTask.get();
    }
}
