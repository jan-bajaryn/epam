package by.epam.cafe.service.validator;

public interface Validator<T> {
    boolean isValid(T input);
}
