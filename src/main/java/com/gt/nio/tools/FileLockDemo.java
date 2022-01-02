package com.gt.nio.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class FileLockDemo {

    // FileLock仅用于进程之间的加锁，同一个进程之间的多个线程仍然可以在同一个事件内访问修改文件
    // FileLock是不可重入的
    // 排他锁:独占锁，加锁的进程可以对文件进行访问修改，其他进程不可以
    // 共享锁:某个进程加了共享锁，其他进程只能读此文件，不能写
    public static void main(String[] args) throws Exception {
//        FileChannel fileChannel = new FileOutputStream("1.txt").getChannel();
        // 对文件加锁
        // lock() ---> 默认排他锁
        // lock(long position, long size, boolean shared) ---> 可以只针对部分文件进行加锁，shared参数为是否为共享锁
        // tryLock() ---> 对整个文件加锁，默认排他锁
        // tryLock(long position, long size, boolean shared) --->
//        FileLock fileLock = fileChannel.lock();

        // lock()与tryLock()区别:
        // lock()为阻塞式的，tryLock()为非阻塞的

        // 读写操作
//        fileLock.release();

        String msg = "fuck LiuXiao's daughter\n";
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        String file = "01.txt";
        Path path = Paths.get(file);
        FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        // 获取锁
        FileLock lock = channel.tryLock(0, Long.MAX_VALUE, false);
        System.out.println("共享锁shared:" + lock.isShared());
        channel.write(buffer);
        channel.close();
        System.out.println("写操作完成");
        readPrint(file);
    }

    private static void readPrint(String path) throws Exception {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String tr = bufferedReader.readLine();
        System.out.println("读取内容:");
        while (tr != null) {
            System.out.println(" " + tr);
            tr = bufferedReader.readLine();
        }
        bufferedReader.close();
        fileReader.close();
    }
}
