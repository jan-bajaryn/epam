package by.epam.task5.reversearr.service;

import by.epam.task5.reversearr.exception.IllegalArgumentSizeException;
import by.epam.task5.reversearr.exception.IllegalInputParameterException;

import java.util.Arrays;

public class ReverseArrayRandomly {
    private ArrayFiller arrayFiller;
    private ArrayReverser arrayReverser;

    public ReverseArrayRandomly() {
        arrayFiller = new ArrayFiller();
        arrayReverser = new ArrayReverser();
    }

    public void execute(int size,
                        int min,
                        int max) {
        try {
            int[] arr = arrayFiller.fillRandomly(size, min, max);

            System.out.println("This is filled randomly array = " + Arrays.toString(arr));

            System.out.println("This is reversed array = " + Arrays.toString(arrayReverser.reverseArr(arr)));


        } catch (IllegalArgumentSizeException e) {
            System.out.println("Size you input is incorrect.");
        } catch (IllegalInputParameterException e) {
            System.out.println("minimum can't be less then maximum.");
        }
    }
}
