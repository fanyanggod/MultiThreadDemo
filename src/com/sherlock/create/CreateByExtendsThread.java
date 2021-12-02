package com.sherlock.create;

/**
 * @Author sherlock
 */
public class CreateByExtendsThread {
    static class MultiThreadDemo extends Thread {
        @Override
        public void run(){
            for (int i = 0; i < 5; i++) {
                System.out.println(getName()+":"+i);
            }
        }
    }

    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName());
        MultiThreadDemo demo = new MultiThreadDemo();
        demo.start();
    }
}
