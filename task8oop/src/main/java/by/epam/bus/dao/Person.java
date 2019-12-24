package by.epam.bus.dao;

public class Person {
    private String name;
    private String surname;
    private String fatherName;


    public Person(String name, String surname, String fatherName) {
        this.name = name;
        this.surname = surname;
        this.fatherName = fatherName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fatherName='" + fatherName + '\'' +
                '}';
    }
}
