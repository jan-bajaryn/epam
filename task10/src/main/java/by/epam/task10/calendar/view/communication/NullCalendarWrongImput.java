package by.epam.task10.calendar.view.communication;

import by.epam.task10.calendar.view.WrongInput;

public class NullCalendarWrongImput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("For this command you need to create or read calendar at first.");
    }
}
