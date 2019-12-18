package by.epam.taskarr;

import by.epam.taskarr.controller.ArrayReverser;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        ArrayReverser arrayReverser = new ArrayReverser();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(arrayReverser.reverseArr(arr)));
    }

}
