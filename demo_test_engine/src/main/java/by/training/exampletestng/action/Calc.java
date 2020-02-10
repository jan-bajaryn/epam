package by.training.exampletestng.action;

public class Calc {

    public int sum(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return 0;
    }

    public int mul(int a, int b) {
        return 0;
    }

    public int div(int a, int b) throws Exception {
        int c;
        return 0;
    }

    public double sqrt(double a) throws Exception {
        if (a >= 0) {
            return Math.sqrt(a);
        } else {
            throw new Exception();
        }

    }
}