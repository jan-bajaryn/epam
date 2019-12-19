package by.epam.task6.sorting.insertsort.runner;

import by.epam.task6.sorting.selectsort.exception.IllegalInputArrayException;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) throws IllegalInputArrayException {
        int[] arr = {1, 5, 3, 2, 3, 4, 5, 6, 7};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void insertSort(int[] arr) throws IllegalInputArrayException {

        if (arr == null) {
            throw new IllegalInputArrayException();
        }
        if (arr.length < 1)
            return;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                insertToPrev(arr, i);
            }
        }
    }

    private static void insertToPrev(int[] arr, int index) {
        for (int i = 0; i < index; i++) {
            if (arr[i] > arr[index]) {
                insert(arr, index, i);
                return;
            }
        }
    }

    private static void insert(int[] arr, int indexFrom, int indexTo) {
        int temp = arr[indexFrom];
        for (int i = indexFrom; i > indexTo; i--) {
            arr[i] = arr[i - 1];
        }
        arr[indexTo] = temp;
    }



}
