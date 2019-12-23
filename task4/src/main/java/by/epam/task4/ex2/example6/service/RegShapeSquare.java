package by.epam.task4.ex2.example6.service;

import by.epam.task4.ex2.example6.dao.RegPolygon;

public class RegShapeSquare {

    public double calculateSq(RegPolygon regPolygon){
        return (((regPolygon.getNumberSide() /(double) 4) * (regPolygon.getSide() * 2))
                * (1 / Math.tan(Math.PI / regPolygon.getNumberSide())));
    }

}
