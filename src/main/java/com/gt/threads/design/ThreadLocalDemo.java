package com.gt.threads.design;

import java.util.stream.IntStream;

/**
 * @author GTsung
 * @date 2021/12/28
 */
public class ThreadLocalDemo {

    // ThreadLocal里的key为弱引用，但value为强引用， 如果固定的调用某个线程的get，则可能会发生内存泄漏
    // set可以有机会回收 一般不再使用需要即时remove

    public static void main(String[] args) {
        ThreadLocal threadLocal = new InheritableThreadLocal();
        IntStream.range(0, 10).forEach(i -> {
            threadLocal.set(i);

            new Thread(() -> {
                // 子线程中收不到父线程里的变量，这里使用InheritableThreadLocal
                // 存在map中的value是一致的，如果在线程池中就不灵了
                System.out.println(Thread.currentThread().getName()+ ":" + threadLocal.get());

            }).start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
