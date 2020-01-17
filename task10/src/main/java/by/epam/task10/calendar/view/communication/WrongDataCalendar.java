package by.epam.task10.calendar.view.communication;

import by.epam.task10.calendar.view.WrongInput;

public class WrongDataCalendar implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("Format of data in this file is illegal. Please choose another file.");
    }
}
