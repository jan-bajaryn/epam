package by.epam.bus.factory;

import by.epam.bus.entity.Person;
import by.epam.bus.factory.exception.IllegalPersonParamsException;
import by.epam.bus.validator.PersonValidator;

public class PersonFactory {

    private PersonValidator personValidator;

    public PersonFactory() {
        personValidator = new PersonValidator();
    }

    public Person create(String name, String surname, String fatherName) throws IllegalPersonParamsException {
        Person person = new Person(name, surname, fatherName);
        if (personValidator.isValid(person)) {
            return person;
        } else {
            throw new IllegalPersonParamsException();
        }
    }
}
