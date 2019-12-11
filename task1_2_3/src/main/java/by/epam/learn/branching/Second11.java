package by.epam.learn.branching;

public class Second11 {

    //find square by 3 sides of triangle


    public static void main(String[] args) {
        Triangle triangle = new Triangle(3, 4, 5);
        Triangle triangle1 = new Triangle(4, 5, 6);
        System.out.println(triangle.compareTo(triangle1));
        System.out.println(triangle.compareTo(triangle));
    }
}

class Triangle implements Comparable<Triangle> {
    private double firstSide;
    private double secondSide;
    private double thirdSide;

    public Triangle(double firstSide, double secondSide, double thirdSide) {

        if (!sidesOk(firstSide, secondSide, thirdSide))
            throw new IllegalArgumentException("Wrong sides of triangle.");

        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;

    }

    private boolean isMoreThanZero(double f, double s, double t) {
        return f > 0 || s > 0 || t > 0;
    }

    private boolean sumSidesOk(double f, double s, double t) {
        return f + s > t || f + t > s || s + t > f;
    }

    private boolean sidesOk(double f, double s, double t) {
        return isMoreThanZero(f, s, t) && sumSidesOk(f, s, t);
    }

    private double square(double f, double s, double t) {

        if (!isMoreThanZero(f, s, t))
            throw new IllegalArgumentException("side of triangle can't be less or equal 0");

        if (!sumSidesOk(f, s, t))
            throw new IllegalArgumentException("one side can't be more that sum of two another");

        double h = (f + s + t) / 2;
        return Math.sqrt(h * (h - f) * (h - s) * (h - t));
    }

    @Override
    public int compareTo(Triangle o) {
        if (o == null) return 1;
        double result = square(firstSide, secondSide, thirdSide) - square(o.firstSide, o.secondSide, o.thirdSide);
        if (result > 0)
            return 1;
        if (result < 0)
            return -1;
        return 0;
    }
}
