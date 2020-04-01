package thread;

/*** 单例模式示例,双重锁定检查*/
public class DoubleCheckedLockingSingleton {
    private volatile DoubleCheckedLockingSingleton INSTANCE;

    private DoubleCheckedLockingSingleton() {
    }

    public DoubleCheckedLockingSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                //double checking Singleton instance
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return INSTANCE;
    }
}

/*** 使用 Java 枚举的单例模式示例*/
enum EasySingleton {
    INSTANCE;
}

/*** 单例模式示例与静态工厂方法*/
class Singleton {
    //initailzed during class loading
    private static final Singleton INSTANCE = new Singleton();
    //to prevent creating another instance of Singleton
    private Singleton() {
    }

    public static Singleton getSingleton() {
        return INSTANCE;
    }
}