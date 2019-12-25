package by.epam.bus.dao.reader;

import by.epam.bus.entity.Person;
import by.epam.bus.factory.PersonFactory;
import by.epam.bus.factory.exception.IllegalPersonParamsException;

import java.util.Scanner;

public class PersonConsoleReader {
    private Scanner sc;
    private PersonFactory personFactory;

    public PersonConsoleReader() {
        sc = new Scanner(System.in);
        personFactory = new PersonFactory();
    }

    public Person readPerson() throws IllegalPersonParamsException {
        System.out.println("Enter name of person.");
        String name = sc.nextLine();
        System.out.println("Enter surname of person.");
        String surname = sc.nextLine();
        System.out.println("Enter father name of person.");
        String fatherName = sc.nextLine();
        return personFactory.create(name, surname, fatherName);
    }
}
