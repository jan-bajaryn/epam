package by.epam.task5.ex4.example16.controller;

import by.epam.task5.ex4.example16.exception.ArrayEmptyException;
import by.epam.task5.ex4.example16.exception.ArrayNotEvenException;
import by.epam.task5.ex4.example16.exception.ArrayNullException;
import by.epam.task5.ex4.example16.service.FindMaxForm;

public class FindMaxFormCommand {

    private FindMaxForm findMaxForm;

    public FindMaxFormCommand() {
        findMaxForm = new FindMaxForm();
    }

    public void execute(double[] arr) {
        try {
            System.out.println("Maximum value from this sequence using this function is " + findMaxForm.find(arr));
        } catch (ArrayNullException e) {
            System.out.println("Array can't be null.");
        } catch (ArrayEmptyException e) {
            System.out.println("Array can't be empty.");
        } catch (ArrayNotEvenException e) {
            System.out.println("Must be even count of element in array.");
        }
    }
}
