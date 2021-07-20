package io.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable {
    Selector selector;
    ServerSocketChannel serverSocket;
/*
    EchoServerReactor() throws IOException {
        //....省略：打开选择器、serverSocket连接监听通道
        //注册serverSocket的accept事件
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        //将新连接处理器作为附件，绑定到sk选择键
        sk.attach(new AcceptorHandler());
    }
 */

    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    //反应器负责dispatch收到的事件
                    SelectionKey sk = it.next();
                    dispatch(sk);
                }
                selected.clear();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void dispatch(SelectionKey k) {
        Runnable handler = (Runnable) (k.attachment());
        //调用之前绑定到选择键的handler处理器对象
        if (handler != null) {
            handler.run();
        }
    }

    // 新连接处理器
    class AcceptorHandler implements Runnable {
        public void run() {
            //接受新连接
            //需要为新连接，创建一个输入输出的handler处理器
        }
    }

}

class IOHandler implements Runnable {
    final SocketChannel channel;
    final SelectionKey sk;

    IOHandler(Selector selector, SocketChannel c) throws IOException {
        channel = c;
        c.configureBlocking(false);
        //仅仅取得选择键，稍候设置感兴趣的IO事件
        sk = channel.register(selector, 0);
        //将Handler处理器作为选择键的附件
        sk.attach(this);
        //注册读写就绪事件
        sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }

    public void run() {
        //...处理输入和输出
    }
}



