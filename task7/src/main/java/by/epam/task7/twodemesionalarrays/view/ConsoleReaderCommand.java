package by.epam.task7.twodemesionalarrays.view;

import by.epam.task7.twodemesionalarrays.service.Pair;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReaderCommand {

    public static final String ZERO_ONE_FILL = "ZW";
    public static final String ODD_REVERSE = "OR";
    public static final String ROW_NUMBERS_VALUE = "RNV";
    public static final String MAGIC_SQUARE = "MS";
    public static final String EXIT = "E";
    public static final String CYCLE_FILL = "CF";

    private Scanner sc = new Scanner(System.in);

    public String chooseCommand() {
        System.out.println("Choose operation:");
        System.out.println("example 1, Zero-one-filler - " + ZERO_ONE_FILL);
        System.out.println("example 11, odd_row_reverse - " + ODD_REVERSE);
        System.out.println("example 30, check_row - " + ROW_NUMBERS_VALUE);
        System.out.println("example 40, magic_square - " + MAGIC_SQUARE);
        System.out.println("example additional, cycle fill - " + CYCLE_FILL);
        System.out.println("Exit - " + EXIT);
        return sc.nextLine();
    }

    public Pair readParams() throws IllegalInputReaderException {
        try {
            System.out.println("enter row number");
            int rows = sc.nextInt();
            System.out.println("enter column number");
            int columns = sc.nextInt();
            return new Pair(rows, columns);
        } catch (InputMismatchException e) {
            throw new IllegalInputReaderException(e);
        } finally {
            sc.nextLine();
        }

    }

    public Pair readMinMax() throws IllegalInputReaderException {
        try {
            System.out.println("Please, enter minimum");
            int min = sc.nextInt();
            System.out.println("Please enter maximum");
            int max = sc.nextInt();
            return new Pair(min, max);
        } catch (InputMismatchException e) {
            throw new IllegalInputReaderException(e);
        } finally {
            sc.nextLine();
        }

    }

    public Pair readCheckParams() throws IllegalInputReaderException {
        try {
            System.out.println("Enter number what we check");
            int res1 = sc.nextInt();
            System.out.println("Enter number times");
            int res2 = sc.nextInt();
            return new Pair(res1, res2);
        } catch (InputMismatchException e) {
            throw new IllegalInputReaderException(e);
        } finally {
            sc.nextLine();
        }

    }

    public int readSize() throws IllegalInputReaderException {
        try {
            System.out.println("Write the size of square matrix");
            int result = sc.nextInt();
            return result;
        } catch (InputMismatchException e) {
            throw new IllegalInputReaderException(e);
        } finally {
            sc.nextLine();
        }

    }

    public int readBeginValue() throws IllegalInputReaderException {
        try {
            System.out.println("Please enter begin value for filling");
            int result = sc.nextInt();
            return result;
        } catch (InputMismatchException e) {
            throw new IllegalInputReaderException(e);
        } finally {
            sc.nextLine();
        }

    }
}
