package com.gt.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * @author GTsung
 * @date 2022/5/14
 */
public class ReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个引用队列
        ReferenceQueue<Integer> queue = new ReferenceQueue<>();
        // 创建一个弱引用
        WeakReference<Integer> reference = new WeakReference<>(12, queue);
        System.out.println(reference);

        System.gc();

        Reference reference1 = queue.remove();
        System.out.println(reference1);

        // WeakHashMap， Entry继承了WeakReference
        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
        weakHashMap.put("s", "1");
        System.out.println(weakHashMap.get("s"));
    }
}
