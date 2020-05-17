package by.epam.cafe.service.parser.parts;

import by.epam.cafe.entity.struct.OptionalNullable;
import by.epam.cafe.service.validator.Validator;

public abstract class ParamsParser<T> {
    private Validator<T> validator;

    public ParamsParser(Validator<T> validator) {
        this.validator = validator;
    }

    protected abstract T modify(String input) throws Exception;

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
