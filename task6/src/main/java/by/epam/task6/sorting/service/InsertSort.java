package by.epam.task6.sorting.service;


public class InsertSort implements Sort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }


        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                insertToPrev(arr, i);
            }
        }
    }

    private void insertToPrev(int[] arr, int index) {
        for (int i = 0; i < index; i++) {
            if (arr[i] >= arr[index]) {
                insert(arr, index, i);
                return;
            }
        }
    }

    private void insert(int[] arr, int indexFrom, int indexTo) {
        int temp = arr[indexFrom];
        for (int i = indexFrom; i > indexTo; i--) {
            arr[i] = arr[i - 1];
        }
        arr[indexTo] = temp;
    }
}
