package by.epam.task04.ex3.example11.controller;

public class Runner {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        SumElemArrCommand sumElemArrCommand = new SumElemArrCommand();
        sumElemArrCommand.execute(arr, 1, 3);
        sumElemArrCommand.execute(arr, 3, 3);
        sumElemArrCommand.execute(arr, 4, 3);
    }
}
