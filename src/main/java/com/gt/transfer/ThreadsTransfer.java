package com.gt.transfer;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * 多线程复制文件
 * @author GTsung
 * @date 2021/12/26
 */
public class ThreadsTransfer {

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\下载目录\\第01集.mp4");
        long fileLength = file.length();
        int threadNum = 5;

        // 每个线程读的最大size
        int part = (int) Math.ceil(fileLength / threadNum);

        for (int i = 0; i < threadNum; i++) {
            final int n = i;

            new Thread(() -> {
                try {
                    RandomAccessFile in = new RandomAccessFile(file, "r");
                    RandomAccessFile out = new RandomAccessFile("D:\\t.mp4", "rw");

                    // seek从指定的绝对位置开始读取x写入
                    in.seek(n * part);
                    out.seek(n * part);

                    byte[] bytes = new byte[1024 * 8];
                    int readLength = -1, sum = 0;

                    while (true) {
                        readLength = in.read(bytes);

                        // 读完则退出
                        if (readLength == -1) {
                            break;
                        }

                        sum += readLength;
                        out.write(bytes, 0, readLength);

                        // 每个线程读的总量大于等于规定容量则退出
                        if (sum >= part) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        }

    }

}
