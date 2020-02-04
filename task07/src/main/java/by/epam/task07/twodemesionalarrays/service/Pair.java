package by.epam.task07.twodemesionalarrays.service;

public class Pair {
    private Integer fst;
    private Integer sec;

    public Pair(Integer fst, Integer sec) {
        this.fst = fst;
        this.sec = sec;
    }

    public Integer getFst() {
        return fst;
    }

    public Integer getSec() {
        return sec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (fst != null ? !fst.equals(pair.fst) : pair.fst != null) return false;
        return sec != null ? sec.equals(pair.sec) : pair.sec == null;
    }

    @Override
    public int hashCode() {
        int result = fst != null ? fst.hashCode() : 0;
        result = 31 * result + (sec != null ? sec.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "fst=" + fst +
                ", sec=" + sec +
                '}';
    }
}
