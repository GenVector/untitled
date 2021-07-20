package proxy;

import java.lang.reflect.Proxy;

interface Run {
    void run(String[] args);
}

class RunDemo2 implements Run {
    public void run(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    public static void main(String[] args) throws Throwable {
        String[] args1 = {"12", "23"};
        RunDemo demo = new RunDemo();
        CglibProxy cglibProxy = new CglibProxy();
        RunDemo run = (RunDemo) cglibProxy.CreatProxyedObj(RunDemo.class);

        run.run(args1);
    }
}


class RunDemo implements Run {
    public void run(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    public static void main(String[] args) throws Throwable {
        String[] args1 = {"12", "23"};
        RunDemo demo = new RunDemo();

        ProxyHandler proxyHandler = new ProxyHandler(demo);
        Run dynamicProxy = (Run) Proxy.newProxyInstance(RunDemo.class.getClassLoader(),
                RunDemo.class.getInterfaces(), proxyHandler);
        dynamicProxy.run(args1);
    }
}
