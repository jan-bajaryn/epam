package by.epam.cafe.service.parser.parts;

import by.epam.cafe.service.Validator;

import java.util.Optional;

public abstract class ParamsParser<T> {
    private Validator<T> validator;

    public ParamsParser(Validator<T> validator) {
        this.validator = validator;
    }

    protected abstract T modify(String input) throws Exception;

    public Optional<T> parse(String input) {
        try {
            T modify = modify(input);
            if (validator.isValid(modify)) {
                return Optional.of(modify);
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
