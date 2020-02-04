package by.epam.task05.shiftarr.service;

import by.epam.task05.shiftarr.service.exceptiion.IllegalCountException;
import by.epam.task05.shiftarr.service.exceptiion.NullShiftArrayException;

public class ArrayShifter {

    public void shiftArr(int[] arr, int count) throws NullShiftArrayException, IllegalCountException {
        if (count < 0)
            throw new IllegalCountException();

        for (int i = 0; i < count; i++) {
            shiftArr(arr);
        }
    }

    private void shiftArr(int[] arr) throws NullShiftArrayException {
        if (arr == null)
            throw new NullShiftArrayException();
        if (arr.length == 1)
            return;

        int temp = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = temp;
    }
}
