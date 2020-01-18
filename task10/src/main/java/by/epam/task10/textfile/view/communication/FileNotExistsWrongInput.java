package by.epam.task10.textfile.view.communication;

import by.epam.task10.textfile.view.WrongInput;

public class FileNotExistsWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("File already not exists. Change signature to work further.");
    }
}
