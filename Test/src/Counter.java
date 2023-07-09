/*
    @author: Dinh Quang Anh
    Date   : 7/4/2023
    Project: Test
*/
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
}

