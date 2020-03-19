package jconsole;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OOMObject {

    static class OOMObjectT {
        public byte[] placeholder = new byte[64 * 1024];
        public static void fillHeap(int num) throws InterruptedException {
            List<OOMObjectT> list = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                //稍作延时,令监视曲线的变化更加明显
                Thread.sleep(50);
                System.out.println(i);
                list.add(new OOMObjectT());
            }

        }

        public static void main(String[] args) throws Exception {
            System.out.println("start");
            fillHeap(1000);
            System.gc();
        }

    }

    static class LockThreadObjectT {
       public static void createBusyThread(){
            Thread thread=new Thread(new Runnable(){
                @Override
                public void run(){
                    while(true);
                }
            },"testBusyThread");
            thread.start();
        }
        /**
         *线程锁等待演示
         */
        public static void createLockThread(final Object lock){
            Thread thread=new Thread(new Runnable(){
                @Override
                public void run(){
                    synchronized(lock){
                        try{
                            lock.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            },"testLockThread");
            thread.start();
        }
        public static void main(String[]args)throws Exception{
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            br.readLine();
            createBusyThread();
            br.readLine();
            Object obj=new Object();
            createLockThread(obj);
        }

    }
}