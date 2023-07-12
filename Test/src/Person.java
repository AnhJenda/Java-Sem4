/*
    @author: Dinh Quang Anh
    Date   : 7/11/2023
    Project: Test
*/
public class Person {
    private String name;


    public Person(Person person1) {
        this.name = person1.getName();
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
