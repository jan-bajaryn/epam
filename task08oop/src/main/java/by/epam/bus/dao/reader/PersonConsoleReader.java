package by.epam.bus.dao.reader;

import by.epam.bus.dao.PersonParams;
import by.epam.bus.entity.Person;
import by.epam.bus.factory.PersonFactory;
import by.epam.bus.factory.exception.IllegalPersonParamsException;
import by.epam.bus.view.ConsoleQuaryPerson;

import java.util.Scanner;

public class PersonConsoleReader {
    private Scanner sc = new Scanner(System.in);
    private PersonFactory personFactory = new PersonFactory();
    private ConsoleQuaryPerson consoleQuaryPerson = new ConsoleQuaryPerson();


    public Person readPerson() throws IllegalPersonParamsException {
        consoleQuaryPerson.quaryParameter(PersonParams.NAME.getTitle());
        String name = sc.nextLine();
        consoleQuaryPerson.quaryParameter(PersonParams.SURNAME.getTitle());
        String surname = sc.nextLine();
        consoleQuaryPerson.quaryParameter(PersonParams.FATHER_NAME.getTitle());
        String fatherName = sc.nextLine();
        return personFactory.create(name, surname, fatherName);
    }
}
