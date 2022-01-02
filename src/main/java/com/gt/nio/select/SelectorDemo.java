package com.gt.nio.select;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * IO多路复用，需要注册通道，
 * 通道必须是继承SelectableChannel，因此FileChannel不可用
 * @author GTsung
 * @date 2021/12/24
 */
public class SelectorDemo {

    public static void main(String[] args) throws Exception {
        // 获取Selector
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 非阻塞(必须)
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 注册到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // Selector:select(); 阻塞 | select(long timeout); 最长阻塞timeout | selectNow(); 非阻塞
        Set selectedKeys = selector.selectedKeys();
        Iterator it = selectedKeys.iterator();
        while (it.hasNext()) {
            SelectionKey key = (SelectionKey) it.next();
            if (key.isAcceptable()) {
                // ServerSocketChannel
            } else if (key.isConnectable()) {
                // SocketChannel
            } else if (key.isReadable()) {
                // read
            } else if (key.isWritable()) {
                // write
            }
            it.remove();
        }
    }
}
