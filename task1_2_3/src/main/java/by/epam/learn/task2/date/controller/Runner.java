package by.epam.learn.task2.date.controller;


import by.epam.learn.task2.date.creator.DateCreator;
import by.epam.learn.task2.date.entity.Date;
import by.epam.learn.task2.date.creator.exception.IncorrectParametersException;
import by.epam.learn.task2.date.reader.Reader;

public class Runner {
    public static void main(String[] args) {
        Reader reader = new Reader();
        DateCreator dateCreator = new DateCreator();

        boolean isExit = false;
        while (!isExit) {
            switch (reader.isContinue()) {
                case "y":
                    String[] input = reader.command();
                    try {
                        Date date = dateCreator.createDate(input);
                        NextDay nextDay = new NextDay(date);
                        System.out.println("Next date after that is: " + nextDay.execute());
                    } catch (IncorrectParametersException e) {
                        System.out.println("Incorrect input. Try again.");
                    }
                    break;
                case "n":
                    isExit = true;
                    break;
                default:
                    System.out.println("Try again.");
            }
        }

    }
}
