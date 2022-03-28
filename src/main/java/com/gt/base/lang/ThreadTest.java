package com.gt.base.lang;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class ThreadTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            threadLocal.set("01");
            print("thread1");
            System.out.println("after remove - " + threadLocal.get());
        }).start();

        new Thread(() -> {
            threadLocal.set("02");
            print("thread2");
            System.out.println("after remove - " + threadLocal.get());
        }).start();
    }

    private static void print(String str) {
        System.out.println(str + " - " + threadLocal.get());
        threadLocal.remove();
    }

}
