package com.gt.nio.sample.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class ChatClient {

    public void startClient(String name) throws Exception {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8000));
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new ClientThread(selector)).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();
            if (msg.length() > 0) {
                socketChannel.write(Charset.forName(StandardCharsets.UTF_8.name()).encode(name + ":" + msg));
            }
        }
    }

    static class ClientThread implements Runnable {

        private Selector selector;

        public ClientThread(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                for (; ; ) {
                    int readChannels = selector.select();
                    if (readChannels == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if (selectionKey.isReadable()) {
                            readOperator(selector, selectionKey);
                        }
                    }
                }
            } catch (Exception e) {

            }
        }

        private void readOperator(Selector selector, SelectionKey selectionKey) throws IOException {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int readLength = socketChannel.read(buffer);
            String msg = "";
            if (readLength > 0) {
                buffer.flip();
                msg += Charset.forName(StandardCharsets.UTF_8.name()).decode(buffer);
            }
            socketChannel.register(selector, SelectionKey.OP_READ);
            if (msg.length() > 0) {
                System.out.println(msg);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new ChatClient().startClient("fuck LiuXiao's mother");
    }
}
