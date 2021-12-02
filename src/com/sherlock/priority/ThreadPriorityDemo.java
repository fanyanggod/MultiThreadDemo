package com.sherlock.priority;

/**
 * @Author sherlock
 */
public class ThreadPriorityDemo {

    static class PriorityDemo extends Thread{
        //set thread name
        static int threadNo = 1;
        public PriorityDemo() {
            super("priority-thread-"+threadNo);
            threadNo++;
        }

        // calculation opportunity
        public long opportunities = 0;
        @Override
        public void run() {
            while(true){
                opportunities++;
            }
        }
    }

    public static void main(String[] arg) {
        PriorityDemo[] threads = new PriorityDemo[10];
        for (int i = 0; i < 10; i++) {
            //create thread
            threads[i] = new PriorityDemo();
            //set priority
            threads[i].setPriority(i+1);
        }
        for (PriorityDemo thread : threads) {
            //start all thread
            thread.start();
        }

        //sleep thread
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (PriorityDemo thread : threads) {
            //stop all thread
            thread.stop();
        }

        for (PriorityDemo thread : threads) {
            System.out.println(thread.getName() + ": Priority-"+ thread.getPriority() +" Opportunity-"+thread.opportunities);
        }
    }
}
