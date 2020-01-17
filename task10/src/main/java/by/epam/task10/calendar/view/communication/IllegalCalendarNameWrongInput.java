package by.epam.task10.calendar.view.communication;

import by.epam.task10.calendar.view.WrongInput;

public class IllegalCalendarNameWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("You need to write only letters, numbers or spaces.");
    }
}
