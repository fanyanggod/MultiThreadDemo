package com.sherlock.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author sherlock
 */
public class CreateAndResult {

    static class MultiThreadDemo implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            int temp = 0;
            for (int i = 0; i < 5; i++) {
                temp += i;
            }
            return String.valueOf(temp);
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        // create callable instance
        MultiThreadDemo callable = new MultiThreadDemo();
        // create FutureTask
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        // create thread target
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
