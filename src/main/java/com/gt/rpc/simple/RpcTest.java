package com.gt.rpc.simple;

import java.net.InetSocketAddress;

/**
 * @author GTsung
 * @date 2022/1/11
 */
public class RpcTest {

    public static void main(String[] args) {
        new Thread(() -> {
            Server server = new ServerCenter(9000);
            server.register(Producer.class, ProducerImpl.class);
            server.start();
        }).start();
        RpcClient client = new RpcClient();
        Producer producer = (Producer) client.doService(Producer.class, new InetSocketAddress(9000));
        System.out.println(producer.sayHello());
    }
}
