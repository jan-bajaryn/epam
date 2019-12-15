package by.epam.learn.task1.linear.example1;

public class Calculator {

    public double add(double x, double y) {
        return x + y;
    }

    public double subtract(double x, double y) {
        return x - y;
    }

    public double multiplication(double x, double y) {
        return x * y;
    }

    public double divide(double x, double y) {
        if (y == 0)
            throw new ArithmeticException("division by zero");
        return x / y;
    }

}
