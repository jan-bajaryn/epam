package by.epam.cafe.service;

public interface Validator<T> {
    boolean isValid(T input);
}
