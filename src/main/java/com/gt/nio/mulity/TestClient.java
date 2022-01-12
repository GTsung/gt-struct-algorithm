package com.gt.nio.mulity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author GTsung
 * @date 2022/1/12
 */
public class TestClient {

    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 9000));
        sc.write(Charset.defaultCharset().encode("1234567"));
        System.in.read();
    }
}
