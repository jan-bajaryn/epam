package by.epam.task12.entity;

public class Element {

    private static final int DEFAULT_VALUE = 0;

    private Integer value;

    public Element() {
        value = DEFAULT_VALUE;
    }

    public Element(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        return value != null ? value.equals(element.value) : element.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                '}';
    }
}
