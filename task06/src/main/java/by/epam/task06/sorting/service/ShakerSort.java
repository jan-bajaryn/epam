package by.epam.task06.sorting.service;

public class ShakerSort implements Sort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        int k = 0;

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


    private void swap(int[] arr, int f, int s) {
        int temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }
}
