package com.gt.base.lang;

/**
 * @author GTsung
 * @date 2022/3/28
 */
public class InheritableThreadLocalTest {

    static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("mainThread");
        System.out.println("value:" + threadLocal.get());
        threadLocal.remove();
        Thread thread = new Thread(() -> {
            String value = threadLocal.get();
            System.out.println("sonThread gets parent's value:" + value);
        });
        thread.start();
    }


}
