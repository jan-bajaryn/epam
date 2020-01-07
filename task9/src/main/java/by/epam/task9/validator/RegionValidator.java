package by.epam.task9.validator;

import by.epam.task9.entity.Region;

public class RegionValidator  {
    public boolean isValid (Region region) {
        return region.contains(region.getRegionCenter());
    }
}
