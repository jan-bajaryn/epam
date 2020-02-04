package by.epam.task05.shiftarr.controller;

import by.epam.task05.shiftarr.service.exceptiion.IllegalCountException;
import by.epam.task05.shiftarr.service.exceptiion.NullShiftArrayException;
import by.epam.task05.shiftarr.service.ArrayShifter;

import java.util.Arrays;

public class ArrayShifterCommand {
    private ArrayShifter arrayShifter;

    public ArrayShifterCommand() {
        arrayShifter = new ArrayShifter();
    }

    public void execute(int[] arr, int count) {
        try {
            System.out.println("This is array before shifting = " + Arrays.toString(arr));
            arrayShifter.shiftArr(arr, count);
            System.out.println("This is array after shifting = " + Arrays.toString(arr));
        } catch (NullShiftArrayException e) {
            System.out.println("Array can't be null");
        } catch (IllegalCountException e) {
            System.out.println("Count of shift can't be less that 0");
        }
    }
}
