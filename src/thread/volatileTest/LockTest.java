package thread.volatileTest;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
    public static void main(String[] args) {
        HashMap<String,String> map =new HashMap<>();
        ReadWriteLock readWriteLock =new ReentrantReadWriteLock();
    }
}
