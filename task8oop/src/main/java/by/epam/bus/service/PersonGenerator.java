package by.epam.bus.service;

import by.epam.bus.entity.Person;
import by.epam.bus.factory.PersonFactory;
import by.epam.bus.factory.exception.IllegalPersonParamsException;

import java.util.List;
import java.util.Random;

public class PersonGenerator {
    private PersonFactory personFactory=new PersonFactory();


    public Person generatePerson(List<String> names, List<String> surnames, List<String> fatherNames) throws IllegalPersonParamsException {
        Random r = new Random();
        return personFactory.create(names.get(r.nextInt(names.size())),
                surnames.get(r.nextInt(surnames.size())),
                fatherNames.get(r.nextInt(fatherNames.size())));
    }
}