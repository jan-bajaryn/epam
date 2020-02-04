package by.epam.learn.task2.branching.example3.ex30;

public class Runner {
    public static void main(String[] args) {
        run();

    }

    private static void run() {
        double a = 20.45;
        double b = 11.4;
        double c = 13.2;

        if (a > b && b > c) {
            a = a * 2;
            b = b * 2;
            c = c * 2;
        } else {
            a = Math.abs(a);
            b = Math.abs(b);
            c = Math.abs(c);
        }


        System.out.println("a = " + a + ", b = " + b + ", c = " + c);
    }
}
