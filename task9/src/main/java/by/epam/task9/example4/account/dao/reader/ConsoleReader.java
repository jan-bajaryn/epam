package by.epam.task9.example4.account.dao.reader;

import java.util.Scanner;

public class ConsoleReader {
    private Scanner sc = new Scanner(System.in);

    public String[] readParamsAccount() {
        String[] arr = new String[6];
        System.out.println("Please write id of the person");
        arr[0] = sc.nextLine();
        System.out.println("Please write the name of person");
        arr[1] = sc.nextLine();
        System.out.println("Please write the surname of person");
        arr[2] = sc.nextLine();
        System.out.println("Please write number of the account");
        arr[3] = sc.nextLine();
        System.out.println("Please write amount on the account");
        arr[4] = sc.nextLine();
        System.out.println("Write isActive account: true or false");
        arr[5] = sc.nextLine();
        return arr;
    }
}
