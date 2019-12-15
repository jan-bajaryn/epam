package by.epam.inclassactivity.runner;


import by.epam.inclassactivity.controller.DateCreator;
import by.epam.inclassactivity.controller.NextDay;
import by.epam.inclassactivity.domain.Date;
import by.epam.inclassactivity.exception.IncorrectParametersException;
import by.epam.inclassactivity.reader.Reader;

public class Main {
    public static void main(String[] args) {
        Date date = new Date(2020, 2, 29);
        System.out.println(date);
        System.out.println(date.nextDay(date));
//        Validator validator = new Validator();
//        Parser parser = new Parser();
        Reader reader = new Reader();
        DateCreator dateCreator = new DateCreator();

        boolean isExit = false;
        while (!isExit) {
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
