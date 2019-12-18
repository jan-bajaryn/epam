package by.epam.task5.taskarrrev.runner;

import by.epam.task5.taskarrrev.controller.ReverseArrayCommand;
import by.epam.task5.taskarrrev.controller.ReverseArrayRandomly;

public class Runner {
    public static void main(String[] args) {
        ReverseArrayCommand reverseArrayCommand = new ReverseArrayCommand();
        ReverseArrayRandomly reverseArrayRandomly = new ReverseArrayRandomly();
        int[] arr;

        arr = new int[]{};
        reverseArrayCommand.execute(arr);
        arr = new int[]{1};
        reverseArrayCommand.execute(arr);
        arr = new int[]{1, 2};
        reverseArrayCommand.execute(arr);
        arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        reverseArrayCommand.execute(arr);

        reverseArrayRandomly.execute(10, 3, 100);
    }

}
