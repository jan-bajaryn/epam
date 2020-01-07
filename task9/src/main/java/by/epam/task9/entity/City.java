package by.epam.task9.entity;

public class City {

    public static int counter = 1;

    private int id;
    private int square;
    private String name;


    {
        id = counter++;
    }

    public City(int square, String name) {
        this.square = square;
        this.name = name;
    }

    public int getSquare() {
        return square;
    }

    public void changeSquare(int square) {
        if (square > 0) {
            this.square = square;
        }
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return id == city.id;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + square;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", square=" + square +
                ", name='" + name + '\'' +
                '}';
    }
}
