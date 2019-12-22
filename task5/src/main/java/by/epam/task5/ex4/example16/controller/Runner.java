package by.epam.task5.ex4.example16.controller;

public class Runner {
    public static void main(String[] args) {
        FindMaxFormCommand findMaxFormCommand = new FindMaxFormCommand();

        double[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        findMaxFormCommand.execute(arr);
        arr = new double[]{2, 2};
        findMaxFormCommand.execute(arr);
        arr = new double[]{2, 5, 7};
        findMaxFormCommand.execute(arr);
        arr = new double[]{};
        findMaxFormCommand.execute(arr);
        arr = new double[]{1};
        findMaxFormCommand.execute(arr);

        arr = new double[]{1.5, 10.25, 10, 9};
        findMaxFormCommand.execute(arr);

        arr = new double[]{200, 10.25, 10, 9};
        findMaxFormCommand.execute(arr);

    }
}
