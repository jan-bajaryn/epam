package by.epam.task04.ex4.example16.controller;

public class Runner {
    public static void main(String[] args) {
        FindSiblingsCommand findSiblingsCommand = new FindSiblingsCommand();
        findSiblingsCommand.execute(3, 43);
        findSiblingsCommand.execute(3, 3);
        findSiblingsCommand.execute(3, 10);
        findSiblingsCommand.execute(3, 31);

    }
}
