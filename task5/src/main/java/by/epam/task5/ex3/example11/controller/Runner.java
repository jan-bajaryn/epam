package by.epam.task5.ex3.example11.controller;

public class Runner {
    public static void main(String[] args) {
        int[] nat = {4, 4, 2, 3, 4, 6, 3, 4, 4, 2, 3, 7};
        ReminderCommand reminderCommand = new ReminderCommand();
        reminderCommand.execute(nat, 3, 1);
    }
}
