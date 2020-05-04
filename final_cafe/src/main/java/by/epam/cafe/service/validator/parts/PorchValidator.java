package by.epam.cafe.service.validator.parts;

public class PorchValidator implements by.epam.cafe.service.validator.Validator<Integer> {

    private static final int MIN_VALUE = 0;

    @Override
    public boolean isValid(Integer input) {
        return input > MIN_VALUE;
    }
}
