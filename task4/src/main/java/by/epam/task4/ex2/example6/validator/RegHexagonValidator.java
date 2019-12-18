package by.epam.task4.ex2.example6.validator;

import by.epam.task4.ex2.example6.domain.RegHexagon;

public class RegHexagonValidator {
    private RegPolygonValidator regPolygonValidator;

    public RegHexagonValidator() {
        regPolygonValidator = new RegPolygonValidator();
    }

    public boolean isValid(RegHexagon regHexagon) {
        return regPolygonValidator.isValid(regHexagon) && regHexagon.getNumberSide() == 6;
    }
}
