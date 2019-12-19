package by.epam.task5.ex1.example1.controller;

public class Runner {
    public static void main(String[] args) {
        int[] nat = {3, 5, 2, 3, 4, 2, 3, 4, 3, 5, 2, 3, 4, 8, 7, 34};
        DivisibleCommand divisibleCommand = new DivisibleCommand();
        divisibleCommand.execute(nat, 2);
        nat = new int[]{};
        divisibleCommand.execute(nat, 2);
        nat = new int[]{3, 9, 2, 2, 4};
        divisibleCommand.execute(nat, 2);
        nat = new int[]{1};
        divisibleCommand.execute(nat, 2);
        nat = new int[]{2};
        divisibleCommand.execute(nat, 2);
    }
}
