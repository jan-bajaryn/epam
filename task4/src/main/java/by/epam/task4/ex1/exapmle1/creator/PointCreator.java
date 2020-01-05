package by.epam.task4.ex1.exapmle1.creator;

import by.epam.task4.ex1.exapmle1.entity.Point;

public class PointCreator {
    public Point create(double x, double y){
        return new Point(x,y);
    }
}
