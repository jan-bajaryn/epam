package by.epam.task4.ex2.example6.validator;

import by.epam.task4.ex2.example6.dao.RegPolygon;

public class RegPolygonValidator {
    public boolean isValid(RegPolygon regPolygon) {
        return regPolygon.getNumberSide() > 2 && regPolygon.getSide() > 0;
    }
}
