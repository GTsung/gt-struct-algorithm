package com.gt.threads;

import java.util.concurrent.Exchanger;

/**
 * @author GTsung
 * @date 2021/12/30
 */
public class ExchangeDemo {

    /**
     * Exchange用于两个线程之间交换数据，
     * 当一个线程调用exchange后会阻塞直到另一个线程也调用exchange
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                System.out.println("当前线程A,得到其他线程信息为:"
                        + exchanger.exchange("线程A调用exchange方法传递的信息"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(3000);
        System.out.println("主线程率先执行");

        new Thread(() -> {
            try {
                System.out.println("当前线程B,得到其他线程信息为:" +
                        exchanger.exchange("下称B调用的exchange方法传递的信息"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
