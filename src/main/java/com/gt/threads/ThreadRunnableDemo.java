package com.gt.threads;

/**
 * Thread 与 Runnable
 * @author GTsung
 * @date 2021/12/26
 */
public class ThreadRunnableDemo {

    public static void main(String[] args) {
        new Thread(() -> {System.out.print("ss");});

        new Thread(new Task()).start();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Task");
        }
    }
}
