package com.gt.threads.aqs;

import java.util.concurrent.CyclicBarrier;

/**
 * @author GTsung
 * @date 2021/12/29
 */
public class CyclicBarrierDemo {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        demo1();
        demo2();
    }

    private static void demo2() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            // 这里是个线程，当所有的线程await完毕之后会回来调用这个，接着继续向下执行并且恢复栅栏的parties
            System.out.println(Thread.currentThread().getName() + "最后完成任务");
        });
        for (int i = 0; i < 3; i++) {
            new Task2(cyclicBarrier).start();
        }
        /**
         * Thread-2到达屏障A
         * Thread-1到达屏障A
         * Thread-0到达屏障A
         * Thread-1最后完成任务
         * Thread-1冲破了屏障A
         * Thread-2冲破了屏障A
         * Thread-0冲破了屏障A
         * Thread-2到达屏障B
         * Thread-0到达屏障B
         * Thread-1到达屏障B
         * Thread-1最后完成任务
         * Thread-1冲破了屏障B
         * Thread-2冲破了屏障B
         * Thread-0冲破了屏障B
         */
    }

    static class Task2 extends Thread {

        private CyclicBarrier cyclicBarrier;

        public Task2(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + "到达屏障A");
                // 这个时候等待所有线程到达屏障A
                cyclicBarrier.await();
                // 等到全部到达之后会回调屏障的线程回调函数
                System.out.println(getName() + "冲破了屏障A");

                Thread.sleep(2000);
                System.out.println(getName() + "到达屏障B");
                cyclicBarrier.await();
                System.out.println(getName() + "冲破了屏障B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void demo1() throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Thread("barrierAction") {
            // 当cyclicBarrier的parties为0时，会回调此线程
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " barrier action");
            }
        });

        Thread t1 = new Thread(new Task(cyclicBarrier), "TASK-01");
        Thread t2 = new Thread(new Task(cyclicBarrier), "TASK-02");

        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName() + " going to wait");
        cyclicBarrier.await();
        System.out.println(Thread.currentThread().getName() + " continue");
    }

    static class Task implements Runnable {

        private CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " going to wait");

            try {
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
