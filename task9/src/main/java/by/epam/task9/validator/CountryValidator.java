package by.epam.task9.validator;

import by.epam.task9.entity.Country;

public class CountryValidator {
    public boolean isValid(Country country) {
        return country != null && country.contains(country.getCapital());
    }
}
