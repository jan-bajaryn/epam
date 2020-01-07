package by.epam.task9.factory;

import by.epam.task9.entity.City;
import by.epam.task9.factory.exception.IllegalCityParamsException;
import by.epam.task9.validator.CityValidator;

public class CityFactory {

    private CityValidator cityValidator = new CityValidator();

    public City create(String name, int square) throws IllegalCityParamsException {
        City city = new City(square,name);
        if (cityValidator.isValid(city)){
            return city;
        } else {
            throw new IllegalCityParamsException();
        }
    }
}
