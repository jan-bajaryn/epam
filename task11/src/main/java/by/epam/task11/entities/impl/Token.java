package by.epam.task11.entities.impl;

import by.epam.task11.entities.Composite;

public class Token extends Composite {
    @Override
    public String format(String data) {
        return data + " ";
    }
}
