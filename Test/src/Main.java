import java.util.*;
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

        System.out.println("-----------------Set--------------------");

        Set<String> personSet = new HashSet<>();

        personSet.add("person1");
        personSet.add("person2");
        personSet.add("person3");
        personSet.add("person4");
        personSet.add("person5");
        personSet.add("person6");
        personSet.add("person7");
        personSet.add("person8");
        personSet.add("person9");

        for (String p : personSet){
            System.out.println(p.hashCode());
        }

        System.out.println(personSet);
        System.out.println("-----------------TreeMap with null key---------------");
        TreeMap<String, Integer> tm = new TreeMap<>(Comparator.nullsFirst(Comparator.naturalOrder()));
        tm.put("middle", 23);
        tm.put(null, 24);
        tm.put(null, 25);
        tm.put("end", 25);

        System.out.println(tm);  // null = 25, end = 25, middle = 23

        System.out.println("----------ArrayList--------------");
        ArrayList<Integer> intArr = new ArrayList<>();
        for (int i = 1; i < 10; i++){
            intArr.add(i);
        }

        int hashCode = System.identityHashCode(intArr);
        System.out.println(intArr);
        System.out.println("Địa chỉ bộ nhớ của ArrayList: " + Integer.toHexString(hashCode));
        intArr.add(11);
        intArr.add(12);
        intArr.add(13);
        intArr.add(13);
        intArr.add(13);
        hashCode = System.identityHashCode(intArr);
        System.out.println(intArr);
        System.out.println("Địa chỉ bộ nhớ của ArrayList: " + Integer.toHexString(hashCode));
        System.out.println("-----------TreeSet with null element----------------");
        TreeSet<Integer> personTSet = new TreeSet<>();
        for (int i = 0; i < 10; i ++){
            personTSet.add(i);
        }
        System.out.println(personTSet); // [0 -> 9]

//        personTSet.add(null);

        System.out.println(personTSet);  // throw NullPointerException

        TreeSet<String> treeSet = new TreeSet<>();

        treeSet.add("apple");
        treeSet.add("banana");
//        treeSet.add(null);
        treeSet.add("orange");

        System.out.println("TreeSet: " + treeSet); // throw NullPointerException

    }
}
