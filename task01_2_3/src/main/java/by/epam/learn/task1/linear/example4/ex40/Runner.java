package by.epam.learn.task1.linear.example4.ex40;

public class Runner {

    //    -2х + 3х2 - 4х3
    private static double first(double x) {
        return x * (-2 + x * (3 - 4 * x));
    }

    //    -2х + 3х2 - 4х3
    private static double second(double x) {
        return 1 + x * (2 + x * (3 + 4 * x));
    }

    public static void main(String[] args) {
        System.out.println(first(1));
        System.out.println(second(1));

    }
}
