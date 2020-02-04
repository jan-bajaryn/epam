package by.epam.task04.ex2.example6.service;

import by.epam.task04.ex2.example6.entity.RegPolygon;

public class RegShapeSquare {

    public double calculateSq(RegPolygon regPolygon){
        return (((regPolygon.calcSideNumber() /(double) 4) * (regPolygon.calcSide() * 2))
                * (1 / Math.tan(Math.PI / regPolygon.calcSideNumber())));
    }

}
