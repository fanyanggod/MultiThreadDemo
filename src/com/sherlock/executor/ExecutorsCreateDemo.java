package com.sherlock.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author sherlock
 */
public class ExecutorsCreateDemo {

    public static final int SLEEP = 500;

    static class TargetTask implements Runnable {

        static AtomicInteger taskNo = new AtomicInteger(1);
        private final String taskName;

        public TargetTask(String name) {
            taskName = name+"-task-" + taskNo;
            taskNo.incrementAndGet();
        }

        @Override
        public void run() {
            System.out.println("Task: "+taskName + " doing");
            sleepMs(SLEEP);
            System.out.println(taskName + " down.");
        }
    }

    public static void main(String[] args) {
        single();
        fixed();
        cached();
        schedule();
    }

    public static void single() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask("single"));
            pool.submit(new TargetTask("single"));
        }
        sleepMs(SLEEP*2);
        pool.shutdown();
    }

    public static void fixed() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask("fixed"));
            pool.submit(new TargetTask("fixed"));
        }
        sleepMs(SLEEP*2);
        pool.shutdown();
    }

    public static void cached() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask("cached"));
            pool.submit(new TargetTask("cached"));
        }
        sleepMs(SLEEP*2);
        pool.shutdown();
    }

    public static void schedule() {
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 2; i++) {
            scheduled.scheduleAtFixedRate(new TargetTask("scheduled"), 0, 500, TimeUnit.MILLISECONDS);

        }
        sleepMs(SLEEP*2);
        scheduled.shutdown();
    }

    public static void sleepMs(int ms) {
        LockSupport.parkNanos(ms*1000L*1000L);
    }
}
