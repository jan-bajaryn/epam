package by.epam.learn.task2.branching;

public class Forth40 {
    private double resultFunction(double x) {
        if (x <= 13) {
            return -Math.pow(x, 3) + 9;
        } else return -3 / (x + 1);
    }

    public static void main(String[] args) {
        Forth40 forth40 = new Forth40();
        System.out.println(forth40.resultFunction(34.5));
        System.out.println(forth40.resultFunction(0));
    }
}
