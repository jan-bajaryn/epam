package by.epam.task4.ex3.example11.controller;

import by.epam.task4.ex3.example11.exception.IllegalCountException;
import by.epam.task4.ex3.example11.exception.NullArrayException;

import java.util.Arrays;

public class SumElemArrCommand {
    private SumElemArr sumElemArr;

    public SumElemArrCommand() {
        sumElemArr = new SumElemArr();
    }

    public void execute(int[] arr, int begin, int count) {
        try {
            System.out.println("Sum of elements of array with begin index = "
                    + begin
                    + ", and count = "
                    + count + " is equal = "
                    + sumElemArr.sum(arr, begin, count));
            System.out.println("The array: "+ Arrays.toString(arr));
        } catch (NullArrayException e) {
            System.out.println("Array can't be null");
        } catch (IllegalCountException e) {
            System.out.println("There no such elements in the array.");
        }
    }
}
