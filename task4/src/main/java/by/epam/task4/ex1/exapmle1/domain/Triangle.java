package by.epam.task4.ex1.exapmle1.domain;

public class Triangle {
    private Point f;
    private Point s;
    private Point t;

    public Triangle(Point f, Point s, Point t) {
        this.f = f;
        this.s = s;
        this.t = t;
    }

    public Point getF() {
        return f;
    }

    public Point getS() {
        return s;
    }

    public Point getT() {
        return t;
    }
}
