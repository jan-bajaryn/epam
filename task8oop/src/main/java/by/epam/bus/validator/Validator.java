package by.epam.bus.validator;

public interface Validator<T> {
    boolean isValid(T t);
}
