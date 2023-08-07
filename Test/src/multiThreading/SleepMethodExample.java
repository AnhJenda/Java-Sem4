package multiThreading;

/*
    @author: Dinh Quang Anh
    Date   : 8/1/2023
    Project: Test
*/
public class SleepMethodExample extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("waiting for: " + (System.currentTimeMillis() - start));
        }
    }

    public static void main(String args[]) {
        SleepMethodExample t1 = new SleepMethodExample();
        t1.start();
    }
}
