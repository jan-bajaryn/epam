package by.epam.learn.task1.linear;


public class First01 {

    private double add(double x, double y) {
        return x + y;
    }

    private double subtract(double x, double y) {
        return x - y;
    }

    private double multiplication(double x, double y) {
        return x * y;
    }

    //может быть проблема с точностью
    // маленькие цифры могут быть равны
    private double divide(double x, double y) {
        if (y == 0)
            throw new ArithmeticException("division by zero");
        return x / y;
    }

    public static void main(String[] args) {
        First01 first01 = new First01();
        System.out.println(first01.add(3, 4));
        System.out.println(first01.subtract(45.3, 34.22));
        System.out.println(first01.multiplication(44.3, 22.22));
        System.out.println(first01.divide(44.3, 22.22));
    }
}
