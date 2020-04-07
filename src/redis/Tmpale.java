package redis;

import org.apache.commons.codec.binary.StringUtils;
import org.jsoup.internal.StringUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tmpale {

    Lock lock = new ReentrantLock();

    public Object getData(String key) {
        String value = getByRedis(key);
        if (StringUtil.isBlank(value)) {
            if (lock.tryLock()) {
                value = getByDB(key);
                int tryNum = 0;
                if (StringUtil.isBlank(getByRedis(key))) {
                    boolean result = setRedisKV(key, value);
                    while (!result && tryNum < 10) {
                        result = setRedisKV(key, value);
                        tryNum++;
                    }
                }
            }
            return value;
        } else {
            try {
                Thread.sleep(100);
                value = getByRedis(key);
                return value;

            } catch (InterruptedException e) {
                return null;
            }
        }
    }

    public String getByDB(String key) {
        return "value";
    }

    public String getByRedis(String key) {
        return "value";
    }

    public boolean setRedisKV(String key, String value) {
        return true;
    }
}
