package com.gt.threads.design;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author GTsung
 * @date 2021/12/31
 */
public class ProducerConsumerDemo {

    /**
     * 生产消费
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    private static class Log {
        final BlockingQueue<LogMsg> bq = new LinkedBlockingQueue<>();

        static final int batchSize = 500;

        ExecutorService es = Executors.newFixedThreadPool(1);

        void start() throws Exception {
            File file = File.createTempFile("foo", ".log");
            final FileWriter writer = new FileWriter(file);
            es.execute(() -> {
                try {
                    int curIndex = 0;
                    long preFT = System.currentTimeMillis();
                    while (true) {
                        LogMsg logMsg = bq.poll(5, TimeUnit.SECONDS);
                        if (logMsg != null) {
                            writer.write(logMsg.toString());
                            ++curIndex;
                        }
                        if (curIndex <= 0) {
                            continue;
                        }
                        if (logMsg != null && logMsg.level == LEVEL.ERROR
                                || curIndex == batchSize
                                || System.currentTimeMillis() - preFT > 5000) {
                            writer.flush();
                            curIndex = 0;
                            preFT = System.currentTimeMillis();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //写INFO级别日志
        void info(String msg) throws InterruptedException {
            bq.put(new LogMsg(LEVEL.INFO, msg));
        }

        // 写ERROR级别日志
        void error(String msg) throws InterruptedException {
            bq.put(new LogMsg(LEVEL.ERROR, msg));
        }
    }


    enum LEVEL {INFO, ERROR}

    static class LogMsg {
        LEVEL level;
        String msg;

        //省略构造函数实现
        LogMsg(LEVEL lvl, String msg) {
            this.level = lvl;
            this.msg = msg;
        }

        //省略toString()实现
        public String toString() {
            return super.toString();
        }
    }


    /**
     * 生产消费
     */
    private static class ProducerConsumerInternal {
        BlockingQueue<Task> bq = new LinkedBlockingQueue<>(2000);

        void start() {
            ExecutorService es = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 5; i++) {
                es.execute(() -> {
                    List<Task> tasks = null;
                    while (true) {
                        try {
                            tasks = pollTask();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        execTask(tasks);
                    }
                });
            }
        }

        private void execTask(List<Task> tasks) {
            // do
        }

        private List<Task> pollTask() throws InterruptedException {
            List<Task> tasks = new LinkedList<>();
            Task t = bq.take();
            while (t != null) {
                tasks.add(t);
                t = bq.poll();
            }
            return tasks;
        }
    }

    private static class Task {
    }
}
