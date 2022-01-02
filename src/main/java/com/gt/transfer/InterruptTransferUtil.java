package com.gt.transfer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 断点续传: 记录读取日志，
 * 如果网络中断还未传输完毕则先去日志中查询每个线程读取的字节数
 * 如果日志不存在则从头读否则接着读
 *
 * @author GTsung
 * @date 2021/12/26
 */
public class InterruptTransferUtil {

    private InterruptTransferUtil() {
    }


    public static void transfer(File src, File desc, int threadNum) throws Exception {
        int perThreadLength = (int) Math.ceil(src.length() / threadNum);

        List<Thread> threadList = new ArrayList<>();
        // 记录日志
        final Map<Integer, Long> logMap = new ConcurrentHashMap<>();

        // 先读取日志
        String[] logContext = null;
        File logFile = new File(desc.getCanonicalPath() + ".log");
        if (logFile.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(logFile));
            logContext = reader.readLine().split(",");
            reader.close();
        }

        final String[] logFinalContext = logContext;
        for (int i = 0; i < threadNum; i++) {
            final int n = i;
            Thread thread = new Thread(() -> {
                RandomAccessFile log = null;
                try {
                    RandomAccessFile from = new RandomAccessFile(src, "r");
                    RandomAccessFile to = new RandomAccessFile(desc, "rw");

                    // 日志
                    log = new RandomAccessFile(desc.getCanonicalPath() + ".log", "rw");

                    // 如果之前存在日志则从日志读取的字节数之后开始读写
                    long pos = logFinalContext == null
                            ? n * perThreadLength
                            : Integer.parseInt(logFinalContext[n]);
                    from.seek(pos);
                    to.seek(pos);

                    byte[] storeBytes = new byte[1024 * 5];

                    int sum = 0, readLength = -1;

                    for (; ; ) {
                        readLength = from.read(storeBytes);
                        if (readLength == -1) {
                            break;
                        }

                        sum += readLength;

                        // 记录每个线程读取字节的日志
                        logMap.put(n, sum + pos);
                        // 写入
                        to.write(storeBytes, 0, readLength);

                        // 写入日志
                        log.seek(0);
                        log.write(logMap.values()
                                .stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining())
                                .getBytes());

                        // 线程是否超过规定字节数
                        if (sum + pos >= (n + 1) * perThreadLength) {
                            break;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (log != null) {
                        try {
                            log.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
            threadList.add(thread);
        }

        for (Thread t : threadList) {
            t.join();
        }

        // 最后删除log
        new File(desc.getCanonicalPath() + ".log").delete();

    }


    public static void transfer(File src, File desc) throws Exception {
        transfer(src, desc, 5);
    }

    public static void transfer(String src, String desc) throws Exception {
        transfer(new File(src), new File(desc));
    }

}
