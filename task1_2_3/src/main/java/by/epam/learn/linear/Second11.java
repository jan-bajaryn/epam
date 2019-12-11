package by.epam.learn.linear;

public class Second11 {

    private double square(double cathet1, double cathet2) {
        if (cathet1 <= 0 || cathet2 <= 0)
            throw new IllegalArgumentException("cathet can't be less than or equals 0");

        return cathet1 * cathet2 / 2;
    }

    private double perimeter(double cathet1, double cathet2) {
        if (cathet1 <= 0 || cathet2 <= 0)
            throw new IllegalArgumentException("cathet can't be less than or equals 0");
        return cathet1 + cathet2 + Math.sqrt(Math.pow(cathet1, 2) + Math.pow(cathet2, 2));
    }

    public static void main(String[] args) {
        Second11 second11 = new Second11();
        System.out.println(second11.square(34, 45.23));
        System.out.println(second11.perimeter(34, 45.23));
    }
}
