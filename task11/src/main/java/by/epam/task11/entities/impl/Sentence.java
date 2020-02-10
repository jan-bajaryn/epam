package by.epam.task11.entities.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Composite;

public class Sentence extends Composite {
    @Override
    public String format(String data) {
        return data;
    }

    @Override
    public CompType type() {
        return CompType.TOKEN;
    }
}
