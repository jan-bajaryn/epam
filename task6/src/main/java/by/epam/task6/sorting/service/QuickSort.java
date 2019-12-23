package by.epam.task6.sorting.service;

public class QuickSort implements Sort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int p = partition(arr, begin, end);

            quickSort(arr, begin, p - 1);
            quickSort(arr, p + 1, end);
        }
    }

    private int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int wall = begin;
        for (int i = begin; i < end; i++) {
            if (arr[i] < pivot) {
                wall++;
                swap(arr, wall-1, i);
            }
        }

        swap(arr, wall, end);

        return wall;
    }

    private void swap(int[] arr, int f, int s) {
        int temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }
}
