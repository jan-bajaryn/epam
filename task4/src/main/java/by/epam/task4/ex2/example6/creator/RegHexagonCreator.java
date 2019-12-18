package by.epam.task4.ex2.example6.creator;

import by.epam.task4.ex2.example6.domain.RegHexagon;
import by.epam.task4.ex2.example6.exception.InvalidRegHexagonException;
import by.epam.task4.ex2.example6.validator.RegHexagonValidator;
import by.epam.task4.ex2.example6.validator.RegPolygonValidator;

public class RegHexagonCreator {
    RegHexagonValidator regHexagonValidator;

    public RegHexagonCreator() {
        regHexagonValidator = new RegHexagonValidator();
    }

    public RegHexagon create(double side) throws InvalidRegHexagonException {
        RegHexagon regHexagon = new RegHexagon(side);
        if (regHexagonValidator.isValid(regHexagon)){
            return regHexagon;
        }
        else {
            throw new InvalidRegHexagonException();
        }
    }
}
