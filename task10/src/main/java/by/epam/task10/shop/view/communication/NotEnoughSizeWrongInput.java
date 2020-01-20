package by.epam.task10.shop.view.communication;

import by.epam.task10.shop.view.WrongInput;

public class NotEnoughSizeWrongInput implements WrongInput {

    @Override
    public void displayMessage() {
        System.out.println("Not enough size for this sweet.");
    }
}
