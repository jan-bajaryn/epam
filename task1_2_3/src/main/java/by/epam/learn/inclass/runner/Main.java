package by.epam.learn.inclass.runner;

import by.epam.learn.inclass.controller.DateCreator;
import by.epam.learn.inclass.controller.NextDay;
import by.epam.learn.inclass.exception.IncorrectParametersException;
import by.epam.learn.inclass.reader.Reader;
import by.epam.learn.inclass.validator.Validator;
import by.epam.learn.inclass.domain.Date;

public class Main {
    public static void main(String[] args) {
        Date date = new Date(2020, 2, 29);
        System.out.println(date);
        System.out.println(date.nextDay(date));
//        Validator validator = new Validator();
//        Parser parser = new Parser();
        Reader reader = new Reader();
        DateCreator dateCreator = new DateCreator();

        while (true) {
            switch (reader.isContinue()) {
                case "y":
                    String[] input = reader.command();
//                    if (validator.isValid(input)) {
//                        int[] inputInt = parser.parseStrArrToIntArr(input);
////                        Date date1 = new Date(inputInt[0], inputInt[1], inputInt[2]);
//                        Date date1 = dateCreator.createDate(inputInt);
//                        System.out.println("Next date after that is: " + date1.nextDay(date1));
//                    } else {
//                        System.out.println("Incorrect input. Please try again");
//                    }
                    try {
                        Date date1 = dateCreator.createDate(input);
                        NextDay nextDay = new NextDay(date1);
                        System.out.println("Next date after that is: " + nextDay.execute());
                    } catch (IncorrectParametersException e) {
//                        e.printStackTrace();
                        System.out.println("Incorrect input. Try again.");
                    }
                    break;
                case "n":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Try again.");
            }
        }

    }
}
