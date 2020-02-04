package by.epam.bus.validator;

import by.epam.bus.entity.Person;

import java.util.regex.Pattern;

public class PersonValidator implements Validator<Person> {

    public static final String REG_VALID = "^[a-ЯА-Я]*$";

    @Override
    public boolean isValid(Person person) {

        if (person == null) {
            return false;
        }

        String name = person.getName();
        String surname = person.getSurname();
        String fatherName = person.getFatherName();
        Pattern pattern = Pattern.compile(REG_VALID, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        return pattern.matcher(name).matches()
                && pattern.matcher(surname).matches()
                && pattern.matcher(fatherName).matches();
    }
}
