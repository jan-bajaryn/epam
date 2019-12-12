package by.epam.inclassactivity.reader;

import java.util.Scanner;

public class Reader {
    private Scanner sc;

    public Reader() {
        sc = new Scanner(System.in);
    }

    public String[] command(){
        String [] result = new String[3];
        System.out.println("Enter year: ");
        result[0] = sc.nextLine();
        System.out.println("Enter month: ");
        result[1] = sc.nextLine();
        System.out.println("Enter day: ");
        result[2] = sc.nextLine();
        return result;
    }

    public String isContinue(){
        System.out.println("Do you want to continue?\n y - yes, n - no");
        return sc.nextLine();
    }
}
