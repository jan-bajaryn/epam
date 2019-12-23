package by.epam.task6.sorting.service;

public class InsertBinarySort implements Sort {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }


        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                insPrev(arr, 0, i - 1, i);
            }
        }
    }

    private void insPrev(int[] arr, int left, int right, int index) {
        if (left == right && arr[index] <= arr[left]) {
            if (left != 0) {

                if (arr[index] >= arr[left - 1]) {
                    insert(arr, index, left);
                    return;
                } else {
                    return;
                }
            } else {
                insert(arr, index, 0);
                return;
            }
        }


        int border = (left + right) / 2;


        if (arr[index] <= arr[border]) {
            if (border == 0) {
                insert(arr, index, 0);
                return;
            }
            if (arr[index] >= arr[border - 1]) {
                insert(arr, index, border);
            } else {
                insPrev(arr, left, border - 1, index);
            }
        } else {
            insPrev(arr, border + 1, right, index);
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
