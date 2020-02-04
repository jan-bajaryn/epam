package by.epam.task05.shiftarr.controller;

public class Runner {
    public static void main(String[] args) {
        ArrayShifterCommand arrayShifterCommand = new ArrayShifterCommand();

        arrayShifterCommand.execute(new int[]{1, 2, 3}, 1);
        arrayShifterCommand.execute(new int[]{1, 2, 3, 4, 5, 6}, 3);
        arrayShifterCommand.execute(new int[]{1, 2, 3, 5, 6, 7, 8}, 1);
        arrayShifterCommand.execute(new int[]{1}, 1);
        arrayShifterCommand.execute(new int[]{1, 2}, 1);
        arrayShifterCommand.execute(null, 1);
    }
}
