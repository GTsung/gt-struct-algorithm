package com.gt.threads;

/**
 * @author GTsung
 * @date 2021/12/29
 */
public class Example02 {

    private int count;

    public int getCount() {
        return count;
    }

    public synchronized void test() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }
}
