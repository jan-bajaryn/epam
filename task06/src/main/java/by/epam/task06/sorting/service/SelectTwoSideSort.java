package by.epam.task06.sorting.service;


public class SelectTwoSideSort implements Sort {
    @Override
    public void sort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        int lastIndex = arr.length - 1;
        int i = 0;
        while (i <= lastIndex) {
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
            i++;
        }
    }


    private void swap(int[] arr, int f, int s) {
        int temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }
}
