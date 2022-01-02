package com.gt.nio.udp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class DatagramChannelSendDemo {

    public static void main(String[] args) throws Exception {
        DatagramChannel channel = DatagramChannel.open();
        ByteBuffer buffer = ByteBuffer.wrap("fuck LiuXiao's wife".getBytes());
        channel.send(buffer, new InetSocketAddress("127.0.0.1",9002));
    }
}
