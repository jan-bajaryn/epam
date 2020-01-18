package by.epam.task10.textfile.view.communication;


import by.epam.task10.textfile.view.WrongInput;

public class NullFileNameWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("File name can't be null.");
    }
}
