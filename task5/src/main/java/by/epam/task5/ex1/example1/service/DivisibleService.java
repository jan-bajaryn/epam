package by.epam.task5.ex1.example1.service;

import by.epam.task5.ex1.example1.exception.IllegalInputNaturalException;

public class DivisibleService {

    public int sumDivident(int divider, int[] arr) throws IllegalInputNaturalException {

        if (arr == null) {
            return 0;
        }

        if (!checkInputNatural(arr)) {
            throw new IllegalInputNaturalException();
        }

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (isDivisible(arr[i], divider)) {
                result += arr[i];
            }
        }
        return result;
    }

    private boolean isDivisible(int divident, int divider) {
        return divident % divider == 0;
    }

    private boolean checkInputNatural(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 1) {
                return false;
            }
        }
        return true;
    }


}
