package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate house
 */
public class HouseValidatorOrder implements Validator<String> {
    public static final int MAX_LENGTH = 10;
    private static HouseValidatorOrder INSTANCE = new HouseValidatorOrder();

    public static HouseValidatorOrder getInstance() {
        return INSTANCE;
    }

    private HouseValidatorOrder() {
    }

    @Override
    public boolean isValid(String input) {
        return input != null && !input.isEmpty() && input.length()< MAX_LENGTH;
    }
}
