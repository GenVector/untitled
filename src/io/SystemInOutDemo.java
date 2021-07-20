package io;

import java.io.*;

public class SystemInOutDemo {
    public static void main(String[] args) {
        out();
    }

    public static void test() throws IOException {
        //创建输入输出流对象
        BufferedReader br = new BufferedReader(new FileReader("SystemInOutDemo.java"));
        OutputStream os = System.out;

        String line;//用于存储读取到的数据
        while ((line = br.readLine()) != null) {
            os.write(line.getBytes());
            os.write("\r\n".getBytes());
        }
        //释放资源
        os.close();
        br.close();
        PrintStream old = System.out;
    }

    protected static void out() {
        System.out.println("test out");
        PrintStream old = System.out;
    }
}

