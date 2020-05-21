package by.epam.cafe.service.parser.parts;

import by.epam.cafe.entity.struct.OptionalNullable;
import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to parse String parameter to any declared type, validate it and return
 * Optional value if parameter is valid
 *
 * @param <T> type of output parameter after parsing
 */
public abstract class ParamsParser<T> {
    private Validator<T> validator;

    public ParamsParser(Validator<T> validator) {
        this.validator = validator;
    }

    /**
     * @param input String input to parse to T type
     * @return instance of parsed parameter
     * @throws Exception if parsing is failed
     */
    protected abstract T modify(String input) throws Exception;

    /**
     * @param input String input to parse to T type
     * @return not empty OptionalNullable value if parsing and
     * validation was successful, otherwise return empty OptionalNullable
     */
    public OptionalNullable<T> parse(String input) {
        try {
            T modify = modify(input);
            if (validator.isValid(modify)) {
                return OptionalNullable.of(modify);
            }
            return OptionalNullable.empty();
        } catch (Exception e) {
            return OptionalNullable.empty();
        }
    }
}
