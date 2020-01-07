package by.epam.task9.factory;

import by.epam.task9.entity.City;
import by.epam.task9.entity.Region;
import by.epam.task9.factory.exception.IllegalRegionParamsException;
import by.epam.task9.validator.RegionValidator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RegionFactory {

    private RegionValidator regionValidator = new RegionValidator();

    public Region create(Collection<City> cities, City regionCenter) throws IllegalRegionParamsException {
        Set<City> citySet = new HashSet<>(cities);

        Region region = new Region(citySet, regionCenter);

        if (regionValidator.isValid(region)) {
            return region;
        } else {
            throw new IllegalRegionParamsException();
        }
    }
}
