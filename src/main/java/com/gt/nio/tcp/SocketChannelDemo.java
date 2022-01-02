package com.gt.nio.tcp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class SocketChannelDemo {

    public static void main(String[] args) throws Exception {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9000));
        channel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.wrap("fuck LiuXiao's mother".getBytes());
        channel.write(buffer);
        channel.shutdownOutput();
        channel.close();
    }
}
