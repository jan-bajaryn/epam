package by.epam.task9.example4.account.view;

import java.util.Scanner;

public class ConsoleCommander {

    public static final String READING_FILE = "R";
    public static final String SELF_INPUT = "S";
    public static final String EXIT = "E";
    public static final String FIND_BY_USER_ID = "FUI";
    public static final String FIND_MORE_AMOUNT = "FMA";
    public static final String FIND_LESS_EQ_AMOUNT = "FLEA";
    public static final String FIND_ACTIVE = "FA";
    public static final String FIND_PASSIVE = "FP";
    public static final String SORT_BY_USERNAME = "SBU";
    public static final String SORT_BY_SURNAME = "SBS";
    public static final String AMOUNT_BY_USER_ID = "ABUI";
    public static final String SUM_AMOUNT_MORE_ZERO = "SAMZ";
    public static final String SUM_AMOUNT_LESS_ZERO = "SALZ";
    public static final String WRITE = "W";
    public static final String PRINT = "P";
    public static final String CONTINUE = "C";

    private Scanner sc = new Scanner(System.in);

    public String readFirstCommand() {
        System.out.println("Do you want read account from file - " + READING_FILE);
        System.out.println("Or write it by yourself - " + SELF_INPUT);
        System.out.println("Exit - " + EXIT);
        return sc.nextLine();
    }

    public String readSecondCommand() {
        System.out.println("Find by user id - " + FIND_BY_USER_ID);
        System.out.println("Find accounts with more amount than input - " + FIND_MORE_AMOUNT);
        System.out.println("Find accounts with less or equal amount than input - " + FIND_LESS_EQ_AMOUNT);
        System.out.println("Find active - " + FIND_ACTIVE);
        System.out.println("Find passive - " + FIND_PASSIVE);
        System.out.println("Sort by username - " + SORT_BY_USERNAME);
        System.out.println("Sort by surname - " + SORT_BY_SURNAME);
        System.out.println("Amount by user id - " + AMOUNT_BY_USER_ID);
        System.out.println("Sum amount more than zero - " + SUM_AMOUNT_MORE_ZERO);
        System.out.println("Sum amount less than zero - " + SUM_AMOUNT_LESS_ZERO);
        System.out.println("Exit - " + EXIT);
        return sc.nextLine();
    }

    public String readDesisionWrite() {
        System.out.println("Do you want to write result into file, or print it?");
        System.out.println("Print - " + PRINT);
        System.out.println("Write - " + WRITE);
        return sc.nextLine();
    }

    public String readFileName() {
        System.out.println("Please enter file name.");
        return sc.nextLine();
    }

    public int readUserId() {
        System.out.println("Please enter user id.");
        int result = sc.nextInt();
        sc.nextLine();
        return result;
    }

    public int readAmount() {
        System.out.println("Please enter amount");
        int result = sc.nextInt();
        sc.nextLine();
        return result;
    }

    public String isContinue() {
        System.out.println("Do you want to continue?");
        System.out.println("Continue - " + CONTINUE);
        return sc.nextLine();
    }
}
