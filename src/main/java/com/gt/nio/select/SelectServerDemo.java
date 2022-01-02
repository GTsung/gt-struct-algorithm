package com.gt.nio.select;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class SelectServerDemo {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        // 注册
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        ByteBuffer writeBuffer = ByteBuffer.allocate(128);
        writeBuffer.put("received".getBytes());
        writeBuffer.flip();

        while(true) {
            // 在selector.select阻塞，等待可以使用的IO通道
            int nRead = selector.select();
            System.out.println(nRead); // 为0表示没有可用通道
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                if (key.isAcceptable()) {
                    // 创建并注册，声明为只读
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    readBuffer.clear();
                    socketChannel.read(readBuffer);

                    readBuffer.flip();
                    System.out.println("received：" + new String(readBuffer.array()));
                    key.interestOps(SelectionKey.OP_WRITE);
                } else if (key.isWritable()) {
                    writeBuffer.rewind(); // 从下标0处开始写入
                    SocketChannel sc = (SocketChannel) key.channel();
                    sc.write(writeBuffer);
                    key.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    }
}
