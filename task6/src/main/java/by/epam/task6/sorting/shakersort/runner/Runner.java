package by.epam.task6.sorting.shakersort.runner;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 2, 3, 2, 3, 4, 4};
        System.out.println("Исходник = " + Arrays.toString(arr));
        shakerSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{1, 4};
        System.out.println("Исходник = " + Arrays.toString(arr));
        shakerSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{4, 1};
        System.out.println("Исходник = " + Arrays.toString(arr));
        shakerSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{};
        System.out.println("Исходник = " + Arrays.toString(arr));
        shakerSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{1};
        System.out.println("Исходник = " + Arrays.toString(arr));
        shakerSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static void shakerSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
// i отвечает за шаги
        int left = 0;
        int right = arr.length - 1;
        int k = 0;        //индекс последнего обмена

        while (left < right) {
            for (int j = left; j < right; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    k = j;
                }
            }
            right = k;
            for (int j = right - 1; j >= left; j--) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    k = j;
                }
            }
            left = k + 1;
        }


    }


    private static void swap(int[] arr, int f, int s) {
        int temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }
}
