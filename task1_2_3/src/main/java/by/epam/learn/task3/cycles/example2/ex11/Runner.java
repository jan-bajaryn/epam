package by.epam.learn.task3.cycles.example2.ex11;

public class Runner {

    public static void main(String[] args) {

        System.out.println(sum(200));

    }

    private static double sum(int n) {
        double result = 0;
        for (int i = 1; i < n + 1; i++) {
            result -= Math.pow(i, 3);
        }

        return result;
    }
}
