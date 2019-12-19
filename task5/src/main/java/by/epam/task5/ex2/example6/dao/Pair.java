package by.epam.task5.ex2.example6.dao;

public class Pair {
    private Integer f;
    private Integer s;

    public Pair(Integer f, Integer s) {
        this.f = f;
        this.s = s;
    }

    public Integer getF() {
        return f;
    }

    public Integer getS() {
        return s;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "f=" + f +
                ", s=" + s +
                '}';
    }
}
