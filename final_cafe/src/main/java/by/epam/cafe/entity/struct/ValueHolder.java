package by.epam.cafe.entity.struct;

public class ValueHolder<T> {
    private T value;

    public ValueHolder() {
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
