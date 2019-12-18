package by.epam.task6.sorting.selectsort.runner;

import by.epam.task6.sorting.selectsort.exception.IllegalInputArrayException;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) throws IllegalInputArrayException {
        int[] arr = {1, 5, 3, 2, 3, 4, 5, 6, 7};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void selectSort(int[] arr) throws IllegalInputArrayException {

        if (arr == null) {
            throw new IllegalInputArrayException();
        }

        for (int i = 0; i < arr.length; i++) {
            int iMax = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[iMax]) {
                    iMax = j;
                }
            }
            swap(arr, i, iMax);
        }
    }

    private static void swap(int[] arr, int f, int s) {
        int temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }
}
