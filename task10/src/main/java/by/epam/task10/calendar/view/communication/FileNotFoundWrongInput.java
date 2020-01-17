package by.epam.task10.calendar.view.communication;

import by.epam.task10.calendar.view.WrongInput;

public class FileNotFoundWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("There not so file found. Please choose another file name.");
    }
}
