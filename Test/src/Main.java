import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/*
    @author: Dinh Quang Anh
    Date   : 7/11/2023
    Project: Test
*/
public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("John");
        Person person2 = new Person(person1); // Object Copy
        System.out.println(person2.getName()); // Output: John

        person2.setName("Jane");

        System.out.println(person1.getName()); // Output: John
        System.out.println(person2.getName()); // Output: Jane

        Long a = Long.valueOf(130);
        Long b = Long.valueOf(130);

        System.out.println(a == b);
        System.out.println("-----------------------LinkedList Queue---------------------------");

        Queue<Integer> linkedListQueue = new LinkedList<>();

        linkedListQueue.offer(1);
        linkedListQueue.offer(3);
        linkedListQueue.offer(5);
        linkedListQueue.offer(3);

        System.out.println(linkedListQueue);

        linkedListQueue.remove(5);
        System.out.println(linkedListQueue);

        System.out.println("-------------------Priority Queue-----------------------");

        Queue<Integer> priorityQueue = new PriorityQueue<>();

        priorityQueue.offer(1);
        priorityQueue.offer(3);
        priorityQueue.offer(3);
        priorityQueue.offer(5);

        System.out.println(priorityQueue);


        System.out.println("---------------------priority Blocking queue-------------------");
        Queue<Integer> numbers = new PriorityBlockingQueue<>();
        numbers.add(12);
        System.out.println("Danh sách ban đầu: ");
        System.out.println(numbers);
        numbers.add(11);
        System.out.println("Danh sách ban đầu: ");
        System.out.println(numbers);
        numbers.add(10);
        System.out.println("Danh sách ban đầu: ");
        System.out.println(numbers);
        numbers.add(9);

        System.out.println("Danh sách ban đầu: ");
        System.out.println(numbers);

        System.out.println("\nPhần tử đầu tiên: " + numbers.poll());

        numbers.offer(1);
        System.out.println("Danh sách sau khi thêm: ");
        System.out.println(numbers);

        System.out.println("Phần tử đầu tiên : " +numbers.peek());

    }
}
