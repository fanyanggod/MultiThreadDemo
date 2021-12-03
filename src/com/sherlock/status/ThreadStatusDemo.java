package com.sherlock.status;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sherlock
 */
public class ThreadStatusDemo{
    public static final long MAX_CYCLE = 5;
    static int threadNo = 0;
    static List<Thread> threads = new ArrayList<>();

    private static void printThreadStatus() {
        for (Thread thread : threads) {
            System.out.println(thread.getName() + " status:"+thread.getState());
        }
    }

    private static void addThread(Thread thread) {
        threads.add(thread);
    }

    static class StatusDemo extends Thread {
        public StatusDemo() {
            super("StatusDemoThread"+(++threadNo));
            addThread(this);
        }

        @Override
        public void run() {
            System.out.println(getName() + " status:" + getState());
            for (int i = 0; i < MAX_CYCLE; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printThreadStatus();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        addThread(Thread.currentThread());

        Thread sT1 = new StatusDemo();
        System.out.println(sT1.getName() + " status:"+sT1.getState());
        Thread sT2 = new StatusDemo();
        System.out.println(sT2.getName() + " status:"+sT2.getState());
        Thread sT3 = new StatusDemo();
        System.out.println(sT3.getName() + " status:"+sT3.getState());

        sT1.start();
        Thread.sleep(500);
        sT2.start();
        Thread.sleep(300);
        sT3.start();
        Thread.sleep(100);

    }

}