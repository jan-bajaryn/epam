package by.epam.cafe.service.validator;

/**
 * Dedicated to validate any type T
 *
 * @param <T> type of object what should be validate
 */
public interface Validator<T> {
    /**
     * @param input object for validation
     * @return true if obejct is valid, otherwise returns false
     */
    boolean isValid(T input);
}
