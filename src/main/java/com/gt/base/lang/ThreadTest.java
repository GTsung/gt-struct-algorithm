package com.gt.base.lang;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class ThreadTest {

    public static void main(String[] args) {
        createThread();
    }

    private static void createThread() {
        Thread t = new Thread();
        t.start();
    }
}
