package by.epam.task9.entity;

import java.util.HashSet;
import java.util.Set;

public class Region {

    public static int counter = 1;

    private int id;
    private Set<City> cities;
    private City regionCenter;

    {
        id = counter++;
    }

    public Region(Set<City> cities, City regionCenter) {
        this.cities = cities;
    }

    public Region() {
        cities = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public Set<City> getCities() {
        return cities;
    }

    public City getRegionCenter() {
        return regionCenter;
    }

    public boolean contains(City city){
        return cities.contains(city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (id != region.id) return false;
        if (cities != null ? !cities.equals(region.cities) : region.cities != null) return false;
        return regionCenter != null ? regionCenter.equals(region.regionCenter) : region.regionCenter == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cities != null ? cities.hashCode() : 0);
        result = 31 * result + (regionCenter != null ? regionCenter.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", cities=" + cities +
                ", regionCenter=" + regionCenter +
                '}';
    }
}
