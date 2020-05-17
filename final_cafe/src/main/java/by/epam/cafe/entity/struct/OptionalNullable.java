package by.epam.cafe.entity.struct;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalNullable<T> {
    private final Optional<T> value;
    private final boolean containsNull;

    private OptionalNullable(Optional<T> value, boolean containsNull) {
        this.value = value;
        this.containsNull = containsNull;
    }

    public static <T> OptionalNullable<T> of(T input) {
        if (input == null) {
            return new OptionalNullable<T>(Optional.empty(), true);
        } else {
            return new OptionalNullable<>(Optional.of(input), false);
        }
    }

    public static <T> OptionalNullable<T> empty() {
        return new OptionalNullable<>(Optional.empty(), false);
    }

    public boolean isPresent() {
        return containsNull || value.isPresent();
    }

    public boolean isEmpty() {
        return !isPresent();
    }

    public T get() throws NoSuchElementException {
        if (containsNull) {
            return null;
        }
        if (value.isPresent()) {
            return value.get();
        }
        throw new NoSuchElementException("Optional is empty.");
    }
}
