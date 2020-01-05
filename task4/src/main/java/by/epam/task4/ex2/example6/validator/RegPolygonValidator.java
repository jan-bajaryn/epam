package by.epam.task4.ex2.example6.validator;

import by.epam.task4.ex2.example6.entity.RegPolygon;

public class RegPolygonValidator {
    public boolean isValid(RegPolygon regPolygon) {
        return regPolygon.calcSideNumber() > 2 && regPolygon.calcSide() > 0;
    }
}
