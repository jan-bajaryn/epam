package by.epam.task6.sorting.bublesort.runner;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 2, 3, 2, 3, 4, 4};
        System.out.println(Arrays.toString(arr));
        bubleSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static void bubleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
// i отвечает за шаги
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i-1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }

    }


    private static void swap(int[] arr, int f, int s) {
        int temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }
}
