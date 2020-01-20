package by.epam.task10.shop.view.communication;

import by.epam.task10.shop.view.WrongInput;

public class NotEmptyGiftWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("Your gift not empty. Delete all sweets and than change packaging.");
    }
}
