package by.epam.task05.ex2.example6.controller;

public class Runner {
    public static void main(String[] args) {
        int[] arr = {-2, 3, 5, 3};
        NumericAxisCommand numericAxisCommand = new NumericAxisCommand();
        numericAxisCommand.execute(arr);
        arr = new int[]{};
        numericAxisCommand.execute(arr);
        arr = new int[]{1};
        numericAxisCommand.execute(arr);
    }
}
