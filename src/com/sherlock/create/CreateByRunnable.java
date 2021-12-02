package com.sherlock.create;

/**
 * @Author sherlock
 */
public class CreateByRunnable {
    static class MultiThreadDemo implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread thread = new Thread(new MultiThreadDemo());
        thread.start();
    }
}
