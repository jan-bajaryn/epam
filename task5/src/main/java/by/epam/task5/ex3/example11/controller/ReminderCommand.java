package by.epam.task5.ex3.example11.controller;

import by.epam.task5.ex3.example11.service.exception.IllegalInputArgumentException;
import by.epam.task5.ex3.example11.service.exception.NullArrayException;
import by.epam.task5.ex3.example11.service.ReminderService;

import java.util.Arrays;

public class ReminderCommand {
    private ReminderService reminderService;

    public ReminderCommand() {
        reminderService = new ReminderService();
    }

    public void execute(int[] nat, int m, int l) {

        try {

            Integer[] numNoReminder = reminderService.findNumNoReminder(nat, m, l);
            System.out.println("Input array is: " + Arrays.toString(nat));
            System.out.println("Output array is: " + Arrays.toString(numNoReminder));
        } catch (NullArrayException e) {
            System.out.println("Array can't be null");
        } catch (IllegalInputArgumentException e) {
            System.out.println("l must be 0 < l < m");
        }
    }
}
