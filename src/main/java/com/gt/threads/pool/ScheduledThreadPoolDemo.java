package com.gt.threads.pool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author GTsung
 * @date 2021/12/30
 */
public class ScheduledThreadPoolDemo {

    private static final ScheduledExecutorService es = new ScheduledThreadPoolExecutor(1, Executors.defaultThreadFactory());

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

//        withFixedDelay();
        atFixedDelay();

    }

    private static void atFixedDelay() {
        // 不等待任务执行完毕，直接是在开始任务的时间后开始计时
        /**
         * 2021-12-30 20:46:45继续抽插刘萧他媳妇
         * 2021-12-30 20:46:47继续抽插刘萧他媳妇
         * 2021-12-30 20:46:49继续抽插刘萧他媳妇
         * 任务执行时间粗略计为2秒，但从开始执行任务的时候开始算，1秒后就应该将任务放入到队列中，
         * 但因为任务正在执行，所以间隔2秒,如果执行的任务时间小于1秒，那么打印间隔1秒
         * 当任务开始执行的时候就已经将任务再一次放到了延迟队列中了
         */
        es.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(2000); // 这里如果小于1秒，那么打印间隔时间为1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(df.format(new Date()) + "继续抽插刘萧他媳妇");
        }, 2, 1, TimeUnit.SECONDS);
    }

    private static void withFixedDelay() {
        // 固定延迟时间的计划任务
        /**
         * 2021-12-30 20:40:55 开始肏刘萧他妈妈的肉了
         * 2021-12-30 20:40:58 开始肏刘萧他妈妈的肉了
         * 2021-12-30 20:41:01 开始肏刘萧他妈妈的肉了
         * 任务执行的时间长粗略记为2秒，那么下一次执行任务的时间应该是3秒一个间隔(先执行完任务花2秒，在等待1秒)，
         * 等到任务完全执行完毕后再将任务放到延迟队列中
         */
        es.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(df.format(new Date()) + " 开始肏刘萧他妈妈的肉了");
        }, 2, 1, TimeUnit.SECONDS);
    }

}
