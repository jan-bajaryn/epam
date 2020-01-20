package by.epam.task10.shop.view.communication;

import by.epam.task10.shop.view.WrongInput;

public class InvalidIndexWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("Please choose index from the list.");
    }
}
