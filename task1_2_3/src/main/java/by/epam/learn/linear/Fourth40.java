package by.epam.learn.linear;

public class Fourth40 {

    //    -2х + 3х2 - 4х3
    private double first(double x) {
        return x * (-2 + x * (3 - 4 * x));
    }

    //    -2х + 3х2 - 4х3
    private double second(double x) {
        return 1 + x * (2 + x * (3 + 4 * x));
    }

    public static void main(String[] args) {
        Fourth40 fourth40 = new Fourth40();
        System.out.println(fourth40.first(1));
        System.out.println(fourth40.second(1));

    }
}
