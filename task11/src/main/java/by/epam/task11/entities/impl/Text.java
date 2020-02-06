package by.epam.task11.entities.impl;

import by.epam.task11.entities.Composite;

public class Text extends Composite {
    @Override
    public String format(String data) {
        return "  " + data.substring(0, data.length() - 3);
    }
}
