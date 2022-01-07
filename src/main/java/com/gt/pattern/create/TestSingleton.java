package com.gt.pattern.create;

import java.util.concurrent.*;

/**
 * @author GTsung
 * @date 2022/1/7
 */
public class TestSingleton {


    static class Single {
        private Single() {
        }

        // 需加入volatile，否则发生重排序，volatile发生写操作before happens 读操作
        private static volatile Single instance;

        // 懒加载
        public static synchronized Single getInstance() {
            if (instance == null) {
                synchronized (Single.class) {
                    if (instance == null) {
                        instance = new Single();
                    }
                }
            }
            return instance;
        }

        // 此时也为懒加载，只有调用getInstance方法才会加载类S(类的加载是线程安全的)
//        private static class S {
//            private static final Single instance = new Single();
//        }

//        public static Single getInstance() {
//            return S.instance;
//        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            Future f = es.submit(new Task());
            System.out.println(f.get());
        }
        es.shutdown();
    }

    static class Task implements Callable<Single> {
        @Override
        public Single call() throws Exception {
            return Single.getInstance();
        }
    }

}



