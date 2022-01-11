package com.gt.netty.sample.non;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 不使用Netty的阻塞版本
 * @author GTsung
 * @date 2022/1/11
 */
public class PlainOioServer {

    /**
     * 阻塞模式，每个请求过来都会创建一个线程，适合并发量不大的场景
     * @param port
     * @throws Exception
     */
    public void server(int port) throws Exception {
        final ServerSocket serverSocket = new ServerSocket(port);
        try {
            for (;;) {
                final Socket clientSocket = serverSocket.accept();
                System.out.println("accepted from " + clientSocket);
                new Thread(() -> {
                    OutputStream out = null;
                    try {
                        out = clientSocket.getOutputStream();
                        out.write("Hi".getBytes(Charset.forName("UTF-8")));
                        out.flush();
                        clientSocket.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
