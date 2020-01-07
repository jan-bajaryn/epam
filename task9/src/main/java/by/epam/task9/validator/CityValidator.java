package by.epam.task9.validator;

import by.epam.task9.entity.City;

public class CityValidator {
    public boolean isValid (City city) {
        return !city.getName().isEmpty() && city.getSquare() > 0;
    }
}
