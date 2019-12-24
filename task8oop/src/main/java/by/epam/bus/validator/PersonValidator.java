package by.epam.bus.validator;

import by.epam.bus.dao.Person;

import java.util.regex.Pattern;

public class PersonValidator implements Validator<Person> {

    public static final String REG_VALID = "[А-Яа-я+]";

    @Override
    public boolean isValid(Person person) {

        if (person == null) {
            return false;
        }

        String name = person.getName();
        String surname = person.getSurname();
        String fatherName = person.getFatherName();
//        Pattern pattern = Pattern.compile(REG_VALID, Pattern.UNICODE_CHARACTER_CLASS);
//        return pattern.matcher(name).matches()
//                && pattern.matcher(surname).matches()
//                && pattern.matcher(fatherName).matches();

//        return Pattern.compile(REG_VALID, Pattern.UNICODE_CHARACTER_CLASS).matcher(name).matches()
//                && Pattern.compile(REG_VALID, Pattern.UNICODE_CHARACTER_CLASS).matcher(surname).matches()
//                && Pattern.compile(REG_VALID, Pattern.UNICODE_CHARACTER_CLASS).matcher(fatherName).matches();
        return true;
    }
}
