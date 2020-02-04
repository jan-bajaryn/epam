package by.epam.task04.ex4.example16.controller;

import by.epam.task04.ex4.example16.service.exceptiion.IllegalInputMinMaxException;
import by.epam.task04.ex4.example16.service.SiblingsFinder;

public class FindSiblingsCommand {

    private SiblingsFinder siblingsFinder;

    public FindSiblingsCommand() {
        siblingsFinder = new SiblingsFinder();
    }

    public void execute(int min, int max) {
        try {
            System.out.println(siblingsFinder.findSiblings(min, max));
        } catch (IllegalInputMinMaxException e) {
            System.out.println("Maximum ca'nt be less that minimum");
        }
    }
}
