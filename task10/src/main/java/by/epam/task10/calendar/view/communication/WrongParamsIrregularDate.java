package by.epam.task10.calendar.view.communication;

import by.epam.task10.calendar.view.WrongInput;

public class WrongParamsIrregularDate implements WrongInput {
    @Override
    public void displayMessage() {
        System.out.println("Wrong params for Irregular date.");
    }
}
