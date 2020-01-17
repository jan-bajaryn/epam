package by.epam.task10.calendar.view.communication;

import by.epam.task10.calendar.view.WrongInput;

public class NullFileNameWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("File name can't be null.");
    }
}
