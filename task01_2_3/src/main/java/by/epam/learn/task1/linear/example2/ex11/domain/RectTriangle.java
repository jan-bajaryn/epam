package by.epam.learn.task1.linear.example2.ex11.domain;

public class RectTriangle {
    private double cathet1;
    private double cathet2;

    public double getCathet1() {
        return cathet1;
    }

    public double getCathet2() {
        return cathet2;
    }

    public RectTriangle(double cathet1, double cathet2) {
        this.cathet1 = cathet1;
        this.cathet2 = cathet2;
    }

    public double square() {
        if (cathet1 <= 0 || cathet2 <= 0)
            throw new IllegalArgumentException("cathet can't be less than or equals 0");

        return cathet1 * cathet2 / 2;
    }

    public double perimeter() {
        if (cathet1 <= 0 || cathet2 <= 0)
            throw new IllegalArgumentException("cathet can't be less than or equals 0");
        return cathet1 + cathet2 + Math.sqrt(Math.pow(cathet1, 2) + Math.pow(cathet2, 2));
    }

}
