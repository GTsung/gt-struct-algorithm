package com.gt.threads.design;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author GTsung
 * @date 2021/12/31
 */
public class TwoStageStopDemo {

    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(() -> System.out.print("es stop"));
        es.shutdown();
    }

    /**
     * 终止线程
     */
    private static class Proxy {
        // 线程终止标志位
        volatile boolean terminated = false;
        // 线程是否开启
        boolean started = false;
        Thread rptThread;

        synchronized void start() {
            if (started) {
                // 线程不允许同时多次开启
                return;
            }
            started = true;
            terminated =  false;
            rptThread = new Thread(() -> {
                while (!terminated) {
                    System.out.println("开始执行任务");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                started = false;
            });

            rptThread.start();
        }

        synchronized void stop() {
            terminated = true;
            rptThread.interrupt();
        }
    }
}
