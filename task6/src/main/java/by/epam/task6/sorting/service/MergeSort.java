package by.epam.task6.sorting.service;

public class MergeSort implements Sort {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private void mergeSortHelper(int[] arr, int fst, int lst) {
        if (fst < lst) {
            int middle = (fst + lst) / 2;

            mergeSortHelper(arr, fst, middle);
            mergeSortHelper(arr, middle + 1, lst);

            merge(arr, fst, middle, lst);
        }
    }

    private void merge(int[] arr, int fst, int middle, int lst) {
        int[] fstArr = new int[middle - fst + 1];
        int[] lstArr = new int[lst - middle];

        for (int i = 0; i < fstArr.length; i++) {
            fstArr[i] = arr[fst + i];
        }
        for (int i = 0; i < lstArr.length; i++) {
            lstArr[i] = arr[middle + i + 1];
        }

        int i = 0;
        int j = 0;

        int current = fst;
        while (i < fstArr.length && j < lstArr.length) {
            if (fstArr[i] < lstArr[j]) {
                arr[current++] = fstArr[i++];
            } else {
                arr[current++] = lstArr[j++];
            }
        }

        while (i < fstArr.length) {
            arr[current++] = fstArr[i++];
        }

        while (j < lstArr.length) {
            arr[current++] = lstArr[j++];
        }

    }


}
