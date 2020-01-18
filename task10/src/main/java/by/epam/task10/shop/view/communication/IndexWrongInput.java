package by.epam.task10.shop.view.communication;

import by.epam.task10.shop.view.WrongInput;

public class IndexWrongInput implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("Index can't be less or more than existing or typed not like integer.");
    }
}
