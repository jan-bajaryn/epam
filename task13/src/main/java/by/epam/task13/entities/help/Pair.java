package by.epam.task13.entities.help;

public class Pair<T,K> {
    private T fst;
    private K sec;

    public Pair(T fst, K sec) {
        this.fst = fst;
        this.sec = sec;
    }

    public Pair() {
    }

    public T getFst() {
        return fst;
    }

    public void setFst(T fst) {
        this.fst = fst;
    }

    public K getSec() {
        return sec;
    }

    public void setSec(K sec) {
        this.sec = sec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

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
