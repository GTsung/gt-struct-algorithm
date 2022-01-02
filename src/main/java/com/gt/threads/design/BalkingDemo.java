package com.gt.threads.design;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author GTsung
 * @date 2021/12/30
 */
public class BalkingDemo {

    /**
     * Bulking模式: 多线程的if
     * @param args
     */
    public static void main(String[] args) {

    }

    static class AutoSaveEditor {
        // 是否被修改过
        boolean changed = false;

        ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();

        void startAutoSave() {
            es.scheduleWithFixedDelay(() -> {
                autoSave();
            }, 5, 5, TimeUnit.SECONDS);
        }

        void autoSave() {
            synchronized (this) {
                if (!changed) {
                    return;
                }
                changed = false;
            }
            this.execSave();
        }

        private void execSave() {
            System.out.println("save file");
        }

        void edit() {
            synchronized (this) {
                changed = true;
            }
        }
    }
}
