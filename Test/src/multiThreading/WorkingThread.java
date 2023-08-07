package multiThreading;

/*
    @author: Dinh Quang Anh
    Date   : 8/1/2023
    Project: Test
*/
public class WorkingThread extends Thread{
    public WorkingThread(String name) {
        super(name);
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.printf("thread: %s has priority is %d \n", getName(), getPriority());
        }
    }
}
