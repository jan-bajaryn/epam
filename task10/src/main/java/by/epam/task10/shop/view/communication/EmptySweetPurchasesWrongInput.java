package by.epam.task10.shop.view.communication;

import by.epam.task10.shop.view.WrongInput;

public class EmptySweetPurchasesWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("There nothing to remove.");
    }
}
