package by.epam.task6.sorting.twosideselectsort.runner;

import by.epam.task6.sorting.selectsort.exception.IllegalInputArrayException;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) throws IllegalInputArrayException {
        int[] arr = {1, 5, 3, 2, 3, 4, 5, 6, 7, 2};
        twoSelectSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{1};
        twoSelectSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{};
        twoSelectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void twoSelectSort(int[] arr) throws IllegalInputArrayException {

        if (arr == null) {
            throw new IllegalInputArrayException();
        }

        int lastIndex = arr.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            int iMin = i;
            int iMax = lastIndex;
            for (int j = i + 1; j <= lastIndex; j++) {
                if (arr[j] < arr[iMin]) {
                    iMin = j;
                }
                if (arr[j] > arr[iMax]) {
                    iMax = j;
                }
            }
            if (arr[i] != arr[iMin]) {
                swap(arr, i, iMin);
            }
            if (arr[lastIndex] != arr[iMax]) {
                swap(arr, lastIndex, iMax);
            }
            lastIndex--;
        }
    }

    private static void swap(int[] arr, int f, int s) {
        int temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }
}
