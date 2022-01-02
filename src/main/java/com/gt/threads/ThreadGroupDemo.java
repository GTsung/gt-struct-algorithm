package com.gt.threads;

/**
 * @author GTsung
 * @date 2021/12/26
 */
public class ThreadGroupDemo {

    public static void main(String[] args) {
        Thread testThread = new Thread(() -> {
            System.out.println("testThread当前线程组名字:" +
                    Thread.currentThread().getThreadGroup().getName());
            System.out.println("testThread线程名字:" +
                    Thread.currentThread().getName());
        });

        testThread.start();
        System.out.println("main线程名字:" + Thread.currentThread().getName());

        // 复制线程数组
//        ThreadGroup threadGroup = new ThreadGroup("group01");
//        Thread[] threads = new Thread[threadGroup.activeCount()];
//        threadGroup.enumerate(threads);

        // 线程组的异常
        ThreadGroup group = new ThreadGroup("group01") {
            // 线程组中的线程如果抛出异常，则在这里捕获
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + ":" + e.getMessage());
            }
        };

        Thread thread = new Thread(group, new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("测试异常");
            }
        });
        thread.start();

    }
}
