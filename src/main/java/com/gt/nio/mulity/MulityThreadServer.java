package com.gt.nio.mulity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author GTsung
 * @date 2022/1/12
 */
public class MulityThreadServer {

    /**
     * boss专门负责连接，worker负责获取数据
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        /**
         * 用于事件连接的为boss线程，而用于数据传输的为worker线程
         */
        Thread.currentThread().setName("boss");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        Selector boss = Selector.open();
        ssc.register(boss, SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(9000));

        // 创建固定数量的worker
//        Worker worker = new Worker("worker");
        Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("worker-" + i);
        }

        // 计数器
        AtomicInteger index = new AtomicInteger();

        while (true) {
            boss.select();
            Iterator<SelectionKey> iterator = boss.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    System.out.println("connected...." + sc.getRemoteAddress());


                    System.out.println("before register...." + sc.getRemoteAddress());

                    // 将sc关联到worker的selector处理数据
//                    sc.register(worker.selector, SelectionKey.OP_READ);

//                    worker.register(sc);
                    // 轮询
                    workers[index.getAndIncrement() % workers.length].register(sc);
                    System.out.println("after register...." + sc.getRemoteAddress());
                }
            }
        }
    }

    /**
     * Worker专门做数据传输
     */
    static class Worker implements Runnable {

        private String name;

        private Selector selector;

        private Thread thread;

        private volatile boolean started = false;

        // 添加任务队列，用于不同线程之间
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

        public Worker(String name) {
            this.name = name;
        }

        public void register(SocketChannel sc) throws IOException {
            // 第一次调用时开启select线程
            if (!started) {
                selector = Selector.open();
                thread = new Thread(this, name);
                thread.start();
                started = true;
            }

            // 将socket注册到selector
            queue.offer(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            // 自动唤醒selector，将阻塞在selector.select()线程唤醒，重新select后就可以获取到key
            // wakeup类似于LockSupport.unpark()
            // selector.select() ---> selector.wakeup()---> register
            // selector.wakeup() ---> selector.select()---> register
            // selector.wakeup()---> register ---> selector.select()
            // 以上三种顺序都可以注册上不阻塞
            selector.wakeup();

        }

        @Override
        public void run() {
            while (true) {
                try {
                    // 先注册后select才有效，当先select就会阻塞，后期注册则没有效果(selector仍然阻塞)
                    // 这时可以调用selector.wakeup()唤醒，但唤醒后往下执行是找不到selectionKey的，需要再一次select才能获取到key
                    selector.select();

                    // 拿到注册
                    Runnable task = queue.poll();
                    if (Objects.nonNull(task)) {
                        task.run();
                    }


                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel sc = (SocketChannel) key.channel();
                            sc.read(buffer);
                            buffer.flip();
                            System.out.println(new String(buffer.array()));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
