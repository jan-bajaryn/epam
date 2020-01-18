package by.epam.task10.textfile.view.communication;

import by.epam.task10.textfile.view.WrongInput;

public class NoFileWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("There no so file. Change signature to work with another file.");
    }
}
