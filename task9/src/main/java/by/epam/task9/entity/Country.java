package by.epam.task9.entity;


import java.util.Set;

public class Country {

    public static int counter = 1;

    private int id;
    private Set<Region> regions;
    private City capital;


    {
        id = counter++;
    }

    public Country(Set<Region> regions, City capital) {
        this.regions = regions;
        this.capital = capital;
    }

    public int getId() {
        return id;
    }

    public City getCapital() {
        return capital;
    }

    public boolean contains(City city){
        return regions.contains(city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (id != country.id) return false;
        if (regions != null ? !regions.equals(country.regions) : country.regions != null) return false;
        return capital != null ? capital.equals(country.capital) : country.capital == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (regions != null ? regions.hashCode() : 0);
        result = 31 * result + (capital != null ? capital.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", regions=" + regions +
                ", capital=" + capital +
                '}';
    }
}
