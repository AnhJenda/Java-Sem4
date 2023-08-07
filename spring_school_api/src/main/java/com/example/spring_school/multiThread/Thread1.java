package com.example.spring_school.multiThread;

/*
    @author: Dinh Quang Anh
    Date   : 7/31/2023
    Project: spring_school
*/
public class Thread1 extends Thread{ // cách 1 kế thừa Thread
//    // run()
//    @Override
//    public void run(){
//        System.out.println("Thread running");
//    }
//
//    public static void main(String[] args) {
//        Thread1 thread1 = new Thread1();
//        thread1.start();  // cấp phát tài nguyên => run()
//    }
    private String threadName;
    private int i;

    public Thread1(int i, String name) {
        this.i = i;
        this.threadName = name;
        System.out.println("Created: " + threadName);
    }
    @Override
    public void run(){
        System.out.println("running thread: " +threadName);
        try {
            Thread.sleep(1000 * i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //not multi-threading
        int m = 10;
        long start_time = System.currentTimeMillis();
//        for (int i = 1; i <= m; i++){
//            try {
//                Thread.sleep(1000*i);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("Process time without multi-threading: " +
//                (System.currentTimeMillis() - start_time));
        // multi-threading
        for (int i = 1; i <= m; i++){
            Thread1 thread1 = new Thread1(i, "thread1");
            thread1.start();
        }
        System.out.println("Process time without multi-threading: " +
                (System.currentTimeMillis() - start_time));
    }
}
