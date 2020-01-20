package by.epam.task10.shop.view.communication;

import by.epam.task10.shop.view.WrongInput;

public class NoPackagingWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("You must choose packaging before adding sweets.");
    }
}
