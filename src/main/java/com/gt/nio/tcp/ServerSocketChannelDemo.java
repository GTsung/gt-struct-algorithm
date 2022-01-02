package com.gt.nio.tcp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class ServerSocketChannelDemo {

    public static void main(String[] args) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(48);
        // 打开serverSocket通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 绑定端口
        ssc.socket().bind(new InetSocketAddress(9000));
        // 非阻塞设置
        ssc.configureBlocking(false);
        while (true) {
            System.out.println("waiting for connection");

            // 监听
            SocketChannel sc = ssc.accept();
            if (sc == null) {
                Thread.sleep(2000);
            } else {
                System.out.println("Incoming connection from：" + sc.getRemoteAddress());
                // position = 0; 从起始处开始写入 compact()--->将未读完的字节放到数组前端，继续向后读入
                buffer.rewind();
                sc.read(buffer);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char)buffer.get());
                }
                System.out.println();
                sc.close();
            }
        }
    }
}
