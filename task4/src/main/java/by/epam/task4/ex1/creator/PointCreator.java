package by.epam.task4.ex1.creator;

import by.epam.task4.ex1.domain.Point;

public class PointCreator {
    public Point create(double x, double y){
        return new Point(x,y);
    }
}
