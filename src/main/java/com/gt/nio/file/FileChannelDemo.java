package com.gt.nio.file;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class FileChannelDemo {

    public static void main(String[] args) throws Exception {
        readBytes();

//        writeBytes();

        // FileChannel:position() ---> 返回当前position
        // FileChannel:position(n) ---> 指定position位置
        // FileChannel:size() ---> 关联的文件大小
        // FileChannel:truncate(n) ---> 截断文件，n后的将被清除
        // FileChannel:force() ---> 将通道里的数据强制写到磁盘

//        transfer();
    }

    private static void transfer() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("a.txt", "rw");
        FileChannel fromChannel = aFile.getChannel();

        RandomAccessFile bFile = new RandomAccessFile("b.txt", "rw");
        FileChannel toChannel = bFile.getChannel();
        // 将fromChannel的数据传输到toChannel
        toChannel.transferFrom(fromChannel, 0, fromChannel.size());
        // fromChannel.transferTo(0, fromChannel.size(), toChannel);
        aFile.close();
        bFile.close();
    }

    private static void writeBytes() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("a.txt", "rw");
        FileChannel fileChannel = aFile.getChannel();

        String msg = "Fuck The CCP!";
        ByteBuffer buffer = ByteBuffer.allocate(24);
        // position = 0; limit = capacity; 清空缓冲区
        buffer.clear();
        // 将字节放入到缓冲区
        buffer.put(msg.getBytes());
        // 开启写模式 limit = position, position = 0
        buffer.flip();
        while (buffer.hasRemaining()) {
            fileChannel.write(buffer);
        }
        fileChannel.close();
    }

    public static void readBytes() throws Exception {
        // 读模式
        RandomAccessFile aFile = new RandomAccessFile("aFile.txt", "r");
        FileChannel fileChannel = aFile.getChannel();
        // 分配容量
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 读数据
        int bytesRead = fileChannel.read(buffer);
        while (bytesRead != -1) {
            System.out.println("读取到的字节数:" + bytesRead);
            // 缓冲区切换到读模式
            buffer.flip();
            // position < limit 表明还有数据未读到
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            // 将缓冲区清空
            buffer.clear();
            // 继续读
            bytesRead = fileChannel.read(buffer);
        }
        aFile.close();
    }
}
