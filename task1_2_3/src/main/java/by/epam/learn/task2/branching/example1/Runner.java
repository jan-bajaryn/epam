package by.epam.learn.task2.branching.example1;

public class Runner {
    public static void main(String[] args) {
        System.out.println(retInt());
    }

    private static int retInt() {
        if (1 < 2) {
            return 7;
        } else {
            return 8;
        }
    }
}
