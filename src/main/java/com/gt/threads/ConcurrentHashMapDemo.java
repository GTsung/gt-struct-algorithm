package com.gt.threads;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GTsung
 * @date 2021/12/28
 */
public class ConcurrentHashMapDemo {

    /**
     * jdk1.7: Segment[16] --> 先初始化的Segment[0]  根据hash找到对应Segment，对该segment加锁Lock.lock()
     *          然后根据hash获取对应entry,查找对应元素，查不到或者没元素则新建元素并将其置于entry数组处，最后解锁
     *          get方法不加锁
     *
     * jdk1.8: 去除了分段锁，数组+链表+红黑树 synchronized + CAS 在一个循环体里首先判断是否初始化，
     *          没有则在循环里CAS进行初始化，再根据hash找到对应的entry下标，判断是否表头无元素，无元素则CAS设置
     *          再看他是不是正在rehash：CAS判断，synchronized加锁到表头元素进行扩容
     *          最后synchronized加锁表头对链表或者红黑树进行put，再看看是否需要红黑树化
     *
     */
    private static final Map<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        map.put("sd", "sd");
    }
}
