package by.epam.learn.task1.linear.example4;

public class Runner {

    //    -2х + 3х2 - 4х3
    private double first(double x) {
        return x * (-2 + x * (3 - 4 * x));
    }

    //    -2х + 3х2 - 4х3
    private double second(double x) {
        return 1 + x * (2 + x * (3 + 4 * x));
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        System.out.println(runner.first(1));
        System.out.println(runner.second(1));

    }
}