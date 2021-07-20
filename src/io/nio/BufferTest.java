package io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

public class BufferTest {
    static IntBuffer intBuffer = null;

    public static void main(String[] args) {

    }

    public static void allocatTest() {
        //调用allocate方法，而不是使用new
        intBuffer = IntBuffer.allocate(20);
        //输出buffer的主要属性值
        System.out.println("------------after allocate------------------");
        System.out.println("position=" + intBuffer.position());
        System.out.println("limit=" + intBuffer.limit());
        System.out.println("capacity=" + intBuffer.capacity());
    }

}

class FileNIOCopyDemo {
    public static void main(String[] args) {
        //演示复制资源文件
        nioCopyResouceFile();
    }

    /**
     * 复制两个资源目录下的文件
     */
    public static void nioCopyResouceFile() {
        String srcPath = "/Users/guoshiyu/Downloads/工作内容.md";
        String destDecodePath = "/Users/guoshiyu/Downloads/copy.md";
        nioCopyFile(srcPath, destDecodePath);
    }


    public static void nioCopyFile(String srcPath, String destPath) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        try {
            //如果目标文件不存在，则新建
            if (!destFile.exists()) {
                if (!destFile.createNewFile()) {
                    return;
                }
            }
            long startTime = System.currentTimeMillis();
            try (FileInputStream fis = new FileInputStream(srcFile); FileOutputStream fos = new FileOutputStream(destFile); FileChannel inChannel = fis.getChannel(); FileChannel outchannel = fos.getChannel()) {
                int length = -1;
                ByteBuffer buf = ByteBuffer.allocate(1024);
                //从输入通道读取到buf
                while ((length = inChannel.read(buf))   != -1) {
                    //第一次切换：翻转buf，变成读取模式
                    buf.flip();
                    int outlength = 0;
                    //将buf写入到输出的通道
                    while ((outlength = outchannel.write(buf)) != 0) {
                        System.out.println("写入的字节数：" + outlength);
                    }
                    //第二次切换：清除buf，变成写入模式
                    buf.clear();
                }
                //强制刷新到磁盘
                outchannel.force(true);
            }
            //关闭所有的可关闭对象
            long endTime = System.currentTimeMillis();
            System.out.println("base复制毫秒数：" + (endTime - startTime));
            Selector selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

