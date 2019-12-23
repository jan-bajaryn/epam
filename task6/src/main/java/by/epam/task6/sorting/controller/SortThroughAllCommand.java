package by.epam.task6.sorting.controller;

import by.epam.task6.sorting.service.Sort;

import java.util.Arrays;

public class SortThroughAllCommand {

    public static final String SPLITER = "----------------------------------------------";

    public void printSort(Sort sortIns) {
        System.out.println("Testing: --" + sortIns.getClass().getName() + " --type of sort");
        int[][] arr = {{1, 4, 5, 2, 3, 2, 3, 4, 4},
                {1, 4, 5, 2, 3, 2, 3, 4},
                {1, 4},
                {4, 1},
                {},
                {1},
//                {3,4,1},
//                {3, 4, 1, 2},
                {3, 4, 1, 2,1},
                null
        };
        testArrays(arr, sortIns);
    }

    private void testArrays(int[][] arr, Sort sortIns) {
        for (int i = 0; i < arr.length; i++) {
            try {
                System.out.println("Source = \t\t\t" + Arrays.toString(arr[i]));
                int[] tempArr = Arrays.copyOf(arr[i], arr[i].length);
                Arrays.sort(tempArr);

                sortIns.sort(arr[i]);
                System.out.print("Sorted array: \t\t" + Arrays.toString(arr[i]));
                if (Arrays.compare(tempArr, arr[i]) != 0) {
                    System.out.println("\t\t -- not right.");
                } else {
                    System.out.println();
                }
            } catch (NullPointerException e) {
                System.out.println("Problems to implement with with this array");
            }
        }
        System.out.println(SPLITER);
    }
}
