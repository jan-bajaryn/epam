package by.epam.learn.task1.linear.example1;


public class Runner {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(3, 4));
        System.out.println(calculator.subtract(45.3, 34.22));
        System.out.println(calculator.multiplication(44.3, 22.22));
        System.out.println(calculator.divide(44.3, 22.22));
    }
}
