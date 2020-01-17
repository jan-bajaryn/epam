package by.epam.task10.calendar.view.communication;

import by.epam.task10.calendar.view.WrongInput;

public class WrongSpecDateParamsInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("Not right name, description or date.");
    }
}
