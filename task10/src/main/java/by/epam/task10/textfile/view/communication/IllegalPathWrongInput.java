package by.epam.task10.textfile.view.communication;

import by.epam.task10.textfile.view.WrongInput;

public class IllegalPathWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("Wrong path to file.");
    }
}
