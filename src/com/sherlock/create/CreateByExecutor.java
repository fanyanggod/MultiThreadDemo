package com.sherlock.create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author sherlock
 */
public class CreateByExecutor {
    private static ExecutorService pool = Executors.newFixedThreadPool(3);

    static class MultiThreadTarget implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 3; i++){
            MultiThreadTarget target = new MultiThreadTarget();
            pool.execute(target);
        }
        pool.shutdown();

    }
}
