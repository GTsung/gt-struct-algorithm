package com.gt.nio.tools;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class AsynchronousFileChannelDemo {

    // 异步读写数据
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("01.txt");


        read01(path);

        read02(path);

        write01(path);

        write02(path);

    }

    private static void write02(Path path) throws Exception {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        buffer.put("fuck Liuxiao's mother".getBytes());
        buffer.flip();

        fileChannel.write(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("bytes written:" + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("write failed");
                exc.printStackTrace();
            }
        });
    }

    private static void write01(Path path) throws Exception {
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        buffer.put("Fuck LiuXiao's mother and sister".getBytes());
        buffer.flip();

        Future<Integer> operation = fileChannel.write(buffer, position);
        buffer.clear();
        while (!operation.isDone()) {
        }
        System.out.println("write over");
    }


    private static void read02(Path path) throws Exception {
        AsynchronousFileChannel fileChannel = null;
        fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        // 读取
        fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);
                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);

                System.out.println(new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }

    private static void read01(Path path) throws Exception {
        AsynchronousFileChannel fileChannel = null;
        try {
            // StandardOpenOption.READ 只读
            fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        Future<Integer> operation = fileChannel.read(buffer, position);
        while (!operation.isDone()) {
            Thread.sleep(100);
        }
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
    }
}
