package by.epam.task5.reversearr.controller;

public class Runner {
    public static void main(String[] args) {
        ReverseArrayCommand reverseArrayCommand = new ReverseArrayCommand();
        ReverseArrayRandomlyCommand reverseArrayRandomlyCommand = new ReverseArrayRandomlyCommand();
        int[] arr;

        arr = new int[]{};
        reverseArrayCommand.execute(arr);
        arr = new int[]{1};
        reverseArrayCommand.execute(arr);
        arr = new int[]{1, 2};
        reverseArrayCommand.execute(arr);
        arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        reverseArrayCommand.execute(arr);

        reverseArrayRandomlyCommand.execute(10, 3, 100);
    }

}
