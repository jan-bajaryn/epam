package by.epam.task5.taskarrrev.controller;

import java.util.Arrays;

public class ReverseArrayCommand {
    private ArrayReverser arrayReverser;

    public ReverseArrayCommand() {
        arrayReverser = new ArrayReverser();
    }

    public void execute(int[] arr) {
        System.out.println("Input array is = " + Arrays.toString(arr));
        System.out.println("Reversed array is = " + Arrays.toString(arrayReverser.reverseArr(arr)));
    }
}
