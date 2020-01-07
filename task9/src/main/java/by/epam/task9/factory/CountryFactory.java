package by.epam.task9.factory;

import by.epam.task9.entity.City;
import by.epam.task9.entity.Country;
import by.epam.task9.entity.Region;
import by.epam.task9.factory.exception.IllegalCountryArgumentException;
import by.epam.task9.validator.CountryValidator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CountryFactory {
    private CountryValidator countryValidator = new CountryValidator();

    public Country create(Collection<Region> regions, City capital) throws IllegalCountryArgumentException {
        Set<Region> regionSet = new HashSet<>(regions);
        Country country = new Country(regionSet, capital);
        if (countryValidator.isValid(country)){
            return country;
        } else {
            throw new IllegalCountryArgumentException();
        }


    }
}
