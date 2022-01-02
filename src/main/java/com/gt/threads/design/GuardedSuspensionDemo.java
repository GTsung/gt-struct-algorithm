package com.gt.threads.design;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @author GTsung
 * @date 2021/12/30
 */
public class GuardedSuspensionDemo {

    public static void main(String[] args) {
        int id = new Random().nextInt(100);
        Message sendMsg = new Message(id, "this is a msg");

        GuardedObject<Message> guardedObject = GuardedObject.create(id);
        // 发送消息
//        send(sendMsg);
        // 当前线程阻塞，等待接收消息
        Message returnMsg = guardedObject.get(t -> t != null);

        // 消息中间件内返回消息后唤醒等待的线程
        String resMsg = "return msg";
        GuardedObject.fireEvent(id, resMsg);
    }


    static class GuardedObject<T> {

        T obj;

        final Lock lock = new ReentrantLock();

        final Condition done = lock.newCondition();

        final int timeout = 1;

        //保存所有GuardedObject
        final static Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();

        //静态方法创建GuardedObject
        static <K> GuardedObject create(K key) {
            GuardedObject go = new GuardedObject();
            gos.put(key, go);
            return go;
        }

        static <K, T> void fireEvent(K key, T obj) {
            GuardedObject go = gos.remove(key);
            if (go != null) {
                go.onChange(obj);
            }
        }

        /**
         * 获取结果
         *
         * @param p
         * @return
         */
        T get(Predicate<T> p) {
            lock.lock();
            try {
                while (!p.test(obj)) {
                    done.await(timeout, TimeUnit.SECONDS);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            return obj;
        }

        /**
         * 通知
         *
         * @param obj
         */
        void onChange(T obj) {
            lock.lock();
            try {
                this.obj = obj;
                done.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }


    static class Message {
        private String context;
        private Integer id;

        public Message(Integer id, String context) {
            this.id = id;
            this.context = context;
        }


    }
}


