package by.epam.task05.reversearr.service;

public class ArrayReverser {

    public int[] reverseArr(int[] arr) {

        if (arr == null || arr.length < 1){
            return arr;
        }

        int[] bufArr = new int[arr.length];

        int j = arr.length;
        for (int i = 0; i < arr.length; i++) {
            bufArr[i] = arr[--j];
        }
        return bufArr;
    }
}
