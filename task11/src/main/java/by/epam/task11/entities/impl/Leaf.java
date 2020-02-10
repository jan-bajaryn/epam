package by.epam.task11.entities.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Component;

public class Leaf implements Component {

    private Character element;

    public Leaf(Character element) {
        this.element = element;
    }

    public Character getElement() {
        return element;
    }

    public void setElement(Character element) {
        this.element = element;
    }

    @Override
    public void add(Component c) {
    }

    @Override
    public Component getChild(int index) {
        return null;
    }

    @Override
    public String operation() {
        return element.toString();
    }

    @Override
    public void remove(Component c) {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public CompType type() {
        return CompType.LEAF;
    }

}
