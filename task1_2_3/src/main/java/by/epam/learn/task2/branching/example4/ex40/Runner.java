package by.epam.learn.task2.branching.example4.ex40;

public class Runner {
    private static double resultFunction(double x) {
        if (x <= 13) {
            return -Math.pow(x, 3) + 9;
        } else return -3 / (x + 1);
    }

    public static void main(String[] args) {
        System.out.println(resultFunction(34.5));
        System.out.println(resultFunction(0));
    }
}
