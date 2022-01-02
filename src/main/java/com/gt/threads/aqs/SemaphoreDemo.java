package com.gt.threads.aqs;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author GTsung
 * @date 2021/12/29
 */
public class SemaphoreDemo {


    /**
     * 令牌是不可重入的，可以实现限流的作用
     * release是增加一个令牌，如果初始化令牌数为3，首先调用release方法，则此时令牌数为4
     * @param args
     */
    public static void main(String[] args) {
        // 指定state的值，儅調用acquire時會將state的值減一，減爲0時綫程阻塞不能再進入
        // 調用release時會增加state的值，并將隊列中存在的阻塞綫程釋放
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(semaphore)).start();
        }
    }

    static class Task implements Runnable {

        private Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }


        @Override
        public void run() {
            try {
                // 获取state如果不够则堵塞
                semaphore.acquire();
                System.out.println("当前线程为" + Thread.currentThread().getName()
                        + "--还剩下的资源" + semaphore.availablePermits());
                Random random = new Random();
                Thread.sleep(random.nextInt(1000));
//                semaphore.release();
                System.out.println(Thread.currentThread().getName() + "释放资源");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
