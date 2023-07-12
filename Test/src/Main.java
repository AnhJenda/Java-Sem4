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
    }
}
