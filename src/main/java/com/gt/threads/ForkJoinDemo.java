package com.gt.threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author GTsung
 * @date 2021/12/29
 */
public class ForkJoinDemo {

    /**
     * 分治
     * Fork分解任务  join等待任务执行完
     * 任务对象: ForkJoinTask---> RecursiveTask | RecursiveAction | CountedCompleter
     * RecursiveTask递归求解返回值
     * RecursiveAction递归求解无返回值
     * CountedCompleter任务完成后触发执行钩子函数
     * 执行任务的线程: ForkJoinWorkerThread
     * 线程池: ForkJoinPool
     * 工作流程：首先新建一个任务提交到队列，看看这个队列是否又存在的阻塞线程，没有则新建线程并为这个线程分配队列
     * 接着线程执行任务，没有任务则阻塞，还可以从其他队列中通过先进先出来窃取任务，但执行自己队列的任务时使用的是先进后出
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        calculateSum();
        calculateFibonacci();
    }

    /**
     * 1、1、2、3、5、8、13、21、34、…… 公式 : F(1)=1，F(2)=1, F(n)=F(n-1)+F(n-2)(n>=3，n∈N*)
     * 斐波那契数列
     *
     * @throws Exception
     */
    private static void calculateFibonacci() throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        Fibonacci fibonacci = new Fibonacci(20);
        long start = System.currentTimeMillis();
        Integer result = forkJoinPool.invoke(fibonacci);
        long end = System.currentTimeMillis();
        System.out.println(result + " 使用了" + (end - start) + "秒");
    }

    static class Fibonacci extends RecursiveTask<Integer> {

        final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return 1;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
//            f2.fork();
//            return f1.join()+ f2.join();
            return f2.compute() + f1.join();
        }
    }

    /**
     * 计算1+...+10000
     */
    private static void calculateSum() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = new SumTask(1, 10000);
        pool.submit(task);
        System.out.println(task.get());
    }

    static final class SumTask extends RecursiveTask<Integer> {

        final int start;
        final int end;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        protected Integer compute() {
            if (end - start < 1000) {
                System.out.println(Thread.currentThread().getName() + "开始执行" + start + "-" + end);
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }

            SumTask sumTask1 = new SumTask(start, (start + end) / 2);
            SumTask sumTask2 = new SumTask((start + end) / 2 + 1, end);
            sumTask1.fork();
            sumTask2.fork();
            // 返回结果
            return sumTask1.join() + sumTask2.join();
        }
    }
}
