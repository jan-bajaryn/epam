package by.epam.task10.textfile.view.communication;

import by.epam.task10.textfile.view.WrongInput;

public class ParamsFileWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("Wrong parameters to file creation");
    }
}
