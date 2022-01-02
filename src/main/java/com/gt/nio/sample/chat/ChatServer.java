package com.gt.nio.sample.chat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class ChatServer {

    public void startServer() throws Exception {
        // 创建Selector
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8000));
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器已经启动");

        for (; ; ) {
            int readChannels = selector.select();
            // 为0表示没有通道可用
            if (readChannels == 0) {
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                // 如果accept状态
                if (selectionKey.isAcceptable()) {
                    acceptOperator(serverSocketChannel, selector);
                }
                // 如果可读状态操作
                if (selectionKey.isReadable()) {
                    readOperator(selector, selectionKey);
                }
            }
        }
    }

    private void readOperator(Selector selector, SelectionKey selectionKey) throws Exception {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int readLength = socketChannel.read(buffer);
        String msg = "";
        if (readLength > 0) {
            // 切换读模式
            buffer.flip();
            msg += Charset.forName(StandardCharsets.UTF_8.name()).decode(buffer);
        }
        // 将channel再次注册到选择器上
        socketChannel.register(selector, SelectionKey.OP_READ);
        if (msg.length() > 0) {
            System.out.println(msg);
            // 广播
            castOtherClient(msg, selector, socketChannel);
        }
    }

    private void castOtherClient(String msg, Selector selector, SocketChannel socketChannel) throws Exception {
        // 获取channel
        Set<SelectionKey> selectionKeys = selector.keys();
        for (SelectionKey selectionKey : selectionKeys) {
            Channel tarChannel = selectionKey.channel();
            if (tarChannel instanceof SocketChannel
                    && tarChannel != socketChannel) {
                // 写入数据
                ((SocketChannel) tarChannel)
                        .write(Charset.forName(StandardCharsets.UTF_8.name()).encode(msg));
            }
        }
    }

    private void acceptOperator(ServerSocketChannel serverSocketChannel, Selector selector) throws Exception {
        SocketChannel socketChannel = serverSocketChannel.accept();

        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        socketChannel.write(Charset.forName(StandardCharsets.UTF_8.name()).encode("欢迎进入聊天室，请注意隐私安全"));
    }


    public static void main(String[] args) throws Exception {
        new ChatServer().startServer();
    }
}
