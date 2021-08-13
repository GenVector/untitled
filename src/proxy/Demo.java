package proxy;

import java.lang.reflect.Proxy;

interface Run {
    void run(String[] args);

    void test();

}

class RunDemo2 implements Run {
    public void run(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    public void test() {
        System.out.println("test");
    }

    public static void main(String[] args) throws Throwable {
        String[] args1 = {"12", "23"};
        RunDemo demo = new RunDemo();
        CglibProxy cglibProxy = new CglibProxy();
        RunDemo run = (RunDemo) cglibProxy.CreatProxyedObj(demo.getClass());
        //run = (RunDemo) cglibProxy.CreatProxyedObj(run.getClass());
        run.test();
        run.run(args1);
    }
}


class RunDemo implements Run {
    public void run(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }

//    @Override
//    public String toString() {
//        return "123";
//    }

    public void test() {
        System.out.println("test");
    }

    public static void main(String[] args) throws Throwable {
        String[] args1 = {"12", "23"};
        RunDemo demo = new RunDemo();

        ProxyHandler proxyHandler = new ProxyHandler(demo);

        Run dynamicProxy = (Run) Proxy.newProxyInstance(demo.getClass().getClassLoader(),
                demo.getClass().getInterfaces(), proxyHandler);
         proxyHandler = new ProxyHandler(dynamicProxy);

        Run doubleDynamicProxy = (Run) Proxy.newProxyInstance(demo.getClass().getClassLoader(),
                dynamicProxy.getClass().getInterfaces(), proxyHandler);
        //doubleDynamicProxy.run(args1);
        doubleDynamicProxy.test();
    }
}
