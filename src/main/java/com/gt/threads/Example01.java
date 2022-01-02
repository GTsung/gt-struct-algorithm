package com.gt.threads;

/**
 * @author GTsung
 * @date 2021/12/29
 */
public class Example01 {

    private Example02 example02;

    public Example01(Example02 example02) {
        this.example02 = example02;
    }

    public void use() {
        example02.test();
    }

    public static void main(String[] args) throws InterruptedException {
        Example02 example02 = new Example02();
        Example01 example01 = new Example01(example02);
        for(int i =0;i<3;i++) {
            new Thread(example01::use).start();
        }
        Thread.sleep(3000);
        System.out.println(example02.getCount());
    }
}
