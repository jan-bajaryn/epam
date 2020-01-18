package by.epam.task10.textfile.view.communication;

import by.epam.task10.textfile.view.WrongInput;

public class NullFFileWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("You must make signature for this file at first. Choose 'change signature' for that");
    }
}
