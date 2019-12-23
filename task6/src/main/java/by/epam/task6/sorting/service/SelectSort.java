package by.epam.task6.sorting.service;


public class SelectSort implements Sort {
    @Override
    public void sort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
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

    private void swap(int[] arr, int f, int s) {
        int temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }
}
