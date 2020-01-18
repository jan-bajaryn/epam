package by.epam.task10.textfile.view.communication;

import by.epam.task10.textfile.view.WrongInput;

public class NullAppendDataWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("Input data can't be null");
    }
}
