package com.example.spring_school.multiThread;

/*
    @author: Dinh Quang Anh
    Date   : 7/31/2023
    Project: spring_school
*/
public class RunnableDemo implements Runnable{
    private String threadName;
    private int i;

    private String result;



    public RunnableDemo(int i, String threadName) {
        this.i = i;
        this.threadName = threadName;
        System.out.println("Creating..... " + threadName);
    }

    @Override
    public void run() {
        try {
            System.out.println("Running thread: " + threadName);
            Thread.sleep(1000 * i);
            this.result = "result of thread " + threadName;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // not multi-threading
        int m = 10;
        long start_time = System.currentTimeMillis();
//        for (int i = 1; i <= m; i++) {
//            try {
//                Thread.sleep(1000*i);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        System.err.println("Process time in not multi-thread: " + (System.currentTimeMillis() - start_time));

        // multi-threading
        for (int i = 1; i <= m; i++) {
            RunnableDemo runableDemo = new RunnableDemo(i, "thread" + 1);
            Thread thread = new Thread(runableDemo);
            thread.start();
            System.err.println("result = " + runableDemo.result);
        }

        System.err.println("Process time in not multi-thread: " + (System.currentTimeMillis() - start_time));

    }
}