package com.gt.nio.udp;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class DatagramChannelReceiveDemo {

    public static void main(String[] args) throws Exception {
        DatagramChannel server = DatagramChannel.open();
        server.socket().bind(new InetSocketAddress(9002));
//        server.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        SocketAddress receiveSocket = server.receive(buffer);
        System.out.println(receiveSocket.toString());
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
    }
}
