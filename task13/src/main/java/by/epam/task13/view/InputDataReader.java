package by.epam.task13.view;

import by.epam.task13.consolecontroller.controller.Controller;

import java.util.ResourceBundle;
import java.util.Scanner;

public class InputDataReader {
    public String readFileName() {

        ResourceBundle rb = ResourceBundle.getBundle("property.text", Controller.locale);
        System.out.println(rb.getString("consoleinput.enterfile"));
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String readMethod() {


        ResourceBundle rb = ResourceBundle.getBundle("property.text", Controller.locale);
        System.out.println(rb.getString("consoleinput.entermethod"));
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
