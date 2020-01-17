package by.epam.task10.calendar.view;

import by.epam.task10.calendar.controller.Controller;
import by.epam.task10.calendar.entity.IrregularFreeCelebrity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputDataReader {
    private Scanner sc = new Scanner(System.in);

    public String readFileName() {
        System.out.println("Please enter fileName with extension from what you want to take the calendar.");
        return sc.nextLine();
    }

    public String readCalendarName() {
        System.out.println("Please enter new name for calendar(use only letters, spaces and numbers)");
        return sc.nextLine();
    }

    public List<Object> readSpecDateParams() {
        List<Object> list = new ArrayList<>();
        System.out.println("Is that celebrity? (true or false)");
        list.add(sc.nextBoolean());
        sc.nextLine();
        System.out.println("Is that free date? (true or false)");
        list.add(sc.nextBoolean());
        sc.nextLine();
        System.out.println("Enter name");
        list.add(sc.nextLine());
        System.out.println("Enter description");
        list.add(sc.nextLine());
        System.out.println("Enter year of date.");
        list.add(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter month of date.");
        list.add(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter day of date.");
        list.add(sc.nextInt());
        sc.nextLine();
        return list;
    }

    public List<Object> readRegularDayParams() {
        List<Object> list = new ArrayList<>();
        System.out.println("Is that celebrity? (true or false)");
        list.add(sc.nextBoolean());
        sc.nextLine();
        System.out.println("Is that free date? (true or false)");
        list.add(sc.nextBoolean());
        sc.nextLine();
        System.out.println("Enter name");
        list.add(sc.nextLine());
        System.out.println("Enter description");
        list.add(sc.nextLine());
        System.out.println("Enter year of beginDate.");
        list.add(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter month of beginDate.");
        list.add(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter day of beginDate.");
        list.add(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter count of days for period.");
        list.add(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter count of months for period");
        list.add(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter count of years for period");
        list.add(sc.nextInt());
        sc.nextLine();
        System.out.println("Is this date repeat only after begin date, or on both directions(true - yes, false - no)?");
        list.add(sc.nextBoolean());
        sc.nextLine();
        return list;
    }

    public int readIndex() {
        List<IrregularFreeCelebrity> irregularDates = Controller.irregularDates;
        for (int i = 0; i < irregularDates.size(); i++) {
            IrregularFreeCelebrity irregularFreeCelebrity = irregularDates.get(i);
            System.out.println(i + irregularFreeCelebrity.getName() + "  " + irregularFreeCelebrity.getDescription());
        }
        System.out.println("Please select index of irregular date to add.");
        int result = sc.nextInt();
        sc.nextLine();
        return result;
    }
}
