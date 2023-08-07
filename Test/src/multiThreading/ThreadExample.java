package multiThreading;

/*
    @author: Dinh Quang Anh
    Date   : 8/1/2023
    Project: Test
*/
public class ThreadExample {
    public static void main(String[] args) {
        Thread t1 = new WorkingThread("thread 1");
        Thread t2 = new WorkingThread("thread 2");
        Thread t3 = new WorkingThread("thread 3");

        System.out.println("ID thread 1: " + t1.getId());
        System.out.println("ID thread 2: " + t2.getId());
        System.out.println("ID thread 3: " + t3.getId());

        t1.setPriority(1);
        t2.setPriority(5);
        t3.setPriority(10);

        t1.start();
        t2.start();
        t3.start();
    }
}
