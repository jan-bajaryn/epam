package by.epam.learn.cycles;

public class Second11 {

    public static void main(String[] args) {
        double result =0;
        for (int i = 1; i < 201; i++) {
            result -= Math.pow(i,3);
        }

        System.out.println(result);
    }
}
