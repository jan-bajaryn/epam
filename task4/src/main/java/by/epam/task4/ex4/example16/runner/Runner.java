package by.epam.task4.ex4.example16.runner;

import by.epam.task4.ex4.example16.controller.FindSiblingsCommand;

public class Runner {
    public static void main(String[] args) {
        FindSiblingsCommand findSiblingsCommand = new FindSiblingsCommand();
        findSiblingsCommand.execute(3, 43);
        findSiblingsCommand.execute(3, 3);
        findSiblingsCommand.execute(3, 10);
        findSiblingsCommand.execute(3, 31);

//        SiblingsFinder siblingsFinder = new SiblingsFinder();
//        System.out.println(siblingsFinder.findSimpleNum(3,3));
    }
}
