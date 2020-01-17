package by.epam.task10.calendar.view.communication;

import by.epam.task10.calendar.view.WrongInput;

public class WrongINputRegularParams implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("Wrong input for regular date.");
    }
}
